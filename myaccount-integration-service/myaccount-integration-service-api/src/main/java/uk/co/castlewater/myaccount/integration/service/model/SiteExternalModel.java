package uk.co.castlewater.myaccount.integration.service.model;

public class SiteExternalModel {

    private Integer premiseProviderId;

    private String wholesalerId;

    private Integer premiseDiscriminator;

    private String coreSPID;

    private String buildingName;

    private String buildingNumber;

    private String thoroughfareName;

    private String postCode;

    public Integer getPremiseProviderId() {
        return premiseProviderId;
    }

    public void setPremiseProviderId(Integer premiseProviderId) {
        this.premiseProviderId = premiseProviderId;
    }

    public String getWholesalerId() {
        return wholesalerId;
    }

    public void setWholesalerId(String wholesalerId) {
        this.wholesalerId = wholesalerId;
    }

    public Integer getPremiseDiscriminator() {
        return premiseDiscriminator;
    }

    public void setPremiseDiscriminator(Integer premiseDiscriminator) {
        this.premiseDiscriminator = premiseDiscriminator;
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

    public String getThoroughfareName() {
        return thoroughfareName;
    }

    public void setThoroughfareName(String thoroughfareName) {
        this.thoroughfareName = thoroughfareName;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }
}
