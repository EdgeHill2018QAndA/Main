package test.org.coursework.backend.group;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.coursework.Main;
import org.coursework.backend.group.Group;
import org.coursework.backend.group.GroupTableBuilder;
import org.junit.Test;

public class TestGroupTableBuilder {
	
	@Test
	public void testGetTableName() {
		GroupTableBuilder builder = new GroupTableBuilder();
		assertEquals("GROUPS", builder.getTableName());
	}
	
	@Test
	public void testGetTableColumns() {
		GroupTableBuilder builder = new GroupTableBuilder();
		String[] array = new String[]{"id"};
		assertArrayEquals(array, builder.getTableColumns());
	}
	
	@Test
	public void testGetTableColumnSQLNoParam() {
		GroupTableBuilder builder = new GroupTableBuilder();
		assertEquals("id INTEGER not NULL", builder.getTableColumnSQL(null));
	}
	
	@Test
	public void testGetTableColumnSQLRandomParam() {
		GroupTableBuilder builder = new GroupTableBuilder();
		assertEquals("id INTEGER not NULL", builder.getTableColumnSQL("TEST"));
	}
	
	@Test
	public void testRegisterWithMainWithArray() {
		List<Group> clear = new ArrayList<>();
		Main.setGroups(clear);
		Group groupOne = new Group(5);
		Group groupTwo = new Group(6);
		GroupTableBuilder builder = new GroupTableBuilder();
		builder.registerWithMain(groupOne, groupTwo);
		assertEquals(2, Main.getGroups().size());
	}
	
	@Test
	public void testRegisterWithMainWithCollection() {
		List<Group> clear = new ArrayList<>();
		Main.setGroups(clear);
		Group groupOne = new Group(5);
		Group groupTwo = new Group(6);
		GroupTableBuilder builder = new GroupTableBuilder();
		List<Group> groups = new ArrayList<>();
		groups.add(groupOne);
		groups.add(groupTwo);
		builder.registerWithMain(groups);
		assertEquals(2, Main.getGroups().size());
	}

}
