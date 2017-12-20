package uk.co.castlewater.myaccount.integration.service.api;

import uk.co.castlewater.myaccount.integration.service.model.BillingAddressExternalModel;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
public interface UserExternalApiService {

    void changePassword(String password, String passwordConfirmation);

    List<BillingAddressExternalModel> getBillingAddresses();

}
