package org.coursework.backend.person.student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.coursework.backend.roles.Role;

public class StudentOption {
	
	Student student;
	List<Role> roles = new ArrayList<>();
	
	public Student getStudent() {
		return student;
	}
	
	public Optional<Role> getPreferredRole() {
		if(roles.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(roles.get(0));
	}
	
	public Set<Role> getRoles(){
		return new HashSet<>(roles);
	}
	
	public void registerRole(Role... roles) {
		for(Role role : roles) {
			this.roles.add(role);
		}
	}
	
	public void deregisterRole(Role... roles) {
		for(Role role : roles) {
			this.roles.remove(role);
		}
	}
	
	

}
