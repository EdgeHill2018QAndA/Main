package test.org.coursework.backend.person.student;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.roles.Role;
import org.junit.Test;

public class TestStudentOption {
	
	@Test
	public void testGetId() {
		Student student = new Student(5, "Test", "One");
		Role roleOne = new Role(6, "Test Role");
		StudentOption option = new StudentOption(3, student, roleOne);
		assertEquals(3, option.getId());
	}
	
	@Test
	public void testGetStudent() {
		Student student = new Student(5, "Test", "One");
		Role roleOne = new Role(6, "Test Role");
		StudentOption option = new StudentOption(3, student, roleOne);
		assertEquals(student, option.getStudent());
	}
	
	@Test
	public void testGetPreferredRole() {
		Student student = new Student(5, "Test", "One");
		Role roleOne = new Role(6, "Test Role");
		Role roleTwo = new Role(3, "Test Role 2");
		StudentOption option = new StudentOption(3, student, roleOne, roleTwo);
		if(!option.getPreferredRole().isPresent()) {
			fail();
		}
		assertEquals(roleOne, option.getPreferredRole().get());
	}
	
	@Test
	public void testGetRoles() {
		Student student = new Student(5, "Test", "One");
		Role roleOne = new Role(6, "Test Role");
		Role roleTwo = new Role(3, "Test Role 2");
		StudentOption option = new StudentOption(3, student, roleOne, roleTwo);
		Role[] roles = {roleOne, roleTwo};
		Role[] roles2 = new Role[option.getRoles().size()];
		option.getRoles().toArray(roles2);
		assertArrayEquals(roles, roles2);
	}
	
	@Test
	public void testRegister() {
		Student student = new Student(5, "Test", "One");
		Role roleOne = new Role(6, "Test Role");
		Role roleTwo = new Role(3, "Test Role 2");
		StudentOption option = new StudentOption(3, student);
		option.registerRole(roleOne, roleTwo);
		Role[] roles = {roleOne, roleTwo};
		Role[] roles2 = new Role[option.getRoles().size()];
		option.getRoles().toArray(roles2);
		assertArrayEquals(roles, roles2);
	}
	
	@Test
	public void testDeregister() {
		Student student = new Student(5, "Test", "One");
		Role roleOne = new Role(6, "Test Role");
		Role roleTwo = new Role(3, "Test Role 2");
		StudentOption option = new StudentOption(3, student);
		option.registerRole(roleOne, roleTwo);
		option.deregisterRole(roleOne, roleTwo);
		assertEquals(0, option.getRoles().size());
	}

}
