package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AllocationDAO implements DatabaseConstants {
    private final Connection conn;      // Database Connection

    // SQL queries
    private static final String REQUEST_IT_ALLOCATION = "INSERT INTO "+ALLOCATION_TABLE+"("+ALLOCATION_EMP_CODE+","
            +ALLOCATION_TYPE+","+ALLOCATION_SUBTYPE+","+ALLOCATION_SERIAL+","+ALLOCATION_NEW_EMAIL+","
            +ALLOCATION_DATACARD_NUM+","+ALLOCATION_DATACARD_JUST+"," +ALLOCATION_QUANTITY+","+ALLOCATION_LOCATION_ID
            +","+ALLOCATION_STATUS+","+ ALLOCATION_DATE+","+ALLOCATION_DURATION+") VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String REQUEST_VPN_ALLOCATION = "INSERT INTO "+ALLOCATION_TABLE+"("+
            ALLOCATION_EMP_CODE+","+ALLOCATION_TYPE+","+ALLOCATION_LOCATION_ID+","+ALLOCATION_VPN_ACTION+","
            +ALLOCATION_VPN_ID+","+ALLOCATION_VPN_PURPOSE+","+ALLOCATION_VPN_APPLICATION+","+ALLOCATION_DURATION+","
            +ALLOCATION_DATE+")" + " VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String REQUEST_SAP_ALLOCATION = "INSERT INTO "+ALLOCATION_TABLE+"("
            +ALLOCATION_EMP_CODE+","+ALLOCATION_TYPE+","+ALLOCATION_LOCATION_ID+","+ALLOCATION_SAP_ACTION+","
            +ALLOCATION_SAP_ID+","+ALLOCATION_SAP_FUNCTION+","+ALLOCATION_SAP_PROCESS+","+ALLOCATION_DATE+
            ")"+" VALUES(?,?,?,?,?,?,?,?)";
    private static final String UPDATE_STATUS = "UPDATE "+ALLOCATION_TABLE+" SET "+ALLOCATION_STATUS+"=? WHERE "
            +ALLOCATION_ID+"=?";
    private static final String UPDATE_SERIAL = "UPDATE "+ALLOCATION_TABLE+" SET "+ALLOCATION_SERIAL+"=? WHERE "
            +ALLOCATION_ID+"=?";
    private static final String UPDATE_REMARKS = "UPDATE "+ALLOCATION_TABLE+" SET "+ALLOCATION_REMARKS+"=? WHERE "
            +ALLOCATION_EMP_CODE+"=? AND "+ALLOCATION_SERIAL+"=?";

    // Constructor to accept database connection
    public AllocationDAO(Connection conn) {
        this.conn = conn;
    }

    // Returns true if request made by employee was successfully updated in db, else returns false
    public boolean requestAllocation(String empCode, String type, String subType, int quantity, String newEmail,
                                     String datacardNum, String datacardJust, String locationId, String date,
                                     int duration) {
        try {
            PreparedStatement requestAllocation = conn.prepareStatement(REQUEST_IT_ALLOCATION);
            requestAllocation.setString(1, empCode);
            requestAllocation.setString(2, type);
            requestAllocation.setString(3, subType);
            requestAllocation.setString(4, "");
            requestAllocation.setString(5, newEmail);
            requestAllocation.setString(6, datacardNum);
            requestAllocation.setString(7, datacardJust);
            requestAllocation.setInt(8, quantity);
            requestAllocation.setString(9, locationId);
            requestAllocation.setString(10, "WAITING");
            requestAllocation.setString(11, date);
            requestAllocation.setInt(12, duration);

            int affectedRows = requestAllocation.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when requesting asset "+type+" by "+empCode);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while inserting allocation: "+e.getMessage());
            return false;
        }
    }

    // Returns true if request made by employee was successfully updated in db, else returns false
    public boolean requestVpn(String empCode, String locationId, String action, String vpnId, String purpose,
                              String application, int duration, String date) {
        try {
            PreparedStatement requestVpn = conn.prepareStatement(REQUEST_VPN_ALLOCATION);
            requestVpn.setString(1, empCode);
            requestVpn.setString(2, "VPN");
            requestVpn.setString(3, locationId);
            requestVpn.setString(4, action);
            requestVpn.setString(5, vpnId);
            requestVpn.setString(6, purpose);
            requestVpn.setString(7, application);
            requestVpn.setInt(8, duration);
            requestVpn.setString(9, date);

            int affectedRows = requestVpn.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when requesting VPN");
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while inserting allocation (VPN): "+e.getMessage());
            return false;
        }
    }

    // Returns true if request made by employee was successfully updated in db, else returns false
    public boolean requestSap(String empCode, String locationId, String action, String id, String processOwner,
                              String function, String date) {
        try {
            PreparedStatement requestSap = conn.prepareStatement(REQUEST_SAP_ALLOCATION);
            requestSap.setString(1, empCode);
            requestSap.setString(2, "SAP");
            requestSap.setString(3, locationId);
            requestSap.setString(4, action);
            requestSap.setString(5, id);
            requestSap.setString(6, function);
            requestSap.setString(7, processOwner);
            requestSap.setString(8, date);

            int affectedRows = requestSap.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when requesting SAP");
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while inserting allocation (SAP): "+e.getMessage());
            return false;
        }
    }

    // Updates both serial and status
    // Updates the status(accepted/rejected) of the request as decided by the admin
    // and sets serial if accepted, else sets serial as ""
    public boolean updateStatusAndSerial(int allocationId, String status, String serial) {
        try {
            status = status.toLowerCase();
            if(status.contains("reject"))
                serial = "";
            PreparedStatement updateStatus = conn.prepareStatement(UPDATE_STATUS);
            updateStatus.setInt(2,allocationId);
            PreparedStatement updateSerial = conn.prepareStatement(UPDATE_SERIAL);
            updateSerial.setInt(2,allocationId);
            if(status.contains("accept"))
                updateStatus.setString(1, "Accepted");
            else
                updateStatus.setString(1, "Rejected");

            updateSerial.setString(1, serial);
            int affectedRows = updateStatus.executeUpdate();
            int affectedRows2 = updateSerial.executeUpdate();
            if(affectedRows != 1 || affectedRows2 != 1)
                throw new SQLException("More than one row affected when updating allocation status and serial");
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Request made by the employee on clearance of the IT asset - Returns true if successfully placed, else returns false
    public boolean updateStatusOnReturnRequest(int allocId) {
        try {
            PreparedStatement clearanceUpdate = conn.prepareStatement(UPDATE_STATUS);
            clearanceUpdate.setString(1, "Clearance Waiting");
            clearanceUpdate.setInt(2, allocId);

            int affectedRows = clearanceUpdate.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating clearance allocation status of "+allocId);
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
