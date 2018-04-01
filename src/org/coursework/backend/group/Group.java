package org.coursework.backend.group;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.database.table.TableLink;

public class Group implements TableLink {

    int id;
    List<StudentOption> options = new ArrayList<>();

    public Group(StudentOption... options) throws SQLException {
        this(Arrays.asList(options));
    }

    public Group(Collection<StudentOption> options) throws SQLException {
        this(true, options);
    }

    public Group(boolean useDatabase, StudentOption... options) throws SQLException {
        this(useDatabase, Arrays.asList(options));
    }

    public Group(boolean useDatabase, Collection<StudentOption> options) throws SQLException {
        this(TableLink.getUniquieId(GroupTableBuilder.TABLE_NAME, useDatabase), options);
    }

    public Group(int id, StudentOption... options) {
        this(id, Arrays.asList(options));
    }

    public Group(int id, Collection<StudentOption> options) {
        this.options.addAll(options);
    }

    public Set<StudentOption> getOptions() {
        return new HashSet<>(options);
    }

    public Set<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        options.stream().forEach(o -> students.add(o.getStudent()));
        return new HashSet<>(students);
    }

    public void register(StudentOption... options) {
        for (StudentOption option : options) {
            this.options.add(option);
        }
    }

    public boolean swap(StudentOption newStudent) {
        return swap(newStudent, options.stream().filter(s -> newStudent.getPreferredRole().get().equals(s.getPreferredRole().get())).findFirst().get());
    }

    public boolean swap(StudentOption newStudent, StudentOption with) {
        if (!options.contains(with)) {
            return false;
        }
        options.remove(with);
        options.add(newStudent);
        return true;
    }

    @Override
    public int getId() {
        return id;
    }

}
