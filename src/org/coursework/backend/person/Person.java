package org.coursework.backend.person;

public interface Person {
	
	public String getFirstName();
	public String getLastName();
	
	public Permission getPermission();
	
	public default String getName() {
		return getFirstName() + " " + getLastName();
	}

}
