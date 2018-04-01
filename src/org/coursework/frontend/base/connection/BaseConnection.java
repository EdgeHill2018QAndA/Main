package org.coursework.frontend.base.connection;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import org.coursework.Main;
import org.coursework.database.core.CoreDatabaseLink;

public interface BaseConnection {

    String getURL();

    String getUsername();

    String getPassword();

    public default void fillDatabase() throws IOException {
        Optional<CoreDatabaseLink> opLink = Main.getDatabaseLink();
        if (!opLink.isPresent()) {
            throw new IOException("No Database Link");
        }
        CoreDatabaseLink link = opLink.get();
        link.loadData();
    }

    public default void attemptConnection() throws IOException, SQLException {
        CoreDatabaseLink link = new CoreDatabaseLink(getURL(), getUsername(), getPassword());
        if (!link.loadDriver()) {
            throw new IOException("Driver could not be loaded");
        }
        link.openConnection();
        Main.setDatabaseLink(link);
    }

    public default void createDatabase() throws IOException {
        Optional<CoreDatabaseLink> opLink = Main.getDatabaseLink();
        if (!opLink.isPresent()) {
            throw new IOException("No Database Link");
        }
        CoreDatabaseLink link = opLink.get();
        link.createDatabase();
    }

    public default boolean isDatabaseCreated() throws IOException {
        Optional<CoreDatabaseLink> opLink = Main.getDatabaseLink();
        if (!opLink.isPresent()) {
            throw new IOException("No Database Link");
        }
        CoreDatabaseLink link = opLink.get();
        boolean check = Main.getTableBuilders().stream().allMatch(b -> {
            try {
                boolean check2 = link.isTablePresent(b);
                System.out.println("Is Table (" + b.getTableName() + ") created:" + check2);
                return check2;
            } catch (SQLException ex) {
                System.out.println("on catch (is table (" + b.getTableName() + ") created)");
                return true;
            }
        });
        System.out.println("isDatabaseCreated: " + check);
        return check;
    }

}
