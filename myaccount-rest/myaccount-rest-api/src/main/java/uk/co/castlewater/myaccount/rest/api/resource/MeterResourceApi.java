package uk.co.castlewater.myaccount.rest.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.co.castlewater.myaccount.service.api.model.Meter;
import uk.co.castlewater.myaccount.service.api.model.MeterReading;


import java.util.List;

import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.FORBIDDEN_MESSAGE;
import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.NOT_AUTHORIZED_MESSAGE;
import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.NOT_FOUND_MESSAGE;
import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.OK_MESSAGE;

/**
 * @author Anatol Sialitski
 */
@Api(value = "meters", description = "Meter information")
public interface MeterResourceApi {

    @ApiOperation(value = "Create new meter reading")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 401, message = NOT_AUTHORIZED_MESSAGE),
            @ApiResponse(code = 403, message = FORBIDDEN_MESSAGE),
            @ApiResponse(code = 404, message = NOT_FOUND_MESSAGE)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    void createMeterReading(String meterNumber, MeterReading meterReading);

    @ApiOperation(value = "Get meters for current account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 401, message = NOT_AUTHORIZED_MESSAGE),
            @ApiResponse(code = 403, message = FORBIDDEN_MESSAGE)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    List<Meter> getMetersForCurrentAccount();

    @ApiOperation(value = "Get meter readings by specific meter number")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 401, message = NOT_AUTHORIZED_MESSAGE),
            @ApiResponse(code = 403, message = FORBIDDEN_MESSAGE)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    List<MeterReading> getMeterReadingsByMeter(String meterNumber);

}
