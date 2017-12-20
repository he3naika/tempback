package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;

/**
 * @author Anatol Sialitski
 */
public class MeterExternalModel implements Serializable {

    private String meterMake;

    private String meterSerial;

    private String coreSPID;

    private String buildingName;

    private String buildingNumber;

    private String postcode;

    public String getMeterMake() {
        return meterMake;
    }

    public void setMeterMake(String meterMake) {
        this.meterMake = meterMake;
    }

    public String getMeterSerial() {
        return meterSerial;
    }

    public void setMeterSerial(String meterSerial) {
        this.meterSerial = meterSerial;
    }

    public String getCoreSPID() {
        return coreSPID;
    }

    public void setCoreSPID(String coreSPID) {
        this.coreSPID = coreSPID;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String buildingNumber) {
        this.buildingNumber = buildingNumber;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

}
