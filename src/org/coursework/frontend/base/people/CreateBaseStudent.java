package org.coursework.frontend.base.people;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;

public interface CreateBaseStudent extends CreateBasePerson {

    List<String> getSelectedRoles();

    Collection<Role> getChoosableRoles();

    public default Student createStudent() throws SQLException {
        return new Student(getFirstName(), getLastName(), false);
    }

    public default StudentOption createOption() throws SQLException {
        return createOption(createStudent());
    }

    public default StudentOption createOption(Student student) {
        List<Role> roles = new ArrayList<>();
        getSelectedRoles().stream().forEach(s -> getChoosableRoles().stream().filter(c -> c.getDisplayName().equals(s)).forEach(c -> roles.add(c)));
        getChoosableRoles().stream().filter(c -> !roles.stream().anyMatch(r -> r.equals(c))).forEach(c -> roles.add(c));
        return new StudentOption(student, roles);
    }

}
