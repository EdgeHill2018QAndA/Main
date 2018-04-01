package org.coursework.backend.roles;

import java.sql.SQLException;
import org.coursework.database.table.TableLink;

public class Role implements TableLink {

    int id;
    String displayName;

    public Role(String displayName) throws SQLException {
        this(displayName, true);
    }

    public Role(String displayName, boolean useDatabase) throws SQLException {
        this(TableLink.getUniquieId(RoleTableBuilder.TABLE_NAME, useDatabase), displayName);
    }

    public Role(int id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    @Override
    public int getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String name) {
        this.displayName = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Role)) {
            return false;
        }
        Role role = (Role) obj;
        if (!(role.getDisplayName().equalsIgnoreCase(getDisplayName()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.displayName;
    }

}
