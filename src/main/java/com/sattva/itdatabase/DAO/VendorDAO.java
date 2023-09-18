package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendorDAO implements DatabaseConstants {
    private final Connection conn;            // Database Connection

    // SQL queries
    private static final String INSERT_VENDOR = "INSERT INTO "+VENDOR_TABLE+"("+VENDOR_NAME+","+VENDOR_CONTACT+","+
            VENDOR_LOCATION+","+VENDOR_EMAIL+") VALUES(?,?,?,?)";
    private static final String QUERY_VENDOR = "SELECT "+VENDOR_ID+" FROM "+VENDOR_TABLE+" WHERE "+VENDOR_NAME+"=? AND "
            +VENDOR_CONTACT+"=? AND "+VENDOR_LOCATION+"=? AND "+VENDOR_EMAIL+"=?";
    public static final String UPDATE_VENDOR_CONTACT = "UPDATE "+VENDOR_TABLE+" SET "+VENDOR_CONTACT+"=? WHERE "+
            VENDOR_ID+"=?";
    public static final String GET_VENDOR_ID = "SELECT "+VENDOR_ID+" FROM "+VENDOR_TABLE+" WHERE "+VENDOR_NAME+"=?";

    // Constructor to accept database connection
    public VendorDAO(Connection conn) {
        this.conn = conn;
    }

    // Returns the vendorId if vendor already exists, else -1
    public int vendorExists(String name, String contact, String location, String emailId) throws SQLException {
        PreparedStatement queryVendor = conn.prepareStatement(QUERY_VENDOR);
        queryVendor.setString(1, name);
        queryVendor.setString(2, contact);
        queryVendor.setString(3, location);
        queryVendor.setString(4, emailId);

        ResultSet results = queryVendor.executeQuery();
        if(results.next())
            return results.getInt(1);
        return -1;
    }

    // Returns vendorId after inserting vendor
    public int insertVendor(String name, String contact, String location, String emailId) {
        try {
            int vendorId;
            if((vendorId = vendorExists(name, contact, location, emailId)) != -1)
                return vendorId;

            PreparedStatement insertVendor = conn.prepareStatement(INSERT_VENDOR);
            insertVendor.setString(1, name);
            insertVendor.setString(2, contact);
            insertVendor.setString(3, location);
            insertVendor.setString(4, emailId);

            int affectedRows = insertVendor.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting vendor details of "+name);

            ResultSet generatedKey = insertVendor.getGeneratedKeys();
            if(generatedKey.next())
                return generatedKey.getInt(1);
            else
                throw new SQLException("Couldn't insert Vendor "+name);
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return -1;
        }
    }

    // Returns true if vendor's contact number could be updated successfully, else returns false
    public boolean updateVendor(int vendorId, String contact) {
        try {
            PreparedStatement updateVendor = conn.prepareStatement(UPDATE_VENDOR_CONTACT);
            updateVendor.setString(1, contact);
            updateVendor.setInt(2, vendorId);

            int affectedRows = updateVendor.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating contact for Vendor "+vendorId);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns vendorId if vendor exists, else returns -1
    public int getVendorId(String name) {
        try {
            PreparedStatement getVendorId = conn.prepareStatement(GET_VENDOR_ID);
            getVendorId.setString(1, name);
            ResultSet result = getVendorId.executeQuery();
            if(result.next())
                return result.getInt(1);
            else
                throw new SQLException("No such Vendor Found");
        } catch(SQLException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
}
