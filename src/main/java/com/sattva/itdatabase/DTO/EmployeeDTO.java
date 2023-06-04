package com.sattva.itdatabase.DTO;

import java.sql.Date;

public class EmployeeDTO {
    private String code, name, company, designation, contact, emailId, deptId, reprtMgrCode;
    private Date joining, resigning;

    public EmployeeDTO(String code, String name, String company, String designation, String contact, String emailId, String deptId, String reprtMgrCode, Date joining, Date resigning) {
        this.code = code;
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

    public void setJoining(Date joining) {
        this.joining = joining;
    }

    public void setResigning(Date resigning) {
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

    public Date getJoining() {
        return joining;
    }

    public Date getResigning() {
        return resigning;
    }
}
