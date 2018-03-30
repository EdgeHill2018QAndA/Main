package org.coursework.backend.person.staff;

import java.sql.SQLException;

import org.coursework.backend.person.AbstractPerson;
import org.coursework.backend.person.Permission;
import org.coursework.backend.person.Person;

public class Staff extends AbstractPerson implements Person {

    public Staff(String firstName, String lastName) throws SQLException {
        super(firstName, lastName);
    }

    public Staff(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    @Override
    public Permission getPermission() {
        return Permission.ADMIN;
    }

}
