package uk.co.castlewater.myaccount.service.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * The data model for an address.
 *
 * @author Anatol Sialitski
 */
@ApiModel(value = "address", description = "address of current site")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Address {

    @ApiModelProperty(notes = "Post code")
    private String postCode;

    @ApiModelProperty(notes = "County")
    private String county;

    @ApiModelProperty(notes = "SPID")
    private String spid;

    @ApiModelProperty(notes = "City")
    private String city;

    @ApiModelProperty(notes = "Street addresses")
    private List<String> streetAddresses = new ArrayList<>();

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getStreetAddresses() {
        return streetAddresses;
    }

    public void setStreetAddresses(List<String> streetAddresses) {
        this.streetAddresses = streetAddresses;
    }

}
