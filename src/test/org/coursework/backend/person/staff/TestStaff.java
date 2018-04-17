package test.org.coursework.backend.person.staff;

import static org.junit.Assert.assertEquals;

import org.coursework.backend.person.AbstractPerson;
import org.coursework.backend.person.Permission;
import org.coursework.backend.person.staff.Staff;
import org.junit.Test;

public class TestStaff {
	
	@Test
	public void testGetId() {
		AbstractPerson person = new Staff(5, "Test", "One");
		assertEquals(5, person.getId());
	}
	
	@Test
	public void testGetFirstName() {
		AbstractPerson person = new Staff(5, "Test", "One");
		assertEquals("Test", person.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		AbstractPerson person = new Staff(5, "Test", "One");
		assertEquals("One", person.getLastName());
	}
	
	@Test
	public void testGetFullName() {
		AbstractPerson person = new Staff(5, "Test", "One");
		assertEquals("Test One", person.getName());
	}
	
	@Test
	public void testGetPermission() {
		AbstractPerson person = new Staff(5, "Test", "One");
		assertEquals(Permission.ADMIN, person.getPermission());
	}

}
