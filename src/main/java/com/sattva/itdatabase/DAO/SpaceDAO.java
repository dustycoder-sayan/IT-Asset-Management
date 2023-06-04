package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SpaceDAO implements DatabaseConstants {
    private final Connection conn;

    private static final String SPACE_EXISTS = "SELECT "+SPACE_SPACE+" FROM "+SPACE_TABLE+" WHERE "+SPACE_SPACE+"=?";
    private static final String INSERT_SPACE = "INSERT INTO "+SPACE_TABLE+" VALUES(?)";

    public SpaceDAO(Connection conn) {
        this.conn = conn;
    }

    // Return true if city has already been added to table
    public boolean spaceExists(String space) {
        try {
            PreparedStatement spaceExists = conn.prepareStatement(SPACE_EXISTS);
            spaceExists.setString(1, space);
            ResultSet results = spaceExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if city was inserted successfully, else returns false
    public boolean insertSpace(String space) {
        try {
            if(spaceExists(space))
                throw new SQLException("Space already inserted");
            PreparedStatement insertSpace = conn.prepareStatement(INSERT_SPACE);
            insertSpace.setString(1, space);
            int affectedRows = insertSpace.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting space "+space);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }
}
