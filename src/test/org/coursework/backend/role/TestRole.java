package test.org.coursework.backend.role;

import static org.junit.Assert.assertEquals;

import org.coursework.backend.roles.Role;
import org.junit.Test;

public class TestRole {
	
	@Test
	public void testGetId() {
		Role role = new Role(5, "Test Role");
		assertEquals(5, role.getId());
	}
	
	@Test
	public void testGetDisplayName() {
		Role role = new Role(5, "Test Role");
		assertEquals("Test Role", role.getDisplayName());
	}
	
	@Test
	public void testSetDisplayName() {
		Role role = new Role(5, "Test Role");
		role.setDisplayName("Test Role 2");
		assertEquals("Test Role 2", role.getDisplayName());
	}
	
	@Test
	public void testEquals() {
		Role role = new Role(5, "Test Role");
		Role testRole = new Role(3, "Test Role");
		assertEquals(true, role.equals(testRole));
	}
	
	@Test
	public void testToString() {
		Role role = new Role(5, "Test Role");
		assertEquals("Test Role", role.toString());
	}

}
