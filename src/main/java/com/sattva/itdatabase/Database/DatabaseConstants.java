package com.sattva.itdatabase.Database;

public interface DatabaseConstants {
    String DATABASE_NAME = "itassetmgmt.db";
    String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\LENOVO\\OneDrive\\Documents\\Projects\\IT Asset Management\\src\\main\\java\\com\\sattva\\itdatabase\\Database\\" + DATABASE_NAME;

    String ALLOCATION_TABLE = "ALLOCATION";
    String ASSETS_TABLE = "ASSETS";
    String DEPARTMENT_TABLE = "DEPARTMENT";
    String EMPLOYEE_TABLE = "EMPLOYEE";
    String LOCATION_TABLE = "LOCATION";
    String VENDOR_TABLE = "VENDOR";
    String CITY_TABLE = "CITY";
    String SPACE_TABLE = "SPACE";
    String LOCATION2_TABLE = "LOCATION2";
    String VPN_TABLE = "VPN";
    String SAP_TABLE = "SAP";
    String DATACARD_TABLE = "DATACARD";

    String ALLOCATION_ID = "ALLOCATION_ID";
    String ALLOCATION_EMP_CODE = "EMP_CODE";
    String ALLOCATION_SERIAL = "SERIAL";
    String ALLOCATION_TYPE = "TYPE";
    String ALLOCATION_QUANTITY = "QUANTITY";
    String ALLOCATION_STATUS = "STATUS";
    String ALLOCATION_REMARKS = "REMARKS";
    String ALLOCATION_DATE = "DATE_OF_ALLOCATION";
    String ALLOCATION_LOCATION_ID = "LOCATION_ID";
    String ALLOCATION_DURATION = "DURATION";
    String ALLOCATION_SUBTYPE = "SUBTYPE";
    String ALLOCATION_DATACARD_NUM = "DATACARD_NUM";
    String ALLOCATION_DATACARD_JUST = "DATACARD_JUSTIFICATION";
    String ALLOCATION_SAP_ID = "SAP_ID";
    String ALLOCATION_SAP_ACTION = "SAP_ACTION";
    String ALLOCATION_SAP_PROCESS = "SAP_PROCESS";
    String ALLOCATION_SAP_FUNCTION = "SAP_FUNCTION";
    String ALLOCATION_VPN_ACTION = "VPN_ACTION";
    String ALLOCATION_VPN_ID = "VPN_ID";
    String ALLOCATION_VPN_PURPOSE = "VPN_PURPOSE";
    String ALLOCATION_VPN_APPLICATION = "VPN_APPLICATION";
    String ALLOCATION_NEW_EMAIL = "NEW_EMAIL";

    String ASSETS_SERIAL = "SERIAL";
    String ASSETS_TYPE = "TYPE";
    String ASSETS_NAME = "NAME";
    String ASSETS_MODEL = "MODEL";
    String ASSETS_OS = "OS";
    String ASSETS_CONDITION = "CONDITION";
    String ASSETS_OUTSTOCK = "OUTSTOCK";
    String ASSETS_INSTOCK = "INSTOCK";
    String ASSETS_VENDOR = "VENDOR_ID";
    String ASSETS_SUBTYPE = "SUBTYPE";

    String DEPARTMENT_DEPT_ID = "DEPT_ID";
    String DEPARTMENT_NAME = "NAME";
    String DEPARTMENT_LOCATION_ID = "LOCATION_ID";
    String DEPARTMENT_HOD = "HOD_CODE";

    String EMPLOYEE_CODE = "CODE";
    String EMPLOYEE_NAME = "NAME";
    String EMPLOYEE_COMPANY = "COMPANY";
    String EMPLOYEE_DESIGNATION = "DESGN";
    String EMPLOYEE_CONTACT = "CONTACT";
    String EMPLOYEE_EMAIL = "EMAIL_ID";
    String EMPLOYEE_JOIN = "JOINING_DATE";
    String EMPLOYEE_RESIGN = "RESIGN_DATE";
    String EMPLOYEE_DEPT_ID = "DEPT_ID";
    String EMPLOYEE_REPRT_MNGR = "R_MNGR_CODE";
    String EMPLOYEE_PASSWORD = "PASSWORD";
    String EMPLOYEE_LOCATION_ID = "LOCATION_ID";

    String LOCATION_ID = "LOCATION_ID";
    String LOCATION_CITY = "CITY";
    String LOCATION_LOCATION = "LOCATION";
    String LOCATION_SPACE = "SPACE";

    String VENDOR_ID = "VENDOR_ID";
    String VENDOR_NAME = "NAME";
    String VENDOR_CONTACT = "PHONE";
    String VENDOR_LOCATION = "LOCATION";
    String VENDOR_EMAIL = "EMAIL_ID";

    String CITY_CITY = "CITY_NAME";
    String SPACE_SPACE = "SPACE_NAME";
    String LOCATION2_LOCATION = "LOCATION_NAME";
}
