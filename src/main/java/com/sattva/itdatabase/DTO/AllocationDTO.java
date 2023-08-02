package com.sattva.itdatabase.DTO;

import java.sql.Date;

public class AllocationDTO {
    private String empCode, serial, status, remarks, type, subType, locationId;
    String datacardNum, datacardJust, sapProcess, sapFunc;
    String vpnProcess, vpnApplication, newEmail;
    String sapId, sapAction;
    private int quantity;
    private int duration;
    private String vpnAction, vpnId;
    private int allocationId;
    private String date;

    public AllocationDTO(int allocationId, String empCode, String serial, String type, String subType, int quantity,
                         String status, String remarks, String date, String locationId,
                         String datacardNum, String datacardJust, String sapId, String sapAction,
                         String sapProcess, String sapFunc, String vpnAction, String vpnId, String vpnProcess,
                         String vpnApplication, String newEmail, int duration) {
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
        this.datacardNum = datacardNum;
        this.datacardJust = datacardJust;
        this.sapProcess = sapProcess;
        this.sapFunc = sapFunc;
        this.vpnAction = vpnAction;
        this.vpnId = vpnId;
        this.vpnProcess = vpnProcess;
        this.vpnApplication = vpnApplication;
        this.newEmail = newEmail;
        this.duration = duration;
        this.sapId = sapId;
        this.sapAction = sapAction;
    }

    public void setSapId(String sapId) {
        this.sapId = sapId;
    }

    public void setSapAction(String sapAction) {
        this.sapAction = sapAction;
    }

    public String getSapId() {
        return sapId;
    }

    public String getSapAction() {
        return sapAction;
    }

    public void setVpnAction(String vpnAction) {
        this.vpnAction = vpnAction;
    }

    public void setVpnId(String vpnId) {
        this.vpnId = vpnId;
    }

    public String getVpnAction() {
        return vpnAction;
    }

    public String getVpnId() {
        return vpnId;
    }

    public void setDatacardNum(String datacardNum) {
        this.datacardNum = datacardNum;
    }

    public void setDatacardJust(String datacardJust) {
        this.datacardJust = datacardJust;
    }

    public void setSapProcess(String sapProcess) {
        this.sapProcess = sapProcess;
    }

    public void setSapFunc(String sapFunc) {
        this.sapFunc = sapFunc;
    }

    public void setVpnProcess(String vpnProcess) {
        this.vpnProcess = vpnProcess;
    }

    public void setVpnApplication(String vpnApplication) {
        this.vpnApplication = vpnApplication;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public void setAllocationId(int allocationId) {
        this.allocationId = allocationId;
    }

    public String getDatacardNum() {
        return datacardNum;
    }

    public String getDatacardJust() {
        return datacardJust;
    }

    public String getSapProcess() {
        return sapProcess;
    }

    public String getSapFunc() {
        return sapFunc;
    }

    public String getVpnProcess() {
        return vpnProcess;
    }

    public String getVpnApplication() {
        return vpnApplication;
    }

    public String getNewEmail() {
        return newEmail;
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
