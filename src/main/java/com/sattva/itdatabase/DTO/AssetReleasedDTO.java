package com.sattva.itdatabase.DTO;

public class AssetReleasedDTO {
    private String code, dept, city, space, type, subType, serial, date;

    public AssetReleasedDTO(String code, String dept, String city, String space, String type, String subType, String serial, String date) {
        this.code = code;
        this.dept = dept;
        this.city = city;
        this.space = space;
        this.type = type;
        this.subType = subType;
        this.serial = serial;
        this.date = date;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSubType(String subType) {
        this.subType = subType;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCode() {
        return code;
    }

    public String getDept() {
        return dept;
    }

    public String getCity() {
        return city;
    }

    public String getSpace() {
        return space;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getSerial() {
        return serial;
    }

    public String getDate() {
        return date;
    }
}
