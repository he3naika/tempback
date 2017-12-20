package uk.co.castlewater.myaccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.castlewater.myaccount.integration.service.api.IdentityExternalApiService;
import uk.co.castlewater.myaccount.integration.service.model.AuthResponse;
import uk.co.castlewater.myaccount.service.api.IdentityService;
import uk.co.castlewater.myaccount.service.api.model.Account;
import uk.co.castlewater.myaccount.service.api.model.Contact;

/**
 * @author Anatol Sialitski
 */
@Service
public class IdentityServiceImpl implements IdentityService {

    private IdentityExternalApiService service;

    @Autowired
    public IdentityServiceImpl(IdentityExternalApiService service) {
        this.service = service;
    }

    @Override
    public Account login(String username, String password) {
        AuthResponse response = service.authenticate(username, password);

        return createAccount(response);
    }

    @Override
    public void resetPassword(String email) {
        service.resetPassword(email);
    }

    protected Account createAccount(AuthResponse model) {
        final Account result = new Account();

        result.setEmail(model.getEmail());
        result.setName(model.getAccountName());
        result.setMobile(model.getMobile());

        result.setContact(new Contact());
        result.getContact().setName(model.getContactName());
        result.getContact().setNumber(model.getContactNumber());

        result.setToken(model.getToken());

        return result;
    }

}
