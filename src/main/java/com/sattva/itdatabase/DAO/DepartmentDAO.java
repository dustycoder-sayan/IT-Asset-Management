package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentDAO implements DatabaseConstants {
    private final Connection conn;      // Database Connection

    // SQL queries
    private static final String INSERT_DEPARTMENT = "INSERT INTO "+DEPARTMENT_TABLE+" VALUES(?,?,?,?)";
    private static final String UPDATE_DEPT_HOD = "UPDATE "+DEPARTMENT_TABLE+" SET "+DEPARTMENT_HOD+"=? WHERE "+DEPARTMENT_DEPT_ID+"=?";
    private static final String DEPT_EXISTS = "SELECT "+DEPARTMENT_DEPT_ID+" FROM "+DEPARTMENT_TABLE+" WHERE "+DEPARTMENT_DEPT_ID+"=?";

    // Constructor to accept database connection
    public DepartmentDAO(Connection conn) {
        this.conn = conn;
    }

    // Returns deptCode if department exists in the given locationId, else returns null
    public String deptExists(String name, String locationId) {
        String deptCode = name + locationId.substring(0, 3);
        try {
            PreparedStatement deptExists = conn.prepareStatement(DEPT_EXISTS);
            deptExists.setString(1, deptCode);

            ResultSet results = deptExists.executeQuery();
            if(results.next())
                return deptCode;
            else
                return null;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    // Returns deptCode if department was successfully inserted for the given location, else returns null
    public String insertDepartment(String name, String locationId, String hodCode) {
        String deptCodeExist;
        if((deptCodeExist=deptExists(name, locationId)) != null)
            return deptCodeExist;
        String deptCode = name + locationId.substring(0, 3);
        try {
            PreparedStatement insertDepartment = conn.prepareStatement(INSERT_DEPARTMENT);
            insertDepartment.setString(1, deptCode);
            insertDepartment.setString(2, name);
            insertDepartment.setString(3, locationId);
            insertDepartment.setString(4, hodCode);

            int affectedRows = insertDepartment.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting department "+name);
            return deptCode;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    // Returns true if department's HOD could be updated successfully, else returns false
    public boolean updateHod(String name, String locationId, String hodCode) {
        String deptCode = name + locationId.substring(0, 3);
        try {
            PreparedStatement updateHod = conn.prepareStatement(UPDATE_DEPT_HOD);
            updateHod.setString(1, hodCode);
            updateHod.setString(2, deptCode);

            int affectedRows = updateHod.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating HOD for department "+name+" in "+locationId);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }
}
