package org.coursework.backend.roles;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.coursework.Main;
import org.coursework.backend.person.Person;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableBuilder;

public class RoleTableBuilder implements TableBuilder<Role> {

    public static final String TABLE_NAME = "ROLES";
    
    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getTableColumns() {
        return new String[]{"id", "name"};
    }

    @Override
    public void saveInTable(Role role) throws SQLException {
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        link.insertInto(this, role.getId(), role.getDisplayName());
    }

    @Override
    public String getTableColumnSQL(String columnName) {
        switch (columnName) {
            case "id":
                return "id INTEGER not NULL";
            case "name":
                return "name VARCHAR(255)";
            default:
                return null;
        }
    }

    @Override
    public Set<Role> getData(CoreDatabaseLink link) throws SQLException {
        String[] columns = getTableColumns();
        List<Role> roles = new ArrayList<>();
        ResultSet set = link.executeQuery("SELECT * FROM " + getTableName());
        while (set.next()) {
            int id = set.getInt(columns[0]);
            String displayName = set.getString(columns[1]);
            roles.add(new Role(id, displayName));
        }
        return new HashSet<>(roles);
    }

    @Override
    public void registerWithMain(Role... value) {
        Main.register(value);
    }

    @Override
    public Set<Role> getDataFromMain() {
        return Main.getRoles();
    }
    
    @Override
    public Role[] toArray(int size) {
        return new Role[size];
    }

}
