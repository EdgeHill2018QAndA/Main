package org.coursework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.coursework.backend.group.GroupSort;
import org.coursework.backend.person.Person;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;
import org.coursework.database.core.CoreDatabaseLink;

public class Main {
	
	private static final List<Person> PEOPLE = new ArrayList<>();
	private static final List<Role> ROLES = new ArrayList<>();
	private static final List<StudentOption> ENTERED_OPTIONS = new ArrayList<>();
	
	private static int recommendedGroupSize = 5;
	private static int minRolesSize = 1;
	private static int maxRolesSize = 5;
	private static GroupSort sorter = new GroupSort();
	private static CoreDatabaseLink coreDatabaseLink;
	private static Person loggedInAs;
	
	public static Optional<Person> getLoggedInAs() {
		return Optional.ofNullable(loggedInAs);
	}
	
	public static void setLoggedInAs(Person person) {
		loggedInAs = person;
	}
	
	public static Optional<CoreDatabaseLink> getDatabaseLink(){
		return Optional.ofNullable(coreDatabaseLink);
	}
	
	public static void setDatabaseLink(CoreDatabaseLink link) {
		coreDatabaseLink = link;
	}
	
	public static GroupSort getGroupSort() {
		return sorter;
	}
	
	public static int getRecommendedGroupSize() {
		return recommendedGroupSize;
	}
	
	public static boolean setRecommendedGroupSize(int size) {
		if(size > 1) {
			return false;
		}
		recommendedGroupSize = size;
		return true;
	}
	
	public static int getMaxRoleSize() {
		return maxRolesSize;
	}
	
	public static boolean setMaxRoleSize(int size) {
		if(size > ROLES.size()) {
			return false;
		}
		if(size < 1) {
			return false;
		}
		maxRolesSize = size;
		return true;
	}
	
	public static int getMinRoleSize() {
		return minRolesSize;
	}
	
	public static boolean setMinRoleSize(int size) {
		if(size > ROLES.size()) {
			return false;
		}
		if(size < 1) {
			return false;
		}
		minRolesSize = size;
		return true;
	}
	
	public static Set<Person> getPeople() {
		return new HashSet<>(PEOPLE);
	}
	
	public static Set<Role> getRoles(){
		return new HashSet<>(ROLES);
	}
	
	public static Set<StudentOption> getEnteredOptions(){
		return new HashSet<>(ENTERED_OPTIONS);
	}
	
	public static void register(Person... people) {
		for(Person person : people) {
			PEOPLE.add(person);
		}
	}
	
	public static void register(Role... roles) {
		for(Role role : roles) {
			ROLES.add(role);
		}
	}	
	
	public static void register(StudentOption... options) {
		for(StudentOption option : options) {
			ENTERED_OPTIONS.add(option);
		}
	}
	
	public static void deregister(Person... people) {
		for(Person person : people) {
			PEOPLE.remove(person);
		}
	}
	
	public static void deregister(Role... roles) {
		for(Role role : roles) {
			ROLES.remove(role);
		}
	}	
	
	public static void deregister(StudentOption... options) {
		for(StudentOption option : options) {
			ENTERED_OPTIONS.remove(option);
		}
	}
	
	public static void main(String[] args) {
		
	}

}
