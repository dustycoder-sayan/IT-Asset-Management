package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllocationDAO implements DatabaseConstants {
    private final Connection conn;      // Database Connection

    // SQL queries
    private static final String REQUEST_ALLOCATION = "INSERT INTO "+ALLOCATION_TABLE+"("+ALLOCATION_EMP_CODE+","
            +ALLOCATION_TYPE+","+ALLOCATION_SERIAL+","+ALLOCATION_QUANTITY+","+ALLOCATION_LOCATION_ID+","+ALLOCATION_STATUS+","+
            ALLOCATION_DATE+") VALUES(?,?,?,?,?,?,?)";
    private static final String UPDATE_STATUS = "UPDATE "+ALLOCATION_TABLE+" SET "+ALLOCATION_STATUS+"=? WHERE "
            +ALLOCATION_EMP_CODE+"=? AND "+ALLOCATION_SERIAL+"=?";
    private static final String UPDATE_REMARKS = "UPDATE "+ALLOCATION_TABLE+" SET "+ALLOCATION_REMARKS+"=? WHERE "
            +ALLOCATION_EMP_CODE+"=? AND "+ALLOCATION_SERIAL+"=?";

    // Constructor to accept database connection
    public AllocationDAO(Connection conn) {
        this.conn = conn;
    }

    // Returns true if request made by employee was successfully updated in db, else returns false
    public boolean requestAllocation(String empCode, String type, int quantity, String locationId, String date) {
        try {
            PreparedStatement requestAllocation = conn.prepareStatement(REQUEST_ALLOCATION);
            requestAllocation.setString(1, empCode);
            requestAllocation.setString(2, type);
            requestAllocation.setString(3, "");
            requestAllocation.setInt(4, quantity);
            requestAllocation.setString(5, locationId);
            requestAllocation.setString(6, "Waiting");
            requestAllocation.setString(7, date);

            int affectedRows = requestAllocation.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when requesting asset "+type+" by "+empCode);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Updates the status(accepted/rejected) of the request as decided by the admin
    public boolean acceptRejectAllocation(String empCode, String serial, String status) {
        try {
            if(status.equalsIgnoreCase("reject"))
                serial = "";
            PreparedStatement accRejAllocation = conn.prepareStatement(UPDATE_STATUS);
            accRejAllocation.setString(2, empCode);
            accRejAllocation.setString(3, serial);
            if(status.equalsIgnoreCase("accept"))
                accRejAllocation.setString(1, "Accepted");
            else
                accRejAllocation.setString(1, "Rejected");

            int affectedRows = accRejAllocation.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating allocation status of "+serial+" by "+empCode);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Request made by the employee on clearance of the IT asset - Returns true if successfully placed, else returns false
    public boolean updateStatusOnReturnRequest(String empCode, String serial) {
        try {
            PreparedStatement clearanceUpdate = conn.prepareStatement(UPDATE_STATUS);
            clearanceUpdate.setString(1, "Clearance Waiting");
            clearanceUpdate.setString(2, empCode);
            clearanceUpdate.setString(3, serial);

            int affectedRows = clearanceUpdate.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating clearance allocation status of "+serial+" by "+empCode);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Updates the status (cleared, not cleared) of the asset as decided by the admin - Returns true if successfully updated, else returns false
    public boolean updateStatusAndRemarksOnReturn(String empCode, String serial, String status, String remarks) {
        try {
            PreparedStatement accRejClearance = conn.prepareStatement(UPDATE_STATUS);
            PreparedStatement updateRemarks = conn.prepareStatement(UPDATE_REMARKS);
            accRejClearance.setString(2, empCode);
            accRejClearance.setString(3, serial);
            updateRemarks.setString(2, empCode);
            updateRemarks.setString(3, serial);
            if(status.equalsIgnoreCase("accept")) {
                accRejClearance.setString(1, "Cleared");
                updateRemarks.setString(1, "No damages");
            }
            else {
                accRejClearance.setString(1, "Un-Cleared");
                updateRemarks.setString(1, remarks);
            }

            int affectedRows = accRejClearance.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating allocation status of "+serial+" by "+empCode);

            affectedRows = updateRemarks.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating allocation remarks of "+serial+" by "+empCode);

            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }
}