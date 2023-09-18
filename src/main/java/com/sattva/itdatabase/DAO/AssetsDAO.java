package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AssetsDAO implements DatabaseConstants {
    private final Connection conn;      // Database Connection

    // SQL queries
    private static final String INSERT_ASSET = "INSERT INTO "+ASSETS_TABLE+" VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String ASSET_EXISTS = "SELECT "+ASSETS_SERIAL+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_SERIAL+"=?";
    private static final String UPDATE_OS = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_OS+"=? WHERE "+ASSETS_SERIAL+"=?";
    private static final String UPDATE_CONDITION = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_CONDITION+"=? WHERE "+ASSETS_SERIAL+"=?";
    private static final String UPDATE_INSTOCK = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_INSTOCK+"=? WHERE "+ASSETS_SERIAL+"=?";
    private static final String UPDATE_OUTSTOCK = "UPDATE "+ASSETS_TABLE+" SET "+ASSETS_OUTSTOCK+"=? WHERE "+ASSETS_SERIAL+"=?";
    private static final String GET_INSTOCK = "SELECT "+ASSETS_INSTOCK+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_SERIAL+"=?";
    private static final String GET_OUTSTOCK = "SELECT "+ASSETS_OUTSTOCK+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_SERIAL+"=?";
    private static final String GET_ASSET_NAME = "SELECT "+ASSETS_NAME+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_TYPE
            +"=? AND "+ASSETS_INSTOCK+">0 AND "+ASSETS_SUBTYPE+"=?";
    private static final String GET_ASSET_MODEL = "SELECT "+ASSETS_MODEL+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_TYPE
            +"=? AND "+ASSETS_INSTOCK+">0 AND "+ASSETS_SUBTYPE+"=? AND "+ASSETS_NAME+"=?";
    private static final String GET_ASSET_SERIAL = "SELECT "+ASSETS_SERIAL+" FROM "+ASSETS_TABLE+" WHERE "+ASSETS_TYPE
            +"=? AND "+ASSETS_INSTOCK+">0 AND "+ASSETS_SUBTYPE+"=? AND "+ASSETS_NAME+"=? AND "+ASSETS_MODEL+"=?";

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
    public boolean insertSystemAsset(String serial, String type, String subType, String name, String model, String os,
                                     int inStock, int outStock, int vendorId) {
        if(assetExists(serial))
            return true;
        try {
            PreparedStatement insertAsset = conn.prepareStatement(INSERT_ASSET);
            insertAsset.setString(1, serial);
            insertAsset.setString(2, type);
            insertAsset.setString(3, subType);
            insertAsset.setString(4, name);
            insertAsset.setString(5, model);
            insertAsset.setString(6, os);
            insertAsset.setString(7, "Good");
            insertAsset.setInt(9, inStock);
            insertAsset.setInt(8, outStock);
            insertAsset.setInt(10, vendorId);

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
    public boolean insertNotSystem(String serial, String type, String subType, String name, String model, int inStock, int outStock, int vendorId) {
        if(assetExists(serial))
            return true;
        try {
            PreparedStatement insertAsset = conn.prepareStatement(INSERT_ASSET);
            insertAsset.setString(1, serial);
            insertAsset.setString(2, type);
            insertAsset.setString(3, subType);
            insertAsset.setString(4, name);
            insertAsset.setString(5, model);
            insertAsset.setString(6, "");
            insertAsset.setString(7, "Good");
            insertAsset.setInt(9, inStock);
            insertAsset.setInt(8, outStock);
            insertAsset.setInt(10, vendorId);

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

    public int getInStock(String serial) {
        try {
            if(!assetExists(serial))
                throw new SQLException("No such asset exists");
            PreparedStatement getInStock = conn.prepareStatement(GET_INSTOCK);
            getInStock.setString(1,serial);
            ResultSet results = getInStock.executeQuery();
            if(results.next())
                return results.getInt(1);
            return -1;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return -1;
        }
    }

    public int getOutStock(String serial) {
        try {
            if(!assetExists(serial))
                throw new SQLException("No such asset exists");
            PreparedStatement getOutStock = conn.prepareStatement(GET_OUTSTOCK);
            getOutStock.setString(1,serial);
            ResultSet results = getOutStock.executeQuery();
            if(results.next())
                return results.getInt(1);
            return -1;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return -1;
        }
    }

    // Update instock on vendor retrieval
    public boolean updateInstock(String serial, int newStock) {
        try {
            if(!assetExists(serial))
                throw new SQLException("No such asset exists");
            PreparedStatement updateInstock = conn.prepareStatement(UPDATE_INSTOCK);
            int currentStock = getInStock(serial);
            currentStock += newStock;
            updateInstock.setInt(1,currentStock);
            updateInstock.setString(2, serial);
            int affectedRows = updateInstock.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating instock of "+serial);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Update stock when asset is released or given to employees
    public boolean assetReleased(String serial, int stockReleased) {
        try {
            if(!assetExists(serial))
                throw new SQLException("No such asset exists");
            if(stockReleased>getInStock(serial))
                throw new SQLException("Stock Unavailable");
            int inStock = getInStock(serial);
            int outStock = getOutStock(serial);
            inStock -= stockReleased;
            outStock += stockReleased;
            PreparedStatement updateInstock = conn.prepareStatement(UPDATE_INSTOCK);
            PreparedStatement updateOutstock = conn.prepareStatement(UPDATE_OUTSTOCK);
            updateInstock.setInt(1, inStock);
            updateInstock.setString(2, serial);
            updateOutstock.setInt(1, outStock);
            updateOutstock.setString(2, serial);

            int affectedRows1 = updateInstock.executeUpdate();
            int affectedRows2 = updateOutstock.executeUpdate();

            if(affectedRows2!=1 || affectedRows1!=1)
                throw new SQLException("More than one row affected when updating stock of "+serial);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public ArrayList<String> assetNames(String type, String subType) {
        try {
            PreparedStatement assetNames = conn.prepareStatement(GET_ASSET_NAME);
            assetNames.setString(1, type);
            assetNames.setString(2, subType);
            ArrayList<String> list = new ArrayList<>();
            ResultSet results = assetNames.executeQuery();
            while(results.next())
                list.add(results.getString(1));
            return list;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    public ArrayList<String> assetModels(String type, String subType, String name) {
        try {
            PreparedStatement assetModel = conn.prepareStatement(GET_ASSET_MODEL);
            assetModel.setString(1, type);
            assetModel.setString(2, subType);
            assetModel.setString(3, name);
            ArrayList<String> list = new ArrayList<>();
            ResultSet results = assetModel.executeQuery();
            while(results.next())
                list.add(results.getString(1));
            return list;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    public ArrayList<String> assetSerials(String type, String subType, String name, String model) {
        try {
            PreparedStatement assetSerials = conn.prepareStatement(GET_ASSET_SERIAL);
            assetSerials.setString(1, type);
            assetSerials.setString(2, subType);
            assetSerials.setString(3, name);
            assetSerials.setString(4, model);
            ArrayList<String> list = new ArrayList<>();
            ResultSet results = assetSerials.executeQuery();
            while(results.next())
                list.add(results.getString(1));
            return list;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }
}
