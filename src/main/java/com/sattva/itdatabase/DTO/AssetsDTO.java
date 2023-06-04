package com.sattva.itdatabase.DTO;

public class AssetsDTO {
    private String serial, type, name, model, os, condition;
    private int outStock, inStock, vendorId;

    public AssetsDTO(String serial, String type, String name, String model, String os, String condition, int outStock, int inStock, int vendorId) {
        this.serial = serial;
        this.type = type;
        this.name = name;
        this.model = model;
        this.os = os;
        this.condition = condition;
        this.outStock = outStock;
        this.inStock = inStock;
        this.vendorId = vendorId;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setOutStock(int outStock) {
        this.outStock = outStock;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    public String getSerial() {
        return serial;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getModel() {
        return model;
    }

    public String getOs() {
        return os;
    }

    public String getCondition() {
        return condition;
    }

    public int getOutStock() {
        return outStock;
    }

    public int getInStock() {
        return inStock;
    }

    public int getVendorId() {
        return vendorId;
    }
}
