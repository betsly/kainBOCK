package com.example.kainBOCK.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_Database {
    private String db_url = "jdbc:postgresql://ec2-54-220-53-223.eu-west-1.compute.amazonaws.com:5432/dbn3qsqsrdh9am";
    private String db_driver = "org.postgresql.Driver";
    private String db_username = "nosbzgeqvthazr";
    private String db_password = "56eff061337f9acf82566221cdbc8f80fd92026a147e22ab1b5bfb2df9b07040";
    private Connection connection;
    private DB_CachedConnection cachedConnection;

    public DB_Database() throws ClassNotFoundException, SQLException {
//      loadProperties();
        Class.forName(db_driver);
        connect();
    }

    /**
     * establish connection to database
     *
     * @throws SQLException
     */
    public void connect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
        connection = DriverManager.getConnection(db_url, db_username, db_password);
        cachedConnection = new DB_CachedConnection(connection);
    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
            cachedConnection = null;
        }
    }

//    public void loadProperties() {
//        db_url = DB_Properties.getPropertyValue("url");
//        db_driver = DB_Properties.getPropertyValue("driver");
//        db_username = DB_Properties.getPropertyValue("username");
//        db_password = DB_Properties.getPropertyValue("password");
//    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() throws SQLException {
        if (connection == null || cachedConnection == null) {
            throw new RuntimeException("database connection error");
        }
        return cachedConnection.getStatement();
    }

    public void releaseStatement(Statement statement) {
        if (connection == null || cachedConnection == null) {
            throw new RuntimeException("database connection error");
        }
        cachedConnection.releaseStatement(statement);
    }
}
