package org.coursework.backend.person;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.coursework.Main;
import org.coursework.backend.person.staff.Staff;
import org.coursework.backend.person.student.Student;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableBuilder;

public class PersonTableBuilder implements TableBuilder<Person> {
    
    public static final String TABLE_NAME = "ACCOUNTS";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getTableColumns() {
        String[] columns = {"id", "firstname", "lastname", "permission"};
        return columns;
    }

    @Override
    public void setInTable(Person person) throws SQLException {
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        link.insertInto(this, person.getId(), person.getFirstName(), person.getLastName(), person.getPermission().name());
    }
    
    @Override
    public void updateInTable(Person person) throws SQLException {
        String[] columns = getTableColumns();
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        String first = columns[1] + " = '" + person.getFirstName() + "'";
        String last = columns[2] + " = '" + person.getLastName() + "'";
        String permission = columns[3] + " = '" + person.getPermission().name() + "'";
        String statement = "UPDATE " + TABLE_NAME + " SET " + first + ", " + last + ", " + permission + " WHERE " + columns[0] + " = " + person.getId();
        link.executeUpdate(statement);
    }

    @Override
    public String getTableColumnSQL(String columnName) {
        switch (columnName) {
            case "id":
                return "id INTEGER not NULL";
            case "firstname":
                return "firstname VARCHAR(255) not NULL";
            case "lastname":
                return "lastname VARCHAR(255) not NULL";
            case "permission":
                return "permission VARCHAR(10) not NULL";
            default:
                return null;
        }
    }

    @Override
    public Set<Person> getData(CoreDatabaseLink link) throws SQLException {
        String[] columns = getTableColumns();
        List<Person> people = new ArrayList<>();
        ResultSet set = link.executeQuery("SELECT * FROM " + getTableName());
        while (set.next()) {
            int id = set.getInt(columns[0]);
            String firstName = set.getString(columns[1]);
            String lastName = set.getString(columns[2]);
            String permissionString = set.getString(columns[3]);
            Permission permission = Permission.valueOf(permissionString);
            if (permission == null) {
                System.err.println("Unknown permission: " + permissionString);
            }
            switch (permission) {
                case STUDENT:
                    people.add(new Student(id, firstName, lastName));
                    continue;
                case ADMIN:
                    people.add(new Staff(id, firstName, lastName));
                    continue;
                default:
                    throw new AssertionError(permission.name());
            }
        }
        return new HashSet<>(people);
    }
    
    /*@Override
    public void registerWithMain(Collection<Person> people){
        Person[] peopleArray = new Person[people.size()];
        people.toArray(peopleArray);
        registerWithMain(peopleArray);
    }*/

    @Override
    public void registerWithMain(Person... value) {
        Main.register(value);
    }

    @Override
    public Set<Person> getDataFromMain() {
        return Main.getPeople();
    }

    @Override
    public Person[] toArray(int size) {
        return new Person[size];
    }

}
