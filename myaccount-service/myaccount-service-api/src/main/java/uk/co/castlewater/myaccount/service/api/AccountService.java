package uk.co.castlewater.myaccount.service.api;

import uk.co.castlewater.myaccount.service.api.model.Account;
import uk.co.castlewater.myaccount.service.api.model.Address;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
public interface AccountService {

    void changePassword(String password, String passwordConfirmation);

    Account getAccount();

    List<Address> getBillingAddresses();

}
