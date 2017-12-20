package uk.co.castlewater.myaccount.rest.api.resource;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import uk.co.castlewater.myaccount.service.api.Constants;
import uk.co.castlewater.myaccount.service.api.model.Site;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
@Api(value = "sites", description = "Site information")
public interface SiteResourceApi {

    @ApiOperation(value = "View sites for specific account")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constants.Swagger.OK_MESSAGE),
            @ApiResponse(code = 401, message = Constants.Swagger.NOT_AUTHORIZED_MESSAGE),
            @ApiResponse(code = 403, message = Constants.Swagger.FORBIDDEN_MESSAGE)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    List<Site> getSitesForCurrentAccount();

    @ApiOperation(value = "View site by specific id", response = Site.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = Constants.Swagger.OK_MESSAGE),
            @ApiResponse(code = 401, message = Constants.Swagger.NOT_AUTHORIZED_MESSAGE),
            @ApiResponse(code = 403, message = Constants.Swagger.FORBIDDEN_MESSAGE),
            @ApiResponse(code = 404, message = Constants.Swagger.NOT_FOUND_MESSAGE)
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Authorization", value = "Bearer {TOKEN}", required = true, dataType = "string", paramType = "header")
    })
    Site getSite(String siteNumber);

}
