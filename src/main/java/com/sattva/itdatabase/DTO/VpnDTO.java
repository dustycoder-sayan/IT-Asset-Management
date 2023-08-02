package com.sattva.itdatabase.DTO;

public class VpnDTO {
    String code, purpose, application, endDate;

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getPurpose() {
        return purpose;
    }

    public String getApplication() {
        return application;
    }

    public String getEndDate() {
        return endDate;
    }
}
