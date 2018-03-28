package org.coursework.frontend.base.groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursework.backend.group.Group;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;

public class BaseGroup {
	
	List<Group> groups;
	
	/**
	 * gets all groups that are being displayed 
	 */
	public Set<Group> getGroups(){
		return new HashSet<>(groups);
	}
	
	/**
	 * swap 2 students from different groups that are being displayed.
	 * 
	 */
	public void swap(Student origin, Student other) {
		Group gOrigin = groups.stream().filter(g -> g.getStudents().contains(origin)).findFirst().get();
		Group gOther = groups.stream().filter(g -> g.getStudents().contains(other)).findFirst().get();
		StudentOption oOrigin = gOrigin.getOptions().stream().filter(o -> o.getStudent().equals(origin)).findFirst().get();
		StudentOption oOther = gOther.getOptions().stream().filter(o -> o.getStudent().equals(other)).findFirst().get();
		gOrigin.swap(oOther, oOrigin);
		gOther.swap(oOrigin, oOther);
	}

}
