package org.coursework.database.table;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Set;
import org.coursework.database.core.CoreDatabaseLink;

public interface TableBuilder<T extends TableLink> {
    
    public T[] toArray(int size);

    public String getTableName();

    public String[] getTableColumns();

    public String getTableColumnSQL(String columnName);

    public void setInTable(T data) throws SQLException;
    
    public void updateInTable(T data) throws SQLException;

    public Set<T> getData(CoreDatabaseLink link) throws SQLException;

    public Set<T> getDataFromMain();

    @SuppressWarnings("unchecked")
    public void registerWithMain(T... value);

    public default void registerWithMain(Collection<T> values) {
        T[] array = toArray(values.size());
        values.toArray(array);
        registerWithMain(array);
    }

    public default void registerAllWithMain(CoreDatabaseLink link) throws SQLException {
        registerWithMain(getData(link));
    }

    public default void saveAllInTable(CoreDatabaseLink link) throws SQLException {
        Set<T> set = getData(link);
        for (T data : getDataFromMain()) {
            if(set.stream().anyMatch(t -> t.getId() == data.getId())){
                updateInTable(data);
            }else{
                setInTable(data);
            }
        }
    }
}
