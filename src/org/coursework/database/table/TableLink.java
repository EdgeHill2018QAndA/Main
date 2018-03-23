package org.coursework.database.table;

import java.sql.SQLException;

public interface TableLink {
	
	public String getTableName();
	public String[] getTableColumns();
	public void saveInTable() throws SQLException;
}
