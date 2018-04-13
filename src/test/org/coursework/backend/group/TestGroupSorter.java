package test.org.coursework.backend.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import org.coursework.Main;
import org.coursework.backend.group.Group;
import org.coursework.backend.group.GroupSort;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;
import org.coursework.database.core.CoreDatabaseLink;
import org.junit.Test;

public class TestGroupSorter {
	
	private static CoreDatabaseLink link = new CoreDatabaseLink("jdbc:mysql://localhost:3306/test", "root", "Patmoseley2");
	
	@Test
	public void testGroupSortNoArgument() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Role roleThree = new Role(3, "RoleThree");
		Student studentOne = new Student(4, "Test", "One");
		Student studentTwo = new Student(5, "Test", "Two");
		Student studentThree = new Student(6, "Test", "Three");
		StudentOption soOne = new StudentOption(2, studentOne, roleTwo);
		StudentOption soTwo = new StudentOption(3, studentTwo, roleOne);
		StudentOption soThree = new StudentOption(4, studentThree, roleThree);
		Main.register(soOne, soTwo, soThree);
		GroupSort sort = new GroupSort();
		assertEquals(true, sort.getOptions().stream().allMatch(s -> s.equals(soOne) || s.equals(soTwo) || s.equals(soThree)));
	}
	
	@Test
	public void testGroupSortArrayArgument() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Role roleThree = new Role(3, "RoleThree");
		Student studentOne = new Student(4, "Test", "One");
		Student studentTwo = new Student(5, "Test", "Two");
		Student studentThree = new Student(6, "Test", "Three");
		StudentOption soOne = new StudentOption(2, studentOne, roleTwo);
		StudentOption soTwo = new StudentOption(3, studentTwo, roleOne);
		StudentOption soThree = new StudentOption(4, studentThree, roleThree);
		GroupSort sort = new GroupSort(soOne, soTwo, soThree);
		assertEquals(true, sort.getOptions().stream().allMatch(s -> s.equals(soOne) || s.equals(soTwo) || s.equals(soThree)));
	}
	
	@Test
	public void testGroupSortCollectionArgument() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Role roleThree = new Role(3, "RoleThree");
		Student studentOne = new Student(4, "Test", "One");
		Student studentTwo = new Student(5, "Test", "Two");
		Student studentThree = new Student(6, "Test", "Three");
		StudentOption soOne = new StudentOption(2, studentOne, roleTwo);
		StudentOption soTwo = new StudentOption(3, studentTwo, roleOne);
		StudentOption soThree = new StudentOption(4, studentThree, roleThree);
		
		ArrayList<StudentOption> list = new ArrayList<>();
		list.add(soOne);
		list.add(soTwo);
		list.add(soThree);
		
		GroupSort sort = new GroupSort(list);
		assertEquals(true, sort.getOptions().stream().allMatch(s -> s.equals(soOne) || s.equals(soTwo) || s.equals(soThree)));
	}
	
	@Test
	public void testSortNoArgument() {
		//setup
		Main.setDatabaseLink(link);
		link.loadDriver();
		try {
			link.openConnection();
		} catch (SQLException e) {
			fail();
		}
		
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Role roleThree = new Role(3, "RoleThree");
		Student studentOne = new Student(4, "Test", "One");
		Student studentTwo = new Student(5, "Test", "Two");
		Student studentThree = new Student(6, "Test", "Three");
		StudentOption soOne = new StudentOption(2, studentOne, roleTwo);
		StudentOption soTwo = new StudentOption(3, studentTwo, roleOne);
		StudentOption soThree = new StudentOption(4, studentThree, roleThree);
				
		Main.setRecommendedGroupSize(2);
		GroupSort sort = new GroupSort(soOne, soTwo, soThree);
		Set<Group> groups = sort.sortGroups();
		assertEquals(2, groups.size());
	}
	
	@Test
	public void testSortArrayArgument() {
		//setup
		Main.setDatabaseLink(link);
		link.loadDriver();
		try {
			link.openConnection();
		} catch (SQLException e) {
			fail();
		}
		
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Role roleThree = new Role(3, "RoleThree");
		Student studentOne = new Student(4, "Test", "One");
		Student studentTwo = new Student(5, "Test", "Two");
		Student studentThree = new Student(6, "Test", "Three");
		StudentOption soOne = new StudentOption(2, studentOne, roleTwo);
		StudentOption soTwo = new StudentOption(3, studentTwo, roleOne);
		StudentOption soThree = new StudentOption(4, studentThree, roleThree);
				
		Main.setRecommendedGroupSize(2);
		GroupSort sort = new GroupSort(soOne, soTwo, soThree);
		Set<Group> groups = sort.sortGroups(roleOne, roleThree);
		assertEquals(2, groups.size());
	}
	
	
	

}
