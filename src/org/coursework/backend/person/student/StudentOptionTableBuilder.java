package org.coursework.backend.person.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.coursework.Main;
import org.coursework.backend.group.Group;
import org.coursework.backend.person.Person;
import static org.coursework.backend.person.PersonTableBuilder.TABLE_NAME;
import org.coursework.backend.roles.Role;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableBuilder;

public class StudentOptionTableBuilder implements TableBuilder<StudentOption> {

    public static final String TABLE_NAME = "STUDENTOPTIONS";
    
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getTableColumns() {
        String[] columns = new String[12];
        columns[0] = "id";
        columns[1] = "studentid";
        columns[2] = "groupid";
        for (int A = 0; A < 9; A++) {
            columns[A + 3] = "role" + A;
        }
        return columns;
    }

    @Override
    public void setInTable(StudentOption option) throws SQLException {
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        Object[] columns = new Object[12];
        columns[0] = option.getId();
        columns[1] = option.getStudent().getId();
        Optional<Group> opGroup = Main.getGroups().stream().filter(g -> g.getOptions().stream().anyMatch(o -> o.getId() == option.getId())).findAny();
        if (opGroup.isPresent()) {
            columns[2] = opGroup.get().getId();
        }
        for (int A = 0; A < 9; A++) {
            Role role = null;
            if (A >= option.getRoles().size()) {
                Optional<Role> opRole = Main.getRoles().stream().filter(r -> !option.getRoles().stream().anyMatch(e -> r.equals(e))).findFirst();
                if (opRole.isPresent()) {
                    role = opRole.get();
                }
            } else {
                role = option.getRoles().get(A);
            }
            if (role == null) {
                columns[A + 3] = null;
            } else {
                columns[A + 3] = role.getId();
            }
        }
        link.insertInto(this, columns);
    }
    
    @Override
    public void updateInTable(StudentOption option) throws SQLException {
        String[] columns = getTableColumns();
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        Optional<Group> opGroup = Main.getGroups().stream().filter(g -> g.getOptions().contains(option)).findAny();
        int groupId = -1;
        if(opGroup.isPresent()){
           groupId = opGroup.get().getId();
        }
        String student = columns[1] + " = " + option.getStudent().getId();
        String group1 = columns[2] + " = " + groupId;
        String statement = "UPDATE " + TABLE_NAME + " SET " + student + ", " + group1;
        for(int A = 0; A < 9; A++){
            if(option.getRoles().size() <= A){
                statement = statement + ", " + columns[A + 3] + " = -1";
            }else{
                Role role = option.getRoles().get(A);
                statement = statement + ", " + columns[A + 3] + " = " + role.getId();
            }
        }
        statement = statement + " WHERE " + columns[0] + " = " + option.getId();
        link.executeUpdate(statement);
    }

    @Override
    public String getTableColumnSQL(String columnName) {
        switch (columnName) {
            case "id":
                return "id INTEGER not NULL";
            case "studentid":
                return "studentid INTEGER not NULL";
            default:
                return columnName + " INTEGER";
        }
    }

    @Override
    public Set<StudentOption> getData(CoreDatabaseLink link) throws SQLException {
        List<StudentOption> options = new ArrayList<>();
        String[] columns = getTableColumns();
        ResultSet set = link.executeQuery("SELECT * FROM " + getTableName());
        while (set.next()) {
            final int id = set.getInt(columns[0]);
            final int studentId = set.getInt(columns[1]);
            final Integer groupId = set.getInt(columns[2]);
            Optional<Student> opStudent = Main.getPeople(Student.class).stream().filter(p -> p.getId() == studentId).findFirst();
            if (!opStudent.isPresent()) {
                System.err.println("StudentOptionTableBuilder.getData() was launched before PeopleTableBuilder");
                continue;
            }
            StudentOption option = new StudentOption(id, opStudent.get());
            if (groupId != null) {
                Optional<Group> opGroup = Main.getGroups().stream().filter(g -> g.getId() == groupId).findFirst();
                if (opGroup.isPresent()) {
                    opGroup.get().register(option);
                } else {
                    System.err.println("StudentOption.getData() was launched before GroupTableBuilder");
                }
            }
            for (int A = 0; A < 9; A++) {
                int columnNo = A + 3;
                final int roleId = set.getInt(columnNo);
                Optional<Role> opRole = Main.getRoles().stream().filter(r -> r.getId() == roleId).findFirst();
                if (!opRole.isPresent()) {
                    System.err.println("StudentOption.getData() was launched before RoleTableBuilder");
                    continue;
                }
                Role role = opRole.get();
                option.registerRole(role);
            }
            options.add(option);
        }
        return new HashSet<>(options);
    }

    @Override
    public void registerWithMain(StudentOption... value) {
        Main.register(value);
    }

    @Override
    public Set<StudentOption> getDataFromMain() {
        return Main.getEnteredOptions();
    }
    
    @Override
    public StudentOption[] toArray(int size) {
        return new StudentOption[size];
    }

}
