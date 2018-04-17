package test.org.coursework.backend.person;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.coursework.Main;
import org.coursework.backend.person.Person;
import org.coursework.backend.person.PersonTableBuilder;
import org.coursework.backend.person.staff.Staff;
import org.coursework.backend.person.student.Student;
import org.junit.Test;

public class TestPersonTableBuilder {
	
	@Test
	public void testGetTableName() {
		PersonTableBuilder builder = new PersonTableBuilder();
		assertEquals(PersonTableBuilder.TABLE_NAME, builder.getTableName());
	}
	
	@Test
	public void testTableColumns() {
		PersonTableBuilder builder = new PersonTableBuilder();
		String[] columns = {"id", "firstname", "lastname", "permission"};
		assertArrayEquals(columns, builder.getTableColumns());
	}
	
	@Test
	public void testGetTableColumnSQLWithID() {
		PersonTableBuilder builder = new PersonTableBuilder();
		assertEquals("id INTEGER not NULL", builder.getTableColumnSQL("id"));
	}
	
	@Test
	public void testGetTableColumnSQLWithFirstName() {
		PersonTableBuilder builder = new PersonTableBuilder();
		assertEquals("firstname VARCHAR(255) not NULL", builder.getTableColumnSQL("firstname"));
	}
	
	@Test
	public void testGetTableColumnSQLWithLastName() {
		PersonTableBuilder builder = new PersonTableBuilder();
		assertEquals("lastname VARCHAR(255) not NULL", builder.getTableColumnSQL("lastname"));
	}
	
	@Test
	public void testGetTableColumnSQLWithPermission() {
		PersonTableBuilder builder = new PersonTableBuilder();
		assertEquals("permission VARCHAR(10) not NULL", builder.getTableColumnSQL("permission"));
	}
	
	@Test
	public void testGetTableColumnSQLWithRandomValue() {
		PersonTableBuilder builder = new PersonTableBuilder();
		assertNull(builder.getTableColumnSQL("sdgihPASGIASG"));
	}
	
	@Test
	public void testRegisterWithMainArray() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		builder.registerWithMain(studentOne, studentTwo);
		assertEquals(2, Main.getPeople().size());
		Main.deregister(studentOne, studentTwo);
	}
	
	@Test
	public void testRegisterWithMainArrayWithStudentOnly() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		Staff staffOne = new Staff(2, "Test", "One");
		Staff staffTwo = new Staff(1, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		builder.registerWithMain(staffOne, staffTwo, studentOne, studentTwo);
		assertEquals(2, Main.getPeople(Student.class).size());
		Main.deregister(staffOne, staffTwo, studentOne, studentTwo);
	}
	
	@Test
	public void testRegisterWithMainArrayWithStaffOnly() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		Staff staffOne = new Staff(2, "Test", "One");
		Staff staffTwo = new Staff(1, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		builder.registerWithMain(studentOne, studentTwo, staffOne, staffTwo);
		assertEquals(2, Main.getPeople(Staff.class).size());
		Main.deregister(staffOne, staffTwo, studentOne, studentTwo);
	}

	@Test
	public void testRegisterWithMainCollection() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		List<Person> list = new ArrayList<>();
		list.add(studentOne);
		list.add(studentTwo);
		builder.registerWithMain(list);
		assertEquals(2, Main.getPeople().size());
		Main.deregister(studentOne, studentTwo);
	}
	
	@Test
	public void testRegisterWithMainCollectionWithStudentOnly() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		Staff staffOne = new Staff(2, "Test", "One");
		Staff staffTwo = new Staff(1, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		List<Person> list = new ArrayList<>();
		list.add(studentOne);
		list.add(studentTwo);
		list.add(staffOne);
		list.add(staffTwo);
		builder.registerWithMain(list);
		assertEquals(2, Main.getPeople(Student.class).size());
		Main.deregister(staffOne, staffTwo, studentOne, studentTwo);
	}
	
	@Test
	public void testRegisterWithMainCollectionWithStaffOnly() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		Staff staffOne = new Staff(2, "Test", "One");
		Staff staffTwo = new Staff(1, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		List<Person> list = new ArrayList<>();
		list.add(studentOne);
		list.add(studentTwo);
		list.add(staffOne);
		list.add(staffTwo);
		builder.registerWithMain(list);
		assertEquals(2, Main.getPeople(Staff.class).size());
		Main.deregister(staffOne, staffTwo, studentOne, studentTwo);
	}
	
	@Test
	public void testGetDataFromMain() {
		Student studentOne = new Student(5, "Test", "One");
		Student studentTwo = new Student(6, "Test", "Two");
		Staff staffOne = new Staff(2, "Test", "One");
		Staff staffTwo = new Staff(1, "Test", "Two");
		PersonTableBuilder builder = new PersonTableBuilder();
		builder.registerWithMain(studentOne, studentTwo, staffOne, staffTwo);
		assertEquals(4, builder.getDataFromMain().size());
		
	}
	
	@Test
	public void testToArray() {
		Person[] array = new Person[5];
		PersonTableBuilder builder = new PersonTableBuilder();
		assertArrayEquals(array, builder.toArray(5));
	}
	
}
