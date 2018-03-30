package org.coursework.backend.person;

import org.coursework.database.table.TableLink;

public interface Person extends TableLink {

    public String getFirstName();

    public String getLastName();

    public Permission getPermission();

    public default String getName() {
        return getFirstName() + " " + getLastName();
    }

}
