package uk.co.castlewater.myaccount.service.api;

import uk.co.castlewater.myaccount.service.api.model.Account;

/**
 * @author Anatol Sialitski
 */
public interface IdentityService {

    Account login(String username, String password);

    void resetPassword(String email);

}
