package org.coursework.backend.group;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.coursework.Main;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;

public class GroupSort {
	
	List<StudentOption> options = new ArrayList<>();
	Set<Group> groups;
	
	public GroupSort(StudentOption... arr) {
		this(Arrays.asList(arr));
	}
	
	public GroupSort(Collection<StudentOption> coll) {
		options.addAll(coll);
	}
	
	public Set<StudentOption> getOptions(){
		return new HashSet<>(options);
	}
	
	public Set<Group> sortGroups(Role... groupsMustHave){
		return sortGroups(Arrays.asList(groupsMustHave));
	}
	
	public Set<Group> sortGroups(Collection<Role> groupsMustHave){
		List<Group> groups = new ArrayList<>();
		while(options.size() >= Main.getRecommendedGroupSize()) {
			Group group = createGroup(groupsMustHave);
			groups.add(group);
		}
		return new HashSet<>(groups);
	}
	
	public Group createGroup(Collection<Role> groupMustHave) {
		List<StudentOption> list = new ArrayList<>();
		int groupSize = Main.getRecommendedGroupSize();
		for(int A = 0; A < groupSize; A++) {
			if(groupMustHave.isEmpty()) {
				StudentOption student = pickRandom(getAppropeateRoles(list));
				list.add(student);
				this.options.remove(student);
			}else {
				StudentOption student = pickRandom(getPreferredNeededStudents(list, groupMustHave));
				list.add(student);
				this.options.remove(student);
			}
		}
		return new Group(list);
	}
	
	private StudentOption pickRandom(List<StudentOption> collection){
		Random random = new Random();
		int randomNum = random.nextInt(collection.size());
		return collection.get(randomNum);
	}
	
	private List<StudentOption> getPreferredNeededStudents(Collection<StudentOption> options, Collection<Role> required){
		Set<Role> rolesLeft = required.stream().filter(o -> !options.stream().anyMatch(s -> s.getPreferredRole().get().equals(o))).collect(Collectors.toSet());
		return this.options.stream().filter(o -> rolesLeft.stream().anyMatch(r -> r.equals(o.getPreferredRole().get()))).collect(Collectors.toList());
	}
	
	private List<StudentOption> getAppropeateRoles(Collection<StudentOption> assigned){
		return this.options.stream().filter(s -> !assigned.stream().anyMatch(o -> o.getPreferredRole().get().equals(s.getPreferredRole().get()))).collect(Collectors.toList());
	}
	

}
