package com.sattva.itdatabase.DTO;

public class VendorDTO {
    private String name, phone, location, emailId;
    private int vendorId;

    public VendorDTO(String name, String phone, String location, String emailId, int vendorId, double credit, double debit) {
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
