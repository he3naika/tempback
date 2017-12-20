package uk.co.castlewater.myaccount.rest.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.co.castlewater.myaccount.rest.api.model.LoginRequestModel;
import uk.co.castlewater.myaccount.rest.api.model.ResetPasswordRequestModel;
import uk.co.castlewater.myaccount.service.api.model.Account;

import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.BAD_REQUEST_MESSAGE;
import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.INTERNAL_ERROR_MESSAGE;
import static uk.co.castlewater.myaccount.service.api.Constants.Swagger.OK_MESSAGE;

/**
 * @author Anatol Sialitski
 */
@Api(value = "Session", description = "Session REST API")
public interface IdentityResourceApi {

    @ApiOperation(value = "This endpoint is for authenticating a user login", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 400, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = 500, message = INTERNAL_ERROR_MESSAGE)
    })
    Account login(LoginRequestModel requestModel);

    @ApiOperation(value = "This endpoint is for resetting a password", response = Account.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = OK_MESSAGE),
            @ApiResponse(code = 400, message = BAD_REQUEST_MESSAGE),
            @ApiResponse(code = 500, message = INTERNAL_ERROR_MESSAGE)
    })
    void resetPassword(ResetPasswordRequestModel requestModel);

}
