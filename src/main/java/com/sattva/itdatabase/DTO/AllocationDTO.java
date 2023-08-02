package com.sattva.itdatabase.DTO;

import java.sql.Date;

public class AllocationDTO {
    private String empCode, serial, status, remarks, type, subType, locationId;
    private int quantity;
    private int duration;

    private int allocationId;
    private String date;

    public AllocationDTO(int allocationId, String empCode, String serial, String type, String subType, int quantity, String status, String remarks,
                         String date, String locationId, int duration) {
        this.empCode = empCode;
        this.serial = serial;
        this.status = status;
        this.remarks = remarks;
        this.quantity = quantity;
        this.date = date;
        this.type = type;
        this.subType = subType;
        this.locationId = locationId;
        this.allocationId = allocationId;
        this.duration = duration;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public int getAllocationId() {
        return allocationId;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getLocationId() {
        return locationId;
    }

    public int getDuration() {
        return duration;
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

    public String getDate() {
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

    public void setDate(String date) {
        this.date = date;
    }
}
