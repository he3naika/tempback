package uk.co.castlewater.myaccount.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.castlewater.myaccount.rest.api.model.ChangePasswordRequestModel;
import uk.co.castlewater.myaccount.rest.api.resource.AccountResourceApi;
import uk.co.castlewater.myaccount.service.api.AccountService;
import uk.co.castlewater.myaccount.service.api.model.Account;
import uk.co.castlewater.myaccount.service.api.model.Address;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateChangePasswordRequest;

@RestController
@RequestMapping(value = "/accounts")
public class AccountResource implements AccountResourceApi {

    private AccountService service;

    @Autowired
    public AccountResource(AccountService accountService) {
        this.service = accountService;
    }

    @Override
    @PutMapping(value = "/changePassword")
    public void changePassword(@RequestBody ChangePasswordRequestModel requestModel) {
        validateChangePasswordRequest(requestModel);

        service.changePassword(requestModel.getPassword(), requestModel.getPasswordConfirmation());
    }

    @Override
    @GetMapping(value = "/info")
    public Account getAccount() {
        return service.getAccount();
    }

    @Override
    @GetMapping(value = "/billingAddresses")
    public List<Address> getBillingAddresses() {
        return createDummyBillingAddresses();
        //return service.getBillingAddresses();
    }

    private List<Address> createDummyBillingAddresses() {
        return new ArrayList<Address>() {{
            add(new Address() {{
                setCity("Perthshire");
                setCounty("Blairgowire");
                setStreetAddresses(Arrays.asList("36 Lower Mill Street", "Blairgowire"));
                setPostCode("PH10 7JB");
            }});
            add(new Address() {{
                setCity("Glasgow");
                setCounty("Ayrshire");
                setStreetAddresses(Arrays.asList("78 Queen St.", "Glasgow"));
                setPostCode("G1 3DS");
            }});
            add(new Address() {{
                setCity("Dundee");
                setCounty("Angus");
                setStreetAddresses(Arrays.asList("1 Argyllgait", "Dundee"));
                setPostCode("DD1 1PZ");
            }});
        }};
    }
}
