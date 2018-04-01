package org.coursework.backend.roles;

import java.sql.SQLException;
import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableLink;

public class Role implements TableLink {

    int id;
    String displayName;

    public Role(String displayName) throws SQLException {
        this(getUniquieId(true), displayName);
    }
    
    public Role(String displayName, boolean useDatabase) throws SQLException{
        this(getUniquieId(useDatabase), displayName);
    }
    
    public Role(int id, String displayName){
        this.id = id;
        this.displayName = displayName;
    }
    
    public int getId(){
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

    @Override
    public String getTableName() {
        return "Roles";
    }

    @Override
    public String[] getTableColumns() {
        return new String[]{"id", "name"};
    }

    @Override
    public void saveInTable() throws SQLException {
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        link.insertInto(this, id, getDisplayName());
    }
    
    private static int getUniquieId(boolean useDatabase) throws SQLException {
        if(useDatabase){
            return Main.getDatabaseLink().get().getTableSize("Roles");
        }
        return Main.getPeople().size();
    }

}
