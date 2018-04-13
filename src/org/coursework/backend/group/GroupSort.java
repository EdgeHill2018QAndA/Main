package org.coursework.backend.group;

import java.sql.SQLException;
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
    //Set<Group> groups;

    public GroupSort() {
        this(Main.getEnteredOptions());
    }

    /**
     * Creates a instance of a groupsorter
     *
     * @param arr adds the students into the list of sorting students. Can be
     * not specified.
     */
    public GroupSort(StudentOption... arr) {
        this(Arrays.asList(arr));
    }

    /**
     * Creates a instance of a groupsorter
     *
     * @param coll adds the students into the list of sorting students.
     */
    public GroupSort(Collection<StudentOption> coll) {
        options.addAll(coll);
    }

    /**
     * gets every single student for the group sorting algorithm and the options
     * they have chosen.
     *
     * @return A set of all students with there options
     */
    public Set<StudentOption> getOptions() {
        return new HashSet<>(options);
    }

    /**
     * This will sort the students into groups aiming for the size specified in
     *
     * @class[Main].@function[RecommendedGroupSize()];
     * @param groupsMustHave If you wish to have the groups have at least a
     * single member having a specific role then you must state it here, this
     * can be not specified if needed.
     * @return A set of sorted groups with each group having the roles
     * specified.
     */
    public Set<Group> sortGroups(Role... groupsMustHave) {
        return sortGroups(Arrays.asList(groupsMustHave));
    }

    /**
     * This will sort the students into groups aiming for the size specified in
     *
     * @class[Main].@function[RecommendedGroupSize()];
     * @param groupsMustHave If you wish to have the groups have at least a
     * single member having a specific role then you must state it here.
     * @return A set of sorted groups with each group having the roles
     * specified.
     */
    public Set<Group> sortGroups(Collection<Role> groupsMustHave) {
        System.out.println("Sorting Groups");
        List<Group> groups = new ArrayList<>();
        while (!options.isEmpty()) {
            System.out.println("On While");
            try {
                Group group = createGroup(groupsMustHave);
                groups.add(group);
            } catch (SQLException e) {
                System.err.println("Failed to create group");
            }
        }
        return new HashSet<>(groups);
    }

    //Creates a single group and marks the students used in the group, so not to use them again.
    private Group createGroup(Collection<Role> groupMustHave) throws SQLException {
        List<StudentOption> list = new ArrayList<>();
        int groupSize = Main.getRecommendedGroupSize();
        System.out.println("Group Size: " + groupSize);
        for (int A = 0; A < groupSize; A++) {
            List<StudentOption> options;
            if (groupMustHave.isEmpty()) {
                options = (getAppropeateRoles(list));
            } else {
                options = getPreferredNeededStudents(list, groupMustHave);
                if(options.isEmpty()) {
                	options = getNoneNeededRoleStudents(options, groupMustHave);
                }
            }
            if (options.isEmpty()) {
            	break;
            }
            System.out.println("Possible options: " + options.size());
            StudentOption student = pickRandom(options);
            System.out.println("picked: " + student.getStudent().getName());
            list.add(student);
            this.options.remove(student);
        }
        if(list.isEmpty()) {
        	return null;
        }
        return new Group(list);
    }

    //picks a random object out of the specified collection of objects
    private <T extends Object> T pickRandom(List<T> collection) {
        Random random = new Random();
        int randomNum = random.nextInt(collection.size());
        return collection.get(randomNum);
    }

    //gets all possible students that would best fit inside the group based on the roles required and the students already in the group
    private List<StudentOption> getPreferredNeededStudents(Collection<StudentOption> options, Collection<Role> required){
        Set<Role> rolesLeft = required.stream().filter(o -> !options.stream().anyMatch(s -> s.getPreferredRole().get().equals(o))).collect(Collectors.toSet());
        int firstRoleFound = Integer.MAX_VALUE;
        List<StudentOption> ret = new ArrayList<>();
        for(StudentOption option : this.options) {
            int B = option.getRoles().size();
            if(firstRoleFound < B) {
            	B = firstRoleFound;
            }
        	for(int A = 0; A <= B; A++) {
        		if(option.getRoles().size() == A) {
        			break;
        		}
        		Role role = option.getRoles().get(A);
        		if(rolesLeft.contains(role)) {
        			if(A < B) {
        				ret = new ArrayList<>();
            			B = A;
        			}
        			ret.add(option);
        		}
        	}
        }
        return ret;
    }
    
    //gets all the students that do not match any of the required roles
    private List<StudentOption> getNoneNeededRoleStudents(Collection<StudentOption> options, Collection<Role> required) {
    	Set<Role> rolesLeft = required.stream().filter(o -> !options.stream().anyMatch(s -> s.getPreferredRole().get().equals(o))).collect(Collectors.toSet());
    	return this.options.stream().filter(o -> !rolesLeft.stream().anyMatch(r -> o.getRoles().contains(r))).collect(Collectors.toList());
    }

    //gets all possible students that have a preferred role that is not in the assigned collection
    private List<StudentOption> getAppropeateRoles(Collection<StudentOption> assigned) {
        return this.options.stream().filter(s -> !assigned.stream().anyMatch(o -> o.getPreferredRole().get().equals(s.getPreferredRole().get()))).collect(Collectors.toList());
    }
    
}
