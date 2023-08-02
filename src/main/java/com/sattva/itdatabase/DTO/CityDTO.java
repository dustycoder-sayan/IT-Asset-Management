package com.sattva.itdatabase.DTO;

public class CityDTO {
    String cityName;

    public CityDTO(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
