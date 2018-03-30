package org.coursework.backend.person.student;

import java.sql.SQLException;

import org.coursework.backend.person.AbstractPerson;
import org.coursework.backend.person.Permission;
import org.coursework.backend.person.Person;

public class Student extends AbstractPerson implements Person {

    public Student(String first, String last) throws SQLException {
        super(first, last);
    }
    
    public Student(String first, String last, boolean useSQL) throws SQLException {
        super(first, last, useSQL);
    }

    public Student(int id, String first, String last) {
        super(id, first, last);
    }

    @Override
    public Permission getPermission() {
        return Permission.STUDENT;
    }

}
