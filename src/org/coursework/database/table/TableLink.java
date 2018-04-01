package org.coursework.database.table;

import java.sql.SQLException;
import org.coursework.Main;

public interface TableLink {

    public int getId();

    public static int getUniquieId(String tableName, boolean useDatabase) throws SQLException {
        if (useDatabase) {
            return Main.getDatabaseLink().get().getTableSize(tableName);
        }
        return Main.getPeople().size();
    }

}
