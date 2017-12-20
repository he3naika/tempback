package uk.co.castlewater.myaccount.rest.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import uk.co.castlewater.myaccount.rest.api.model.ChangePasswordRequestModel;
import uk.co.castlewater.myaccount.service.api.model.Account;
import uk.co.castlewater.myaccount.service.api.model.Address;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
@Api(value = "accounts", description = "Account information")
public interface AccountResourceApi {

    @ApiOperation(value = "change password", response = Void.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    void changePassword(ChangePasswordRequestModel requestModel);

    @ApiOperation(value = "Returns account information for current user", response = Account.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    Account getAccount();

    @ApiOperation(value = "Returns billing addresses for current account", response = Address.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    List<Address> getBillingAddresses();

}
