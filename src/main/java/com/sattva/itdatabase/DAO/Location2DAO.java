package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Location2DAO implements DatabaseConstants {
    private final Connection conn;

    private static final String LOCATION_EXISTS = "SELECT "+LOCATION2_LOCATION+" FROM "+LOCATION2_TABLE+" WHERE "+
            LOCATION2_LOCATION+"=?";
    private static final String INSERT_LOCATION = "INSERT INTO "+LOCATION2_TABLE+" VALUES(?)";

    public Location2DAO(Connection conn) {
        this.conn = conn;
    }

    // Return true if city has already been added to table
    public boolean locationExists(String location) {
        try {
            PreparedStatement locationExists = conn.prepareStatement(LOCATION_EXISTS);
            locationExists.setString(1, location);
            ResultSet results = locationExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if city was inserted successfully, else returns false
    public boolean insertLocation(String location) {
        try {
            if(locationExists(location))
                return true;
            PreparedStatement insertLocation = conn.prepareStatement(INSERT_LOCATION);
            insertLocation.setString(1, location);
            int affectedRows = insertLocation.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting location "+location);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }
}
