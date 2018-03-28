package org.coursework.backend.group;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;

public class Group {

	List<StudentOption> options;
	
	public Group(Collection<StudentOption> options) {
		this.options.addAll(options);
	}
	
	public Set<StudentOption> getOptions(){
		return new HashSet<>(options);
	}
	
	public Set<Student> getStudents(){
		List<Student> students = new ArrayList<>();
		options.stream().forEach(o -> students.add(o.getStudent()));
		return new HashSet<>(students);
	}
	
	public boolean swap(StudentOption newStudent) {
		return swap(newStudent, options.stream().filter(s -> newStudent.getPreferredRole().get().equals(s.getPreferredRole().get())).findFirst().get());
	}
	
	public boolean swap(StudentOption newStudent, StudentOption with) {
		if(!options.contains(with)) {
			return false;
		}
		options.remove(with);
		options.add(newStudent);
		return true;
	}
	
}
