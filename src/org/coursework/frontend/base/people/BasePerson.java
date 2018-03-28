package org.coursework.frontend.base.people;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.coursework.backend.roles.Role;

public class BasePerson {
	
	String firstName;
	String lastName;
	List<String> selectedRoles = new ArrayList<>();
	Collection<Role> choosableRoles;
	
	public BasePerson(Collection<Role> choosable) {
		choosableRoles = choosable; 
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public BasePerson setFirstName(String name) {
		firstName = name;
		return this;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public BasePerson setLastName(String name) {
		lastName = name;
		return this;
	}

}
