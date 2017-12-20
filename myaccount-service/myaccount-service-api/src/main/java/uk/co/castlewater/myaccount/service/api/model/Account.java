package uk.co.castlewater.myaccount.service.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The data model for an account.
 *
 * @author Anatol Sialitski
 */
@ApiModel(value = "account", description = "The data model for an account")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"name", "contact", "email", "mobile", "token"})
public class Account {

    @ApiModelProperty(notes = "Account Name")
    private String name;

    @ApiModelProperty(notes = "Contact details")
    private Contact contact;

    @ApiModelProperty(notes = "Email")
    private String email;

    @ApiModelProperty(notes = "Contact Mobile Phone")
    private String mobile;

    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}