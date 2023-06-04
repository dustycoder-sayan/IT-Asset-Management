package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssetsDAO implements DatabaseConstants {
    private final Connection conn;      // Database Connection

    // SQL queries
    private static final String INSERT_ASSET = "INSERT INTO "+ASSETS_TABLE+" VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String ASSET_EXISTS = "SELECT "+ASSETS_SERIAL+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_SERIAL+"=?";
    private static final String UPDATE_OS = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_OS+"=? WHERE "+ASSETS_SERIAL+"=?";
    private static final String UPDATE_CONDITION = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_CONDITION+"=? WHERE "+ASSETS_SERIAL+"=?";
    private static final String GET_ASSET_SERIAL = "SELECT "+ASSETS_SERIAL+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_TYPE
            +"=? AND "+ASSETS_INSTOCK+">0";
    private static final String UPDATE_QUANTITY = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_INSTOCK+"=? AND "+ASSETS_OUTSTOCK+"=?";

    // Constructor to accept database connection
    public AssetsDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean assetExists(String serial) {
        try {
            PreparedStatement assetExists = conn.prepareStatement(ASSET_EXISTS);
            assetExists.setString(1, serial);

            ResultSet results = assetExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if a system (laptop/desktop) asset was successfully added to db, else returns false
    public boolean insertSystemAsset(String serial, String type, String name, String model, String os,
                               int inStock, int outStock, int vendorId) {
        if(assetExists(serial))
            return true;
        try {
            PreparedStatement insertAsset = conn.prepareStatement(INSERT_ASSET);
            insertAsset.setString(1, serial);
            insertAsset.setString(2, type);
            insertAsset.setString(3, name);
            insertAsset.setString(4, model);
            insertAsset.setString(5, os);
            insertAsset.setString(6, "Good");
            insertAsset.setInt(7, inStock);
            insertAsset.setInt(8, outStock);
            insertAsset.setInt(9, vendorId);

            int affectedRows = insertAsset.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting asset details of "+name);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if a non-system (vpn,printer,etc.) asset was successfully added to db, else returns false
    public boolean insertNonSystemAsset(String serial, String type, String name, String model, int inStock, int outStock, int vendorId) {
        if(assetExists(serial))
            return true;
        try {
            PreparedStatement insertAsset = conn.prepareStatement(INSERT_ASSET);
            insertAsset.setString(1, serial);
            insertAsset.setString(2, type);
            insertAsset.setString(3, name);
            insertAsset.setString(4, model);
            insertAsset.setString(5, null);
            insertAsset.setString(6, "Good");
            insertAsset.setInt(7, inStock);
            insertAsset.setInt(8, outStock);
            insertAsset.setInt(9, vendorId);

            int affectedRows = insertAsset.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting asset details of "+name);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Updates the OS of a system, returns true is successfully updated in db, else false
    public boolean updateOS(String serial, String os) {
        try {
            PreparedStatement updateOS = conn.prepareStatement(UPDATE_OS);
            updateOS.setString(1, os);
            updateOS.setString(2, serial);

            int affectedRows = updateOS.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating OS of "+serial);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Updates the condition of the asset on being returned by the employee - Returns true if updated successfully, else returns false
    public boolean updateCondition(String serial, String condition) {
        try {
            PreparedStatement updateCondition = conn.prepareStatement(UPDATE_CONDITION);
            updateCondition.setString(1, condition);
            updateCondition.setString(2, serial);

            int affectedRows = updateCondition.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating condition of "+serial);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public String getAssetSerial(String type) {
        try {
            PreparedStatement assetSerial = conn.prepareStatement(GET_ASSET_SERIAL);
            assetSerial.setString(1, type);

            ResultSet results = assetSerial.executeQuery();
            return results.getString(1);
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }
}
