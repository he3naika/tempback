package uk.co.castlewater.myaccount.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.co.castlewater.myaccount.rest.api.model.LoginRequestModel;
import uk.co.castlewater.myaccount.rest.api.model.ResetPasswordRequestModel;
import uk.co.castlewater.myaccount.rest.api.resource.IdentityResourceApi;
import uk.co.castlewater.myaccount.service.api.IdentityService;
import uk.co.castlewater.myaccount.service.api.model.Account;

import java.lang.invoke.MethodHandles;

import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateLoginRequest;
import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateResetPasswordRequest;

/**
 * @author Anatol Sialitski
 */
@RestController
public class IdentityResource implements IdentityResourceApi {

    private final static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private IdentityService service;

    @Autowired
    public IdentityResource(IdentityService accountService) {
        this.service = accountService;
    }

    @Override
    @PostMapping(value = "/auth")
    public Account login(@RequestBody LoginRequestModel requestModel) {
        validateLoginRequest(requestModel);

        return service.login(requestModel.getUsername(), requestModel.getPassword());
    }

    @Override
    @PostMapping(value = "/resetPassword")
    public void resetPassword(@RequestBody ResetPasswordRequestModel requestModel) {
        validateResetPasswordRequest(requestModel);

        service.resetPassword(requestModel.getEmail());
    }
}
