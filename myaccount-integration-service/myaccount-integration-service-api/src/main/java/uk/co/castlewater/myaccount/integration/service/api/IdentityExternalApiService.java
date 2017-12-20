package uk.co.castlewater.myaccount.integration.service.api;

import uk.co.castlewater.myaccount.integration.service.model.AuthResponse;
import uk.co.castlewater.myaccount.integration.service.exception.CastleWaterExternalServiceException;

/**
 * @author Anatol Sialitski
 */
public interface IdentityExternalApiService {

    AuthResponse authenticate(String username, String password) throws CastleWaterExternalServiceException;

    void resetPassword(String email) throws CastleWaterExternalServiceException;

}
