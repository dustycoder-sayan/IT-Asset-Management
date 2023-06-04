package com.sattva.itdatabase.DTO;

import java.sql.Date;

public class AllocationDTO {
    private String empCode, serial, status, remarks;
    private int quantity;
    private Date date;

    public AllocationDTO(String empCode, String serial, String status, String remarks, int quantity, Date date) {
        this.empCode = empCode;
        this.serial = serial;
        this.status = status;
        this.remarks = remarks;
        this.quantity = quantity;
        this.date = date;
    }

    public String getEmpCode() {
        return empCode;
    }

    public String getSerial() {
        return serial;
    }

    public String getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
