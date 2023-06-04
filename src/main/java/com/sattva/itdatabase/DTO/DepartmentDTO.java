package com.sattva.itdatabase.DTO;

public class DepartmentDTO {
    private String deptId, name, city, hodCode;

    public DepartmentDTO(String deptId, String name, String city, String hodCode) {
        this.deptId = deptId;
        this.name = name;
        this.city = city;
        this.hodCode = hodCode;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getCity() {
        return city;
    }

    public String getHodCode() {
        return hodCode;
    }
}
