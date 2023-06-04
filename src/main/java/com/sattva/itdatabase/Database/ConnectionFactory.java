package com.sattva.itdatabase.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements DatabaseConstants{
    private static ConnectionFactory instance = new ConnectionFactory();
    private Connection conn;

    private  ConnectionFactory() {
    }

    public static ConnectionFactory getInstance() {
        return instance;
    }

    public Connection open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_STRING);
            return conn;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to Database: "+e.getMessage());
            return null;
        }
    }

    public boolean close() {
        try {
            if(conn!=null)
                conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Could not close database connection: "+e.getMessage());
            return false;
        }
    }
}
