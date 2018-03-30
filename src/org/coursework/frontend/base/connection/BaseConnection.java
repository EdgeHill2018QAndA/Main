package org.coursework.frontend.base.connection;

import java.io.IOException;
import java.sql.SQLException;

import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;

public interface BaseConnection {

    String getURL();

    String getUsername();

    String getPassword();

    public default void attemptConnection() throws IOException, SQLException {
        CoreDatabaseLink link = new CoreDatabaseLink(getURL(), getUsername(), getPassword());
        if (!link.loadDriver()) {
            throw new IOException("Driver could not be loaded");
        }
        link.openConnection();
        Main.setDatabaseLink(link);
    }

}
