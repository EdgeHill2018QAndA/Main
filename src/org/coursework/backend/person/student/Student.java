package org.coursework.backend.person.student;

import org.coursework.backend.person.Permission;
import org.coursework.backend.person.Person;

public class Student implements Person {
	
	String firstName;
	String lastName;
	
	public Student(String first, String last) {
		this.firstName = first;
		this.lastName = last;
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}
	
	@Override
	public String getLastName() {
		return lastName;
	}
	
	@Override
	public Permission getPermission() {
		return Permission.STUDENT;
	}

}
