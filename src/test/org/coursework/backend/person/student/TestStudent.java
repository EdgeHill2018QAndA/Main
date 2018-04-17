package test.org.coursework.backend.person.student;

import static org.junit.Assert.assertEquals;

import org.coursework.backend.person.AbstractPerson;
import org.coursework.backend.person.Permission;
import org.coursework.backend.person.student.Student;
import org.junit.Test;

public class TestStudent {
	
	@Test
	public void testGetId() {
		AbstractPerson person = new Student(5, "Test", "One");
		assertEquals(5, person.getId());
	}
	
	@Test
	public void testGetFirstName() {
		AbstractPerson person = new Student(5, "Test", "One");
		assertEquals("Test", person.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		AbstractPerson person = new Student(5, "Test", "One");
		assertEquals("One", person.getLastName());
	}
	
	@Test
	public void testGetFullName() {
		AbstractPerson person = new Student(5, "Test", "One");
		assertEquals("Test One", person.getName());
	}
	
	@Test
	public void testGetPermission() {
		AbstractPerson person = new Student(5, "Test", "One");
		assertEquals(Permission.STUDENT, person.getPermission());
	}

}
