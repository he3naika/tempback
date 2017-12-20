package uk.co.castlewater.myaccount.service.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * The data model for an invoice.
 *
 * @author Anatol Sialitski
 */

@ApiModel(value = "invoice", description = "Data model for invoice")
public class Invoice {

    @ApiModelProperty(notes = "Invoice Id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
