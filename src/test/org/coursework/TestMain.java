package test.org.coursework;

import static org.junit.Assert.assertEquals;

import java.util.Optional;

import org.coursework.Main;
import org.coursework.backend.person.Person;
import org.coursework.backend.person.student.Student;
import org.junit.jupiter.api.Test;

public class TestMain {
	
	@Test
	public void testGetLoggedInAs() {
		Main.setLoggedInAs(null);
		assertEquals(false, Main.getLoggedInAs().isPresent());
	}
	
	@Test
	public void testSetLoggedInAs() {
		Student student = new Student(5, "JUnit", "Test");
		Main.setLoggedInAs(student);
		Optional<Person> opPerson = Main.getLoggedInAs();
		assertEquals(true, opPerson.isPresent());
	}
	
}
