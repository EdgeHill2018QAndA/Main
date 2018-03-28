package org.coursework.frontend.base.people;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;

public class BaseStudent extends BasePerson {
	
	public BaseStudent(Collection<Role> choosable) {
		super(choosable);
	}

	public List<String> getSelectedRoles(){
		return selectedRoles;
	}
	
	public Collection<Role> getChoosableRoles(){
		return choosableRoles;
	}
	
	public Student createStudent() throws SQLException {
		return new Student(getFirstName(), getLastName());
	}
	
	public StudentOption createOption() throws SQLException {
		return createOption(createStudent());
	}
	
	public StudentOption createOption(Student student) {
		List<Role> roles = new ArrayList<>();
		selectedRoles.stream().forEach(s -> choosableRoles.stream().filter(c -> c.getDisplayName().equals(s)).forEach(c -> roles.add(c)));
		choosableRoles.stream().filter(c -> !roles.stream().anyMatch(r -> r.equals(c))).forEach(c -> roles.add(c));
		return new StudentOption(student, roles);
	}

}
