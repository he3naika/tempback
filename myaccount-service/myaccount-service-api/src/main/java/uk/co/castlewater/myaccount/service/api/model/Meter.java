package uk.co.castlewater.myaccount.service.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The data model for a meter.
 *
 * @author Anatol Sialitski
 */
@ApiModel(value = "meter", description = "Meter")
public class Meter {

    @ApiModelProperty(notes = "SPID")
    private String spid;

    @ApiModelProperty(notes = "Meter number")
    private String number;

    @JsonProperty(value = "isAutomatic")
    @ApiModelProperty(notes = "Is automatic meter")
    private Boolean automatic;

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getAutomatic() {
        return automatic;
    }

    public void setAutomatic(Boolean automatic) {
        this.automatic = automatic;
    }

}
