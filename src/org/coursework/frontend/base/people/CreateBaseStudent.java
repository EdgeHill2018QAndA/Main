package org.coursework.frontend.base.people;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.coursework.Main;

import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;

public interface CreateBaseStudent extends CreateBasePerson {

    List<String> getSelectedRoles();

    Collection<Role> getChoosableRoles();
    
    Optional<Student> getModifyStudent();
    
    public default Optional<StudentOption> getModifyOption(){
        if(!getModifyStudent().isPresent()){
            return Optional.empty();
        }
        Student student = getModifyStudent().get();
        return Main.getEnteredOptions().stream().filter(o -> o.getStudent().getId() == student.getId()).findAny();
    }
    
    public default List<Role> getRoles(){
        List<Role> roles = new ArrayList<>();
        getSelectedRoles().stream().forEach(s -> getChoosableRoles().stream().filter(c -> c.getDisplayName().equals(s)).forEach(c -> roles.add(c)));
        getChoosableRoles().stream().filter(c -> !roles.stream().anyMatch(r -> r.equals(c))).forEach(c -> roles.add(c));
        return roles;
    }

    public default Student createStudent() throws SQLException {
        return new Student(getFirstName(), getLastName(), false);
    }

    public default StudentOption createOption() throws SQLException {
        return createOption(createStudent());
    }

    public default StudentOption createOption(Student student) throws SQLException {
        return new StudentOption(student, getRoles());
    }
        
    public default StudentOption updateOption(){
        StudentOption option = getModifyOption().get();
        option.getRoles().clear();
        option.getRoles().addAll(getRoles());
        return option;
    }

}
