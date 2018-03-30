package org.coursework.backend.person;

import java.sql.SQLException;

import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;

public abstract class AbstractPerson implements Person {

    String firstName;
    String lastName;
    int id;

    public AbstractPerson(String firstName, String lastName) throws SQLException {
        this(Main.getDatabaseLink().get().getTableSize("Accounts"), firstName, lastName);
    }

    public AbstractPerson(int id, String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
    }

    public int getID() {
        return id;
    }

    @Override
    public String getTableName() {
        return "Accounts";
    }

    @Override
    public String[] getTableColumns() {
        String[] columns = {"AccountID", "First", "Last", "Permission"};
        return columns;
    }

    @Override
    public void saveInTable() throws SQLException {
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        link.insertInto(this, id, getFirstName(), getLastName(), getPermission().name());

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
