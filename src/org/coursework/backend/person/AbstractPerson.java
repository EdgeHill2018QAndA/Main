package org.coursework.backend.person;

import java.sql.SQLException;
import org.coursework.database.table.TableLink;

public abstract class AbstractPerson implements Person {

    String firstName;
    String lastName;
    int id;

    public AbstractPerson(String firstName, String lastName) throws SQLException {
        this(firstName, lastName, true);
    }

    public AbstractPerson(String firstName, String lastName, boolean useSQL) throws SQLException {
        this(TableLink.getUniquieId(PersonTableBuilder.TABLE_NAME, useSQL), firstName, lastName);
    }

    public AbstractPerson(int id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        System.out.println("FirstName: " + this.firstName + " | LastName: " + this.lastName + " | ID: " + this.id);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }
}
