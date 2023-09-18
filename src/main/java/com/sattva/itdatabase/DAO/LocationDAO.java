package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocationDAO implements DatabaseConstants {
    private final Connection conn;      // Database Connection

    // SQL queries
    private static final String INSERT_LOCATION = "INSERT INTO "+LOCATION_TABLE+" VALUES(?,?,?,?)";
    private static final String CITY_EXISTS = "SELECT "+LOCATION_CITY+" FROM "+LOCATION_TABLE+" WHERE "+
            LOCATION_CITY+"=?";
    private static final String LOCATION_EXISTS = "SELECT "+LOCATION_CITY+" FROM "+LOCATION_TABLE+" WHERE "+
            LOCATION_CITY+"=? AND "+LOCATION_LOCATION+"=?";
    private static final String SPACE_EXISTS = "SELECT "+LOCATION_CITY+" FROM "+LOCATION_TABLE+" WHERE "+
            LOCATION_CITY+"=? AND "+LOCATION_LOCATION+"=? AND "+LOCATION_SPACE+"=?";
    private static final String GET_LOCATION_NAME = "SELECT "+LOCATION_LOCATION+" FROM "+LOCATION_TABLE+" WHERE "
            +LOCATION_ID+"=?";
    private static final String GET_SPACE_NAME = "SELECT "+LOCATION_SPACE+" FROM "+LOCATION_TABLE+" WHERE "
            +LOCATION_ID+"=?";
    private static final String GET_CITY_NAME = "SELECT "+LOCATION_CITY+" FROM "+LOCATION_TABLE+" WHERE "
            +LOCATION_ID+"=?";

    // Constructor to accept database connection
    public LocationDAO(Connection conn) {
        this.conn = conn;
    }

    // Returns true if the company is present in the said city, else returns false
    public boolean cityExists(String city) {
        try {
            PreparedStatement cityExists = conn.prepareStatement(CITY_EXISTS);
            cityExists.setString(1, city);

            ResultSet results = cityExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if the location exists in the said city, else returns false
    public boolean locationExists(String city, String location) {
        try {
            PreparedStatement locationExists = conn.prepareStatement(LOCATION_EXISTS);
            locationExists.setString(1, city);
            locationExists.setString(2, location);

            ResultSet results = locationExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if the space exists in the location of the said city, else returns false
    public boolean spaceExists(String city, String location, String space) {
        try {
            PreparedStatement spaceExists = conn.prepareStatement(SPACE_EXISTS);
            spaceExists.setString(1, city);
            spaceExists.setString(2, location);
            spaceExists.setString(3, space);

            ResultSet results = spaceExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // generates a location_id based on given city, location and space
    public String generateLocationId(String city, String location, String space) {
        return city.substring(0,3)+location.substring(0,3)+space.substring(0,3);
    }

    // Returns locationId if location was successfully inserted, else returns null
    public String insertLocation(String city, String location, String space) {
        if(spaceExists(city, location, space))
            return generateLocationId(city, location, space);
        try {
            String locationId = generateLocationId(city, location, space);
            PreparedStatement insertLocation = conn.prepareStatement(INSERT_LOCATION);
            insertLocation.setString(1, locationId);
            insertLocation.setString(2, city);
            insertLocation.setString(3, location);
            insertLocation.setString(4, space);

            int affectedRows = insertLocation.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting location "+location+", "+city);
            return locationId;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    public String getLocationName(String locationId) {
        try {
            PreparedStatement getLocation = conn.prepareStatement(GET_LOCATION_NAME);
            getLocation.setString(1, locationId);
            ResultSet results = getLocation.executeQuery();
            return results.getString(1);
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    public String getCityName(String locationId) {
        try {
            PreparedStatement getLocation = conn.prepareStatement(GET_CITY_NAME);
            getLocation.setString(1, locationId);
            ResultSet results = getLocation.executeQuery();
            return results.getString(1);
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    public String getSpaceName(String locationId) {
        try {
            PreparedStatement getLocation = conn.prepareStatement(GET_SPACE_NAME);
            getLocation.setString(1, locationId);
            ResultSet results = getLocation.executeQuery();
            return results.getString(1);
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }
}
