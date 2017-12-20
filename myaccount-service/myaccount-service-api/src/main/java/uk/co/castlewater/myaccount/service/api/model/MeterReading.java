package uk.co.castlewater.myaccount.service.api.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import uk.co.castlewater.myaccount.service.api.converter.LocalDateTimeSerializer;

import java.time.LocalDateTime;

/**
 * The data model for a meter.
 *
 * @author Anatol Sialitski
 */
@ApiModel(value = "meter_reading", description = "Meter reading")
public class MeterReading {

    @ApiModelProperty(notes = "Value")
    private Integer value;

    @ApiModelProperty("Date")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
