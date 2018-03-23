package org.coursework.database.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.coursework.database.table.TableLink;

public class CoreDatabaseLink {
	
	private String username;
	private String password;
	private String url;
	Connection connection;
	
	public CoreDatabaseLink(String url, String username, String password) {
		this.username = username;
		this.password = password;
		this.url = url;
	}
	
	public String getURL() {
		return url;
	}
	
	public String getUsername() {
		return username;
	}
	
	public boolean loadDriver() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
	public void openConnection() throws SQLException {
		this.connection = DriverManager.getConnection(url, this.username, this.password);
	}
	
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return this.connection.prepareStatement(sql);
	}
	
	public ResultSet executeQuery(String sql) throws SQLException {
		Statement statement = this.connection.createStatement();
		return statement.executeQuery(sql);
	}
	
	public void executeUpdate(String sql) throws SQLException {
		Statement statement = this.connection.createStatement();
		statement.executeUpdate(sql);
	}
	
	public int getTableSize(TableLink link) throws SQLException {
		return getTableSize(link.getTableName());
	}
	
	public int getTableSize(String tableName) throws SQLException {
		String query = "SELECT count(*) FROM " + tableName;
		PreparedStatement statement = this.connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		return set.getInt(1);
	}
	
	public void createTable(TableLink link) throws SQLException{
		
	}
	
	public void insertInto(TableLink link, Object... object) throws SQLException{
		PreparedStatement statement;
		String marks = null;
		String col = null;
		String query;
		for(int A = 0; A < link.getTableColumns().length; A++) {
			if(marks == null) {
				marks = "?";
				col = link.getTableColumns()[A];
			}else {
				marks = marks + ",?";
				col = col + ", " + link.getTableColumns()[A];
			}
		}
		query = "INSERT INTO " + link.getTableName() + "(" + col + ") VALUES (" + marks + ")";
		statement = this.connection.prepareStatement(query);
		for(int A = 0; A < object.length; A++) {
			Object obj = object[A];
			statement.setObject(A, obj);
		}
		statement.executeUpdate();
	}

}
