package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;

public class ReadingExternalModel implements Serializable {

    private String meterId;

    private String coreSPID;

    private String meterReadDate;

    private String rolloverIndicator;

    private String rolloverFlag;

    private Boolean approvedForBilling;

    private String meterSerial;

    private Integer reading;

    public String getMeterId() {
        return meterId;
    }

    public void setMeterId(String meterId) {
        this.meterId = meterId;
    }

    public String getCoreSPID() {
        return coreSPID;
    }

    public void setCoreSPID(String coreSPID) {
        this.coreSPID = coreSPID;
    }

    public String getMeterReadDate() {
        return meterReadDate;
    }

    public void setMeterReadDate(String meterReadDate) {
        this.meterReadDate = meterReadDate;
    }

    public String getRolloverIndicator() {
        return rolloverIndicator;
    }

    public void setRolloverIndicator(String rolloverIndicator) {
        this.rolloverIndicator = rolloverIndicator;
    }

    public String getRolloverFlag() {
        return rolloverFlag;
    }

    public void setRolloverFlag(String rolloverFlag) {
        this.rolloverFlag = rolloverFlag;
    }

    public Boolean getApprovedForBilling() {
        return approvedForBilling;
    }

    public void setApprovedForBilling(Boolean approvedForBilling) {
        this.approvedForBilling = approvedForBilling;
    }

    public String getMeterSerial() {
        return meterSerial;
    }

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public Integer getReading() {
        return reading;
    }

    public void setReading(Integer reading) {
        this.reading = reading;
    }
}
