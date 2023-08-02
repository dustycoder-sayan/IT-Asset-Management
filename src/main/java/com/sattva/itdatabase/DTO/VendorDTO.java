package com.sattva.itdatabase.DTO;

public class VendorDTO {
    private String name, phone, location, emailId;
    private int vendorId;

    public VendorDTO(int vendorId, String name, String phone, String location, String emailId) {
        this.name = name;
        this.phone = phone;
        this.location = location;
        this.emailId = emailId;
        this.vendorId = vendorId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getLocation() {
        return location;
    }

    public String getEmailId() {
        return emailId;
    }

    public int getVendorId() {
        return vendorId;
    }
}
