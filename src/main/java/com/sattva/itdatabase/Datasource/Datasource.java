package com.sattva.itdatabase.Datasource;

import com.sattva.itdatabase.DAO.DepartmentDAO;
import com.sattva.itdatabase.DAO.EmployeeDAO;
import com.sattva.itdatabase.DTO.*;
import com.sattva.itdatabase.Database.DatabaseConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Datasource implements DatabaseConstants {
    private final Connection conn;

    private static final String GET_ALL_CITIES = "SELECT * FROM "+CITY_TABLE;
    private static final String GET_ALL_SPACES = "SELECT * FROM "+SPACE_TABLE;
    private static final String GET_ALL_LOCATIONS = "SELECT * FROM "+LOCATION2_TABLE;
    private static final String GET_ALL_DEPTS = "SELECT * FROM "+DEPARTMENT_TABLE;
    private static final String GET_ALL_VENDORS = "SELECT * FROM "+VENDOR_TABLE;
    private static final String GET_ALL_EMPLOYEES = "SELECT * FROM "+EMPLOYEE_TABLE;
    private static final String GET_EMPLOYEE_DETAILS = "SELECT * FROM "+EMPLOYEE_TABLE+" WHERE "+EMPLOYEE_CODE+"=?";
    private static final String GET_EMPLOYEE_REPRT_MGR = "SELECT "+EMPLOYEE_REPRT_MNGR+" FROM "+EMPLOYEE_TABLE+" WHERE " +
            EMPLOYEE_CODE+"=?";
    private static final String GET_EMPLOYEE_BY_COMPANY = "SELECT * FROM "+EMPLOYEE_TABLE+" WHERE "+EMPLOYEE_COMPANY+"=?";
    private static final String GET_EMPLOYEE_BY_DEPT = "SELECT * FROM "+EMPLOYEE_TABLE+" WHERE "+EMPLOYEE_DEPT_ID+"=?";
    private static final String GET_ALL_ASSETS = "SELECT * FROM "+ASSETS_TABLE;
    private static final String GET_SUBTYPE_ASSETS = "SELECT * FROM "+ASSETS_TABLE+" WHERE "+ASSETS_TYPE+"=? AND "
            +ASSETS_SUBTYPE+"=?";
    private static final String GET_VENDOR_ASSETS = "SELECT * FROM "+ASSETS_TABLE+" WHERE "+ASSETS_VENDOR+"=?";
    private static final String GET_ALL_ALLOCATION = "SELECT * FROM "+ALLOCATION_TABLE;

    public Datasource(Connection conn) {
        this.conn = conn;
    }

    public ArrayList<CityDTO> getAllCities() {
        try {
            PreparedStatement getAllCities = conn.prepareStatement(GET_ALL_CITIES);
            ResultSet results = getAllCities.executeQuery();
            ArrayList<CityDTO> cities = new ArrayList<>();
            while(results.next()) {
                cities.add(new CityDTO(results.getString(1)));
            }
            return cities;
        } catch (SQLException e) {
            System.out.println("Exception Occurred: "+e.getMessage());
            return null;
        }
    }

    public ArrayList<Location2DTO> getAllLocations() {
        try {
            PreparedStatement getAllLocations = conn.prepareStatement(GET_ALL_LOCATIONS);
            ResultSet results = getAllLocations.executeQuery();
            ArrayList<Location2DTO> locations = new ArrayList<>();
            while(results.next()) {
                locations.add(new Location2DTO(results.getString(1)));
            }
            return locations;
        } catch (SQLException e) {
            System.out.println("Exception Occured: "+e.getMessage());
            return null;
        }
    }

    public ArrayList<SpaceDTO> getAllSpaces() {
        try {
            PreparedStatement getAllSpaces = conn.prepareStatement(GET_ALL_SPACES);
            ResultSet results = getAllSpaces.executeQuery();
            ArrayList<SpaceDTO> spaces = new ArrayList<>();
            while(results.next()) {
                spaces.add(new SpaceDTO(results.getString(1)));
            }
            return spaces;
        } catch (SQLException e) {
            System.out.println("Exception Occured: "+e.getMessage());
            return null;
        }
    }

    public ArrayList<DepartmentDTO> getAllDept() {
        try {
            PreparedStatement getAllDepts = conn.prepareStatement(GET_ALL_DEPTS);
            ResultSet results = getAllDepts.executeQuery();
            ArrayList<DepartmentDTO> dept = new ArrayList<>();
            while(results.next())
                dept.add(new DepartmentDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4)));
            return dept;
        } catch (SQLException e) {
            System.out.println("Exception Occured: "+e.getMessage());
            return null;
        }
    }

    public ArrayList<VendorDTO> getAllVendors() {
        try {
            PreparedStatement getAllVendors = conn.prepareStatement(GET_ALL_VENDORS);
            ResultSet results = getAllVendors.executeQuery();
            ArrayList<VendorDTO> vendors = new ArrayList<>();
            while(results.next())
                vendors.add(new VendorDTO(results.getInt(1), results.getString(2), results.getString(3), results.getString(4), results.getString(5)));
            return vendors;
        } catch (SQLException e) {
            System.out.println("Exception Occured: "+e.getMessage());
            return null;
        }
    }

    // Single Employee Detail
    public EmployeeDTO getEmployeeDetails(String code) {
        try {
            if(!new EmployeeDAO(conn).employeeExists(code))
                throw new SQLException("No such employee exists");
            PreparedStatement employeeDetails = conn.prepareStatement(GET_EMPLOYEE_DETAILS);
            employeeDetails.setString(1, code);
            ResultSet results = employeeDetails.executeQuery();
            if(results.next())
                return new EmployeeDTO(code, results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getString(10),
                        results.getString(11), results.getString(8), results.getString(9));
            else
                throw new SQLException("No Such Employee Found");
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Employee data: "+e.getMessage());
            return null;
        }
    }

    // Get all employee Details
    public ArrayList<EmployeeDTO> getAllEmployeeDetails() {
        try {
            PreparedStatement employeeDetails = conn.prepareStatement(GET_ALL_EMPLOYEES);
            ResultSet results = employeeDetails.executeQuery();
            ArrayList<EmployeeDTO> employee = new ArrayList<>();
            while(results.next())
                employee.add(new EmployeeDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getString(10),
                        results.getString(11), results.getString(8), results.getString(9)));
            return employee;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Employee data: "+e.getMessage());
            return null;
        }
    }

    // Get details about reporting manager of given employee
    public EmployeeDTO getEmployeeReprtMgr(String code) {
        try {
            if(!new EmployeeDAO(conn).employeeExists(code))
                throw new SQLException("No such employee found");
            PreparedStatement employeeDetails = conn.prepareStatement(GET_EMPLOYEE_REPRT_MGR);
            employeeDetails.setString(1, code);
            ResultSet results = employeeDetails.executeQuery();
            String reprtMgrCode="";
            if(results.next())
                reprtMgrCode = results.getString(1);
            else
                throw new SQLException("No reporting manager assigned");
            return getEmployeeDetails(reprtMgrCode);
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Employee data: "+e.getMessage());
            return null;
        }
    }

    // get Employee by Company
    public ArrayList<EmployeeDTO> getEmployeeByCompany(String company) {
        try {
            PreparedStatement employeeDetails = conn.prepareStatement(GET_EMPLOYEE_BY_COMPANY);
            employeeDetails.setString(1,company);
            ResultSet results = employeeDetails.executeQuery();
            ArrayList<EmployeeDTO> employee = new ArrayList<>();
            while(results.next())
                employee.add(new EmployeeDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getString(10),
                        results.getString(11), results.getString(8), results.getString(9)));
            return employee;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Employee data: "+e.getMessage());
            return null;
        }
    }

    // Get Employee by Dept Name and location of dept
    public ArrayList<EmployeeDTO> getEmployeeByDept(String deptName, String deptLocationId) {
        try {
            String deptId = new DepartmentDAO(conn).getDeptId(deptName, deptLocationId);
            PreparedStatement employeeDetails = conn.prepareStatement(GET_EMPLOYEE_BY_DEPT);
            employeeDetails.setString(1,deptId);
            ResultSet results = employeeDetails.executeQuery();
            ArrayList<EmployeeDTO> employee = new ArrayList<>();
            while(results.next())
                employee.add(new EmployeeDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getString(10),
                        results.getString(11), results.getString(8), results.getString(9)));
            return employee;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Employee data: "+e.getMessage());
            return null;
        }
    }

    // Get all assets
    public ArrayList<AssetsDTO> getAllAssets() {
        try {
            PreparedStatement allAssets = conn.prepareStatement(GET_ALL_ASSETS);
            ArrayList<AssetsDTO> assets = new ArrayList<>();
            ResultSet results = allAssets.executeQuery();
            while(results.next()) {
                assets.add(new AssetsDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getInt(8), results.getInt(9),
                        results.getInt(10)));
            }
            return assets;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Asset data: "+e.getMessage());
            return null;
        }
    }

    // Get Assets Based on Type and Subtype
    public ArrayList<AssetsDTO> getAssetsBasedOnTypeAndSubtype(String type, String subType) {
        try {
            PreparedStatement allAssets = conn.prepareStatement(GET_SUBTYPE_ASSETS);
            allAssets.setString(1,type);
            allAssets.setString(2,subType);
            ArrayList<AssetsDTO> assets = new ArrayList<>();
            ResultSet results = allAssets.executeQuery();
            while(results.next()) {
                assets.add(new AssetsDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getInt(8), results.getInt(9),
                        results.getInt(10)));
            }
            return assets;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Asset data: "+e.getMessage());
            return null;
        }
    }

    // Get Assets Based on Vendors
    public ArrayList<AssetsDTO> getAssetsBasedOnVendor(int vendorId) {
        try {
            PreparedStatement allAssets = conn.prepareStatement(GET_VENDOR_ASSETS);
            allAssets.setInt(1,vendorId);
            ArrayList<AssetsDTO> assets = new ArrayList<>();
            ResultSet results = allAssets.executeQuery();
            while(results.next()) {
                assets.add(new AssetsDTO(results.getString(1), results.getString(2), results.getString(3), results.getString(4),
                        results.getString(5), results.getString(6), results.getString(7), results.getInt(8), results.getInt(9),
                        results.getInt(10)));
            }
            return assets;
        } catch (SQLException e) {
            System.out.println("Exception Occurred while fetching Asset data: "+e.getMessage());
            return null;
        }
    }

    // TODO: Write queries and functions for allocation by IT Assets, Email, Datacard, VPN, SAP, all
}
