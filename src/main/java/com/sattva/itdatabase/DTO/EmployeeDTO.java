package com.sattva.itdatabase.DTO;

import java.sql.Date;

public class EmployeeDTO {
    private String code, name, company, designation, contact, emailId, deptId, reprtMgrCode, password;
    private String joining, resigning;

    public EmployeeDTO(String code, String password, String name, String company, String designation, String contact, String emailId, String deptId, String reprtMgrCode, String joining, String resigning) {
        this.code = code;
        this.password = password;
        this.name = name;
        this.company = company;
        this.designation = designation;
        this.contact = contact;
        this.emailId = emailId;
        this.deptId = deptId;
        this.reprtMgrCode = reprtMgrCode;
        this.joining = joining;
        this.resigning = resigning;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setReprtMgrCode(String reprtMgrCode) {
        this.reprtMgrCode = reprtMgrCode;
    }

    public void setJoining(String joining) {
        this.joining = joining;
    }

    public void setResigning(String resigning) {
        this.resigning = resigning;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public String getDesignation() {
        return designation;
    }

    public String getContact() {
        return contact;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getReprtMgrCode() {
        return reprtMgrCode;
    }

    public String getJoining() {
        return joining;
    }

    public String getResigning() {
        return resigning;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
