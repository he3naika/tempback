package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Address implements Serializable {
    private List<String> addressLine = new ArrayList<>();
    private String postCode;
    private String city;

    public List<String> getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(List<String> addressLine) {
        this.addressLine = addressLine;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressLine=" + addressLine +
                ", postCode='" + postCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
