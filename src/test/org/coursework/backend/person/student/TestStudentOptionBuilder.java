package test.org.coursework.backend.person.student;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.coursework.Main;
import org.coursework.backend.person.student.Student;
import org.coursework.backend.person.student.StudentOption;
import org.coursework.backend.person.student.StudentOptionTableBuilder;
import org.junit.Test;

public class TestStudentOptionBuilder {
	
	@Test
	public void testGetTableName() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("STUDENTOPTIONS", builder.getTableName());
	}
	
	@Test
	public void testGetTableColumns() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		String[] columns = new String[] {"id", "studentid", "groupid", "role0", "role1", "role2", "role3", "role4", "role5", "role6", "role7", "role8"};
		assertArrayEquals(columns, builder.getTableColumns());
	}
	
	@Test
	public void testGetTableColumnSQLWithId() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("id INTEGER not NULL", builder.getTableColumnSQL("id"));
	}
	
	@Test
	public void testGetTableColumnSQLWithStudentId() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("studentid INTEGER not NULL", builder.getTableColumnSQL("studentid"));
	}
	
	@Test
	public void testGetTableColumnSQLWithGroupId() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("groupid INTEGER", builder.getTableColumnSQL("groupid"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleZero() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role0 INTEGER", builder.getTableColumnSQL("role0"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleOne() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role1 INTEGER", builder.getTableColumnSQL("role1"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleTwo() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role2 INTEGER", builder.getTableColumnSQL("role2"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleThree() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role3 INTEGER", builder.getTableColumnSQL("role3"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleFour() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role4 INTEGER", builder.getTableColumnSQL("role4"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleFive() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role5 INTEGER", builder.getTableColumnSQL("role5"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleSix() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role6 INTEGER", builder.getTableColumnSQL("role6"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleSeven() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role7 INTEGER", builder.getTableColumnSQL("role7"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRoleEight() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		assertEquals("role8 INTEGER", builder.getTableColumnSQL("role8"));
	}
	
	@Test
	public void testRegisterWithMainArray() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		Student studentOne = new Student(0, "Test", "One");
		Student studentTwo = new Student(1, "Test", "Two");
		Student studentThree = new Student(2, "Test", "Three");
		StudentOption soOne = new StudentOption(3, studentOne);
		StudentOption soTwo = new StudentOption(4, studentTwo);
		StudentOption soThree = new StudentOption(5, studentThree);
		builder.registerWithMain(soOne, soTwo, soThree);
		assertEquals(3, Main.getEnteredOptions().size());
		Main.deregister(soOne, soTwo, soThree);
	}
	
	@Test
	public void testRegisterWithMainCollection() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		Student studentOne = new Student(0, "Test", "One");
		Student studentTwo = new Student(1, "Test", "Two");
		Student studentThree = new Student(2, "Test", "Three");
		StudentOption soOne = new StudentOption(3, studentOne);
		StudentOption soTwo = new StudentOption(4, studentTwo);
		StudentOption soThree = new StudentOption(5, studentThree);
		List<StudentOption> list = new ArrayList<>();
		list.add(soOne);
		list.add(soTwo);
		list.add(soThree);
		builder.registerWithMain(list);
		assertEquals(3, Main.getEnteredOptions().size());
		Main.deregister(soOne, soTwo, soThree);
	}
	
	@Test
	public void testGetDataFromMain() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		Student studentOne = new Student(0, "Test", "One");
		Student studentTwo = new Student(1, "Test", "Two");
		Student studentThree = new Student(2, "Test", "Three");
		StudentOption soOne = new StudentOption(3, studentOne);
		StudentOption soTwo = new StudentOption(4, studentTwo);
		StudentOption soThree = new StudentOption(5, studentThree);
		builder.registerWithMain(soOne, soTwo, soThree);
		assertEquals(3, builder.getDataFromMain().size());
		Main.deregister(soOne, soTwo, soThree);
	}
	
	@Test
	public void testToArray() {
		StudentOptionTableBuilder builder = new StudentOptionTableBuilder();
		StudentOption[] options = new StudentOption[5];
		assertArrayEquals(options, builder.toArray(5));
	}

}
