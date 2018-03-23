package org.coursework.frontend.base.login;

import java.util.Optional;

import org.coursework.Main;
import org.coursework.backend.person.Person;

public class BaseLogin {
	
	String firstName = "";
	String lastName = "";
	
	public void setFirstName(String name) {
		this.firstName = name;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public void setLastName(String name) {
		this.lastName = name;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public Optional<Person> attemptAuthorization() {
		return Main.getPeople().stream().filter(p -> p.getFirstName().equalsIgnoreCase(firstName) && p.getLastName().equalsIgnoreCase(lastName)).findFirst();
	}
	
	public void forceAuthorization(Person person) {
		Main.setLoggedInAs(person);
	}

}
