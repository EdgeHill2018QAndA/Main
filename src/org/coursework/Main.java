package org.coursework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.coursework.backend.group.GroupSort;
import org.coursework.backend.person.Person;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;

public class Main {
	
	private static final List<Person> PEOPLE = new ArrayList<>();
	private static final List<Role> ROLES = new ArrayList<>();
	private static final List<StudentOption> ENTERED_OPTIONS = new ArrayList<>();
	
	private static int RECOMMENDED_GROUP_SIZE = 5;
	private static int MIN_ROLES_SIZE = 1;
	private static int MAX_ROLES_SIZE = 5;
	private static GroupSort SORTER = new GroupSort();
	
	public static GroupSort getGroupSort() {
		return SORTER;
	}
	
	public static int getRecommendedGroupSize() {
		return RECOMMENDED_GROUP_SIZE;
	}
	
	public static boolean setRecommendedGroupSize(int size) {
		if(size > 1) {
			return false;
		}
		RECOMMENDED_GROUP_SIZE = size;
		return true;
	}
	
	public static int getMaxRoleSize() {
		return MAX_ROLES_SIZE;
	}
	
	public static boolean setMaxRoleSize(int size) {
		if(size > ROLES.size()) {
			return false;
		}
		if(size < 1) {
			return false;
		}
		MAX_ROLES_SIZE = size;
		return true;
	}
	
	public static int getMinRoleSize() {
		return MIN_ROLES_SIZE;
	}
	
	public static boolean setMinRoleSize(int size) {
		if(size > ROLES.size()) {
			return false;
		}
		if(size < 1) {
			return false;
		}
		MIN_ROLES_SIZE = size;
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
