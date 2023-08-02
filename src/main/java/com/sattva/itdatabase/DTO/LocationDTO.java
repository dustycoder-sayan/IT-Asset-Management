package com.sattva.itdatabase.DTO;

public class LocationDTO {
    String city, location, space, locationId;

    public LocationDTO(String locationId, String city, String location, String space) {
        this.locationId = locationId;
        this.city = city;
        this.location = location;
        this.space = space;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getLocationId() {
        return locationId;
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
