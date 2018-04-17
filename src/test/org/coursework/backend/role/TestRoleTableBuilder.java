package test.org.coursework.backend.role;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.coursework.Main;
import org.coursework.backend.roles.Role;
import org.coursework.backend.roles.RoleTableBuilder;
import org.junit.Test;

public class TestRoleTableBuilder {
	
	@Test
	public void testGetId() {
		RoleTableBuilder builder = new RoleTableBuilder();
		assertEquals(RoleTableBuilder.TABLE_NAME, builder.getTableName());
	}
	
	@Test
	public void testGetTableColumns() {
		RoleTableBuilder builder = new RoleTableBuilder();
		String[] columns = {"id", "name"};
		assertArrayEquals(columns, builder.getTableColumns());
	}
	
	@Test
	public void testGetTableColumnSQLWithID() {
		RoleTableBuilder builder = new RoleTableBuilder();
		assertEquals("id INTEGER not NULL", builder.getTableColumnSQL("id"));
	}
	
	@Test
	public void testGetTableColumnSQLWithName() {
		RoleTableBuilder builder = new RoleTableBuilder();
		assertEquals("name VARCHAR(255)", builder.getTableColumnSQL("name"));
	}
	
	@Test
	public void testGetTableColumnSQLWithBlank() {
		RoleTableBuilder builder = new RoleTableBuilder();
		assertEquals(null, builder.getTableColumnSQL(""));
	}
	
	@Test
	public void testRegisterWithMainArray() {
		RoleTableBuilder builder = new RoleTableBuilder();
		Role roleOne = new Role(5, "Role One");
		Role roleTwo = new Role(6, "Role Two");
		builder.registerWithMain(roleOne, roleTwo);
		assertEquals(2, Main.getRoles().size());
		Main.deregister(roleOne, roleTwo);
	}
	
	@Test
	public void testRegisterWithMainCollection() {
		RoleTableBuilder builder = new RoleTableBuilder();
		Role roleOne = new Role(5, "Role One");
		Role roleTwo = new Role(6, "Role Two");
		List<Role> list = new ArrayList<>();
		list.add(roleOne);
		list.add(roleTwo);
		builder.registerWithMain(list);
		assertEquals(2, Main.getRoles().size());
		Main.deregister(roleOne, roleTwo);
	}
	
	@Test
	public void testGetDataFromMain() {
		RoleTableBuilder builder = new RoleTableBuilder();
		Role roleOne = new Role(5, "Role One");
		Role roleTwo = new Role(6, "Role Two");
		builder.registerWithMain(roleOne, roleTwo);
		assertEquals(2, builder.getDataFromMain().size());
		Main.deregister(roleOne, roleTwo);
	}
	
	@Test
	public void testToArray() {
		RoleTableBuilder builder = new RoleTableBuilder();
		Role[] roles = new Role[5];
		assertArrayEquals(roles, builder.toArray(5));
	}

}
