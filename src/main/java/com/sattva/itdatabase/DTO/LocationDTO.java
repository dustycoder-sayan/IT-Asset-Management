package com.sattva.itdatabase.DTO;

public class LocationDTO {
    String city, location, space;

    public LocationDTO(String city, String location, String space) {
        this.city = city;
        this.location = location;
        this.space = space;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setSpace(String space) {
        this.space = space;
    }

    public String getCity() {
        return city;
    }

    public String getLocation() {
        return location;
    }

    public String getSpace() {
        return space;
    }
}
