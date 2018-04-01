package org.coursework.database.table;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import org.coursework.database.core.CoreDatabaseLink;

public interface TableBuilder<T extends TableLink> {

    public String getTableName();

    public String[] getTableColumns();

    public String getTableColumnSQL(String columnName);

    public void saveInTable(T data) throws SQLException;

    public Set<T> getData(CoreDatabaseLink link) throws SQLException;

    public Set<T> getDataFromMain();

    @SuppressWarnings("unchecked")
	public void registerWithMain(T... value);

    @SuppressWarnings("unchecked")
	public default void registerWithMain(Collection<T> values) {
        values.stream().forEach(r -> {
            System.out.println("Register with main: " + r.getClass().getSimpleName());
            registerWithMain(r);
        });
    }

    public default void registerAllWithMain(CoreDatabaseLink link) throws SQLException {
        registerWithMain(getData(link));
    }

    public default void saveAllInTable() throws SQLException {
        for (T data : getDataFromMain()) {
            saveInTable(data);
        }
    }
}
