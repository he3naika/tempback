package uk.co.castlewater.myaccount.service.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The data model for a site.
 *
 * @author Anatol Sialitski
 */
@ApiModel(value = "site", description = "The data model for a site")
public class Site {

    @ApiModelProperty(notes = "Customer Reference Number")
    private String customerRefNumber;

    @ApiModelProperty(notes = "Premise Address")
    private String premiseAddress;

    @ApiModelProperty(notes = "Post Code")
    private String postCode;

    @ApiModelProperty(notes = "SPID(s)")
    private String spid;

    @ApiModelProperty(notes = "SIC")
    private String sic;

    @ApiModelProperty(notes = "Sensitive Customer")
    @JsonProperty(value = "isSensitiveCustomer")
    private Boolean sensitiveCustomer;

    public String getCustomerRefNumber() {
        return customerRefNumber;
    }

    public void setCustomerRefNumber(String customerRefNumber) {
        this.customerRefNumber = customerRefNumber;
    }

    public String getPremiseAddress() {
        return premiseAddress;
    }

    public void setPremiseAddress(String premiseAddress) {
        this.premiseAddress = premiseAddress;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getSpid() {
        return spid;
    }

    public void setSpid(String spid) {
        this.spid = spid;
    }

    public String getSic() {
        return sic;
    }

    public void setSic(String sic) {
        this.sic = sic;
    }

    public Boolean getSensitiveCustomer() {
        return sensitiveCustomer;
    }

    public void setSensitiveCustomer(Boolean sensitiveCustomer) {
        this.sensitiveCustomer = sensitiveCustomer;
    }
}
