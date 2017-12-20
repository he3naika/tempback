package uk.co.castlewater.myaccount.service.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The data model for a contact.
 *
 * @author Anatol Sialitski
 */
@ApiModel(value = "contact", description = "The data model for an contact")
public class Contact {

    @ApiModelProperty(notes = "Contact Name")
    private String name;

    @ApiModelProperty(notes = "Contact Number")
    private String number;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
