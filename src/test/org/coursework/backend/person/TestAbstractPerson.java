package test.org.coursework.backend.person;

import static org.junit.Assert.assertEquals;

import org.coursework.backend.person.AbstractPerson;
import org.coursework.backend.person.Permission;
import org.junit.Test;

public class TestAbstractPerson {
	
	@Test
	public void testGetId() {
		AbstractPerson person = new AbstractPerson(5, "Test", "One") {

			@Override
			public Permission getPermission() {
				return Permission.STUDENT;
			}
			
		};
		assertEquals(5, person.getId());
	}
	
	@Test
	public void testGetFirstName() {
		AbstractPerson person = new AbstractPerson(5, "Test", "One") {

			@Override
			public Permission getPermission() {
				return Permission.STUDENT;
			}
			
		};
		assertEquals("Test", person.getFirstName());
	}
	
	@Test
	public void testGetLastName() {
		AbstractPerson person = new AbstractPerson(5, "Test", "One") {

			@Override
			public Permission getPermission() {
				return Permission.STUDENT;
			}
			
		};
		assertEquals("One", person.getLastName());
	}
	
	@Test
	public void testGetFullName() {
		AbstractPerson person = new AbstractPerson(5, "Test", "One") {

			@Override
			public Permission getPermission() {
				return Permission.STUDENT;
			}
			
		};
		assertEquals("Test One", person.getName());
	}
	
	@Test
	public void testGetPermission() {
		AbstractPerson person = new AbstractPerson(5, "Test", "One") {

			@Override
			public Permission getPermission() {
				return Permission.STUDENT;
			}
			
		};
		assertEquals(Permission.STUDENT, person.getPermission());
	}

}
