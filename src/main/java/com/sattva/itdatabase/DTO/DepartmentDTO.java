package com.sattva.itdatabase.DTO;

public class DepartmentDTO {
    private String deptId, name, locationId, hodCode;

    public DepartmentDTO(String deptId, String name, String locationId, String hodCode) {
        this.deptId = deptId;
        this.name = name;
        this.locationId = locationId;
        this.hodCode = hodCode;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setHodCode(String hodCode) {
        this.hodCode = hodCode;
    }

    public String getDeptId() {
        return deptId;
    }

    public String getName() {
        return name;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getHodCode() {
        return hodCode;
    }
}
