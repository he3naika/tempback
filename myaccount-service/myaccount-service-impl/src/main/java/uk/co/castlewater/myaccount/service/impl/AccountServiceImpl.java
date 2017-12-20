package uk.co.castlewater.myaccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.castlewater.myaccount.integration.service.api.UserExternalApiService;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContext;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;
import uk.co.castlewater.myaccount.integration.service.model.BillingAddressExternalModel;
import uk.co.castlewater.myaccount.service.api.AccountService;
import uk.co.castlewater.myaccount.service.api.model.Account;
import uk.co.castlewater.myaccount.service.api.model.Address;
import uk.co.castlewater.myaccount.service.api.model.Contact;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anatol Sialitski
 */
@Service
public class AccountServiceImpl implements AccountService {

    private UserExternalApiService service;

    @Autowired
    public AccountServiceImpl(UserExternalApiService userExternalApiService) {
        this.service = userExternalApiService;
    }

    @Override
    public void changePassword(String password, String passwordConfirmation) {
        service.changePassword(password, passwordConfirmation);
    }

    @Override
    public Account getAccount() {
        final IdentityContext identityContext = IdentityContextHolder.get();

        return identityContextToAccount(identityContext);
    }

    @Override
    public List<Address> getBillingAddresses() {
        return service.getBillingAddresses().stream().map(this::entityToModel).collect(Collectors.toList());
    }

    protected Account identityContextToAccount(IdentityContext context) {
        final Account result = new Account();

        result.setName(context.getAccountName());
        result.setMobile(context.getMobile());
        result.setEmail(context.getEmail());

        result.setContact(new Contact());
        result.getContact().setName(context.getContactName());
        result.getContact().setNumber(context.getContactNumber());

        return result;
    }

    protected Address entityToModel(BillingAddressExternalModel entity) {
        final Address result = new Address();

        result.setPostCode(entity.getPostCode());
        result.setCity(entity.getCity());
        result.setCounty(entity.getCountry());

        String streetAddress1 = entity.getAddressLine1() + " " + entity.getAddressLine2();
        String streetAddress2 = entity.getAddressLine4() + " " + entity.getAddressLine5();

        result.setStreetAddresses(Arrays.asList(streetAddress1, streetAddress2));

        return result;
    }

}
