package org.coursework.backend.group;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;
import org.coursework.database.table.TableBuilder;

public class GroupTableBuilder implements TableBuilder<Group> {
    
    public static final String TABLE_NAME = "GROUPS";

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public String[] getTableColumns() {
        return new String[]{"id"};
    }

    @Override
    public void setInTable(Group data) throws SQLException {
        CoreDatabaseLink link = Main.getDatabaseLink().get();
        link.insertInto(this, data.getId());
    }
    
    @Override
    public void updateInTable(Group group) throws SQLException {
        //nothing to update
    }

    @Override
    public String getTableColumnSQL(String columnName) {
        return "id INTEGER not NULL";
    }

    @Override
    public Set<Group> getData(CoreDatabaseLink link) throws SQLException {
        String[] columns = getTableColumns();
        List<Group> groups = new ArrayList<>();
        ResultSet set = link.executeQuery("SELECT * FROM " + getTableName());
        while (set.next()) {
            int id = set.getInt(columns[0]);
            Group group = new Group(id);
            groups.add(group);
        }
        return new HashSet<>(groups);
    }

    @Override
    public void registerWithMain(Group... value) {
    	List<Group> groups = new ArrayList<>(Main.getGroups());
        for (Group group : value) {
            groups.add(group);
        }
        Main.setGroups(groups);
    }

    @Override
    public Set<Group> getDataFromMain() {
        return Main.getGroups();
    }
    
    @Override
    public Group[] toArray(int size) {
        return new Group[size];
    }

}
