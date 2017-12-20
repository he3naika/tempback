package uk.co.castlewater.myaccount.integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.co.castlewater.myaccount.integration.service.api.IdentityExternalApiService;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.exception.CastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.model.AuthRequest;
import uk.co.castlewater.myaccount.integration.service.model.AuthResponse;
import uk.co.castlewater.myaccount.integration.service.model.ResetPasswordRequest;

/**
 * @author Anatol Sialitski
 */
@Service
public class IdentityExternalApiServiceImpl implements IdentityExternalApiService {

    @Value("${external.api.url}")
    private String apiUrl;

    private HttpClient httpClient;

    @Autowired
    public IdentityExternalApiServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public AuthResponse authenticate(String username, String password) throws CastleWaterExternalServiceException {
        final AuthRequest request = createAuthRequest(username, password);

        return httpClient.post(apiUrl + "/auth", request, AuthResponse.class);
    }

    @Override
    public void resetPassword(String email) throws CastleWaterExternalServiceException {
        final ResetPasswordRequest request = createResetPasswordRequest(email);

        httpClient.post(apiUrl + "/users/reset", request, Void.class);
    }

    protected AuthRequest createAuthRequest(String username, String password) {
        final AuthRequest request = new AuthRequest();

        request.setEmail(username);
        request.setPassword(password);

        return request;
    }

    protected ResetPasswordRequest createResetPasswordRequest(String email) {
        final ResetPasswordRequest request = new ResetPasswordRequest();

        request.setEmail(email);

        return request;
    }

}
