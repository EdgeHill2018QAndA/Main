package org.coursework.frontend.base.connection;

import java.io.IOException;
import java.sql.SQLException;

import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;

public class BaseConnection {
	
	String username;
	String password;
	String url;
	
	public String getUsername() {
		return this.username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getURL() {
		return this.url;
	}
	
	public void setURL(String url) {
		this.url = url;
	}
	
	public void attemptConnection() throws IOException, SQLException {
		CoreDatabaseLink link = new CoreDatabaseLink(url, username, password);
		if(!link.loadDriver()) {
			throw new IOException("Driver could not be loaded");
		}
		link.openConnection();
		Main.setDatabaseLink(link);
	}

}
