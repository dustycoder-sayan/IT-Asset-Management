package com.sattva.itdatabase.DAO;

import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO implements DatabaseConstants {
    private final Connection conn;            // Database Connection

    // SQL queries
    private static final String INSERT_EMPLOYEE = "INSERT INTO "+EMPLOYEE_TABLE+"("+EMPLOYEE_CODE+","+EMPLOYEE_PASSWORD+","+
            EMPLOYEE_NAME+","+EMPLOYEE_COMPANY+","+EMPLOYEE_DESIGNATION+","+EMPLOYEE_CONTACT+","+
            EMPLOYEE_EMAIL+","+EMPLOYEE_DEPT_ID+","+EMPLOYEE_REPRT_MNGR+","+EMPLOYEE_JOIN+") VALUES(?,?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE_EMPLOYEE_RESIGNATION = "UPDATE "+EMPLOYEE_TABLE+" SET "+EMPLOYEE_RESIGN+"=? " +
            "WHERE "+EMPLOYEE_CODE+"=?";
    private static final String UPDATE_EMPLOYEE_COMPANY = "UPDATE "+EMPLOYEE_TABLE+" SET "+EMPLOYEE_COMPANY+"=? " +
            "WHERE "+EMPLOYEE_CODE+"=?";
    private static final String UPDATE_EMPLOYEE_DESGN = "UPDATE "+EMPLOYEE_TABLE+" SET "+EMPLOYEE_DESIGNATION+"=? " +
            "WHERE "+EMPLOYEE_CODE+"=?";
    private static final String UPDATE_EMPLOYEE_CONTACT = "UPDATE "+EMPLOYEE_TABLE+" SET "+EMPLOYEE_CONTACT+"=? " +
            "WHERE "+EMPLOYEE_CODE+"=?";
    private static final String UPDATE_EMPLOYEE_DEPTID = "UPDATE "+EMPLOYEE_TABLE+" SET "+EMPLOYEE_DEPT_ID+"=? " +
            "WHERE "+EMPLOYEE_CODE+"=?";
    private static final String UPDATE_EMPLOYEE_REPRTMGRCODE = "UPDATE "+EMPLOYEE_TABLE+" SET "+EMPLOYEE_REPRT_MNGR+"=? " +
            "WHERE "+EMPLOYEE_CODE+"=?";
    private static final String EMPLOYEE_EXISTS = "SELECT "+EMPLOYEE_NAME+" FROM "+EMPLOYEE_TABLE+" WHERE "+
            EMPLOYEE_CODE+"=?";
    private static final String EMPLOYEE_RESIGNED = "SELECT "+EMPLOYEE_RESIGN+" FROM "+EMPLOYEE_TABLE+" WHERE "+
            EMPLOYEE_CODE+"=?";
    private static final String LOGIN = "SELECT "+EMPLOYEE_COMPANY+" FROM "+EMPLOYEE_TABLE+" WHERE "+EMPLOYEE_CODE+"=? AND " +
            EMPLOYEE_PASSWORD+"=?";

    // Constructor to accept database connection
    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    // Database operations

    // returns true if code and password match, else false
    public String login(String code, String password) {
        try {
            PreparedStatement login = conn.prepareStatement(LOGIN);
            login.setString(1, code);
            login.setString(2, password);
            ResultSet results = login.executeQuery();
            if(results.next())
                return results.getString(1);
            return null;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    // Returns true if employee exists, else returns false
    public boolean employeeExists(String code) {
        try {
            PreparedStatement employeeExists = conn.prepareStatement(EMPLOYEE_EXISTS);
            employeeExists.setString(1, code);      // Replacing SQL query String with necessary value

            ResultSet results = employeeExists.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if employee has resigned, else returns false
    public boolean employeeResigned(String code) {
        try {
            PreparedStatement employeeResigned = conn.prepareStatement(EMPLOYEE_RESIGNED);
            employeeResigned.setString(1, code);

            ResultSet results = employeeResigned.executeQuery();
            return results.next();
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // Returns true if employee could be successfully inserted, else returns false
    public boolean insertEmployee(String code, String password, String name, String company, String designation,
                                  String contact, String emailId, String deptId, String reprtMgrCode, String joiningDate) {
        if(employeeExists(code))
            return true;
        try {
            PreparedStatement insertEmployee = conn.prepareStatement(INSERT_EMPLOYEE);
            insertEmployee.setString(1, code);
            insertEmployee.setString(2, password);
            insertEmployee.setString(3, name);
            insertEmployee.setString(4, company);
            insertEmployee.setString(5, designation);
            insertEmployee.setString(6, contact);
            insertEmployee.setString(7, emailId);
            insertEmployee.setString(8, deptId);
            insertEmployee.setString(9, reprtMgrCode);
            insertEmployee.setString(10, joiningDate);

            int affectedRows = insertEmployee.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when inserting employee details of "+name);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    // For all updates, returns true if update was successful, else returns false
    public boolean updateEmployeeResignation(String code, String resignDate) {
        try {
            PreparedStatement updateEmployeeResignation = conn.prepareStatement(UPDATE_EMPLOYEE_RESIGNATION);
            updateEmployeeResignation.setString(1, resignDate);
            updateEmployeeResignation.setString(2, code);

            int affectedRows = updateEmployeeResignation.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating resignation date of employee "+code);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public boolean updateEmployeeCompany(String code, String company) {
        try {
            PreparedStatement updateEmployeeCompany = conn.prepareStatement(UPDATE_EMPLOYEE_COMPANY);
            updateEmployeeCompany.setString(1, company);
            updateEmployeeCompany.setString(2, code);

            int affectedRows = updateEmployeeCompany.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating company of employee "+code);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public boolean updateEmployeeDesgn(String code, String desgn) {
        try {
            PreparedStatement updateEmployeeDesgn = conn.prepareStatement(UPDATE_EMPLOYEE_DESGN);
            updateEmployeeDesgn.setString(1, desgn);
            updateEmployeeDesgn.setString(2, code);

            int affectedRows = updateEmployeeDesgn.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating designation of employee "+code);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public boolean updateEmployeeContact(String code, String contact) {
        try {
            PreparedStatement updateEmployeeContact = conn.prepareStatement(UPDATE_EMPLOYEE_CONTACT);
            updateEmployeeContact.setString(1, contact);
            updateEmployeeContact.setString(2, code);

            int affectedRows = updateEmployeeContact.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating contact of employee "+code);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public boolean updateEmployeeDeptId(String code, String deptId) {
        try {
            PreparedStatement updateEmployeeDeptId = conn.prepareStatement(UPDATE_EMPLOYEE_DEPTID);
            updateEmployeeDeptId.setString(1, deptId);
            updateEmployeeDeptId.setString(2, code);

            int affectedRows = updateEmployeeDeptId.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating department of employee "+code);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }

    public boolean updateEmployeeReprtMgrId(String code, String reprtMgrId) {
        try {
            PreparedStatement updateEmployeeReprtMgr = conn.prepareStatement(UPDATE_EMPLOYEE_REPRTMGRCODE);
            updateEmployeeReprtMgr.setString(1, reprtMgrId);
            updateEmployeeReprtMgr.setString(2, code);

            int affectedRows = updateEmployeeReprtMgr.executeUpdate();
            if(affectedRows != 1)
                throw new SQLException("More than one row affected when updating reporting manager of employee "+code);
            return true;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return false;
        }
    }
}
