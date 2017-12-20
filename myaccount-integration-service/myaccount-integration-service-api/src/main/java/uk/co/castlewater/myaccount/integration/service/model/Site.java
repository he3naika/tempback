package uk.co.castlewater.myaccount.integration.service.model;

public class Site {
    private String premisesId;
    private Address address;
    private String premiseDiscriminator;
    private String spid;

    public String getPremisesId() {
        return premisesId;
    }

    public void setPremisesId(String premisesId) {
        this.premisesId = premisesId;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPremiseDiscriminator() {
        return premiseDiscriminator;
    }

    public void setPremiseDiscriminator(String premiseDiscriminator) {
        this.premiseDiscriminator = premiseDiscriminator;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    @Override
    public String toString() {
        return "Site{" +
                "premisesId='" + premisesId + '\'' +
                ", address=" + address +
                ", premiseDiscriminator='" + premiseDiscriminator + '\'' +
                ", spid='" + spid + '\'' +
                '}';
    }
}
