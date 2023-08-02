package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAO implements DatabaseConstants {
    private final Connection conn;

    private static final String CITY_EXISTS = "SELECT "+CITY_CITY+" FROM "+CITY_TABLE+" WHERE "+CITY_CITY+"=?";
    private static final String INSERT_CITY = "INSERT INTO "+CITY_TABLE+" VALUES(?)";

    public CityDAO(Connection conn) {
        this.conn = conn;
    }

    // Return true if city has already been added to table
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

    // Returns true if city was inserted successfully, else returns false
    public boolean insertCity(String city) {
        try {
            if(cityExists(city))
                return true;
            PreparedStatement insertCity = conn.prepareStatement(INSERT_CITY);
            insertCity.setString(1, city);
            int affectedRows = insertCity.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting city "+city);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }
}
