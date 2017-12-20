package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;

public class CreateReadingRequest implements Serializable {

    private Integer read;

    private String meterSerialNumber;

    public Integer getRead() {
        return read;
    }

    public void setRead(Integer read) {
        this.read = read;
    }

    public String getMeterSerialNumber() {
        return meterSerialNumber;
    }

    public void setMeterSerialNumber(String meterSerialNumber) {
        this.meterSerialNumber = meterSerialNumber;
    }
}
