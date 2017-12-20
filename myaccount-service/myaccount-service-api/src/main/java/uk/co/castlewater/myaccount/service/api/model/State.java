package uk.co.castlewater.myaccount.service.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Anatol Sialitski
 */
@ApiModel(value = "state", description = "Application State")
public class State {

    @ApiModelProperty(notes = "Version")
    private String version;

    @ApiModelProperty(notes = "Timestamp")
    private String timestamp;

    public State(String version, String timestamp) {
        this.version = version;
        this.timestamp = timestamp;
    }

    public String getVersion() {
        return version;
    }

    public String getTimestamp() {
        return timestamp;
    }

}
