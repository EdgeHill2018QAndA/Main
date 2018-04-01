package org.coursework.database.core;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.coursework.Main;

import org.coursework.database.table.TableBuilder;
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
            e.printStackTrace();
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
        System.out.println("\t" + sql);
        statement.executeUpdate(sql);
    }

    public int getTableSize(TableBuilder<? extends TableLink> link) throws SQLException {
        return getTableSize(link.getTableName());
    }

    public int getTableSize(String tableName) throws SQLException {
        String query = "SELECT count(*) FROM " + tableName;
        PreparedStatement statement = this.connection.prepareStatement(query);
        ResultSet set = statement.executeQuery();
        try {
            return set.getInt(1);
        } catch (SQLException e) {
            return 0;
        }
    }

    public void insertInto(TableBuilder<? extends TableLink> link, Object... object) throws SQLException {
        PreparedStatement statement;
        String marks = null;
        String col = null;
        String query;
        for (int A = 0; A < link.getTableColumns().length; A++) {
            if (marks == null) {
                marks = "?";
                col = link.getTableColumns()[A];
            } else {
                marks = marks + ",?";
                col = col + ", " + link.getTableColumns()[A];
            }
        }
        query = "INSERT INTO " + link.getTableName() + "(" + col + ") VALUES (" + marks + ")";
        statement = this.connection.prepareStatement(query);
        for (int A = 0; A < object.length; A++) {
            Object obj = object[A];
            statement.setObject((A + 1), obj);
        }
        statement.executeUpdate();
    }

    public boolean isTablePresent(TableBuilder<? extends TableLink> builder) throws SQLException {
        DatabaseMetaData dmd = this.connection.getMetaData();
        ResultSet table = dmd.getTables(null, null, builder.getTableName(), null);
        while(table.next()){
            String tName = table.getString("TABLE_NAME");
            System.out.println("Searching: " + builder.getTableName() + " - " + tName);
            if((tName != null) && (tName.equals(builder.getTableName()))){
                System.out.println("return true;");
                return true;
            }
        }
        System.out.println("return false");
        return false;
    }

    public void createDatabase() {
        Main.getTableBuilders().stream().forEach(b -> {
            try {
                System.out.println("Checking table present");
                if (!isTablePresent(b)) {
                    System.out.println("table was not present");
                    String query = "CREATE TABLE " + b.getTableName() + " (";
                    for (String columnName : b.getTableColumns()) {
                        query = query + b.getTableColumnSQL(columnName) + ", ";
                    }
                    query = query + "PRIMARY KEY (" + b.getTableColumns()[0] + "))";
                    executeUpdate(query);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void loadData() {
        Main.getTableBuilders().stream().forEach(b -> {
            try {
                b.registerAllWithMain(CoreDatabaseLink.this);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void saveData() {
        Main.getTableBuilders().stream().forEach(b -> {
            try {
                b.saveAllInTable();
            } catch (SQLException ex) {
                System.err.println("Failed to save to database in table: " + b.getTableName());
                ex.printStackTrace();
            }
        });
    }
}
