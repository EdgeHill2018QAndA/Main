package test.org.coursework.backend.group;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.coursework.backend.group.Group;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;
import org.junit.Test;

public class TestGroup {
	
	@Test
	public void testGetId() {
		Group group = new Group(5);
		assertEquals(5, group.getId());
	}
	
	@Test
	public void testGetOptionsSize() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3, soOne, soTwo);
		assertEquals(2, group.getOptions().size());
	}
	
	@Test
	public void testGetOptionsContains() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3, soOne, soTwo);
		assertEquals(true, group.getOptions().stream().allMatch(s -> s.equals(soOne) || s.equals(soTwo)));
	}
	
	@Test
	public void testGetStudentsSize() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3, soOne, soTwo);
		assertEquals(2, group.getStudents().size());
	}
	
	@Test
	public void testGetStudentsContains() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3, soOne, soTwo);
		assertEquals(true, group.getStudents().stream().allMatch(s -> s.equals(studentOne) || s.equals(studentTwo)));
	}
	
	@Test
	public void testRegister() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3);
		group.register(soOne, soTwo);
		assertEquals(2, group.getOptions().size());
	}
	
	@Test
	public void testRegisterBlank() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3, soOne, soTwo);
		group.register();
		assertEquals(2, group.getOptions().size());
	}
	
	@Test
	public void testSwapOneStudents() {
		Role roleOne = new Role(1, "RoleOne");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleOne);
		Group group = new Group(3, soOne);
		if (!group.swap(soTwo)) {
			fail();
		}
		assertEquals(true, group.getOptions().stream().allMatch(s -> s.equals(soTwo)));
	}
	
	@Test
	public void testSwapTwoStudents() {
		Role roleOne = new Role(1, "RoleOne");
		Role roleTwo = new Role(2, "RoleTwo");
		Student studentOne = new Student(6, "Test", "One");
		Student studentTwo = new Student(4, "Test", "Two");
		StudentOption soOne = new StudentOption(6, studentOne, roleOne, roleTwo);
		StudentOption soTwo = new StudentOption(4, studentTwo, roleTwo, roleOne);
		Group group = new Group(3, soOne);
		if (!group.swap(soTwo, soOne)) {
			fail();
		}
		assertEquals(true, group.getOptions().stream().allMatch(s -> s.equals(soTwo)));
	}

}
