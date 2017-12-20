package uk.co.castlewater.myaccount.integration.service.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uk.co.castlewater.myaccount.integration.service.exception.BadRequestCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.exception.NotFountCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.exception.ServerErrorCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.exception.UnauthorizedCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;

/**
 * Http client for communication with Castle Water API.
 *
 * @author Anatol Sialitski
 */
@Component
public class HttpClient {

    public static final String AUTHORIZATION_HEADER = "Authorization";

    private RestTemplate restTemplate;

    public HttpClient() {
        this.restTemplate = new RestTemplate();
    }

    public <T> T callAndReceive(String url, HttpMethod httpMethod, Object request, Class<T> responseCls) {
        try {
            final HttpEntity<Object> httpEntity = createHttpEntity(request);

            final ResponseEntity<T> response = restTemplate.exchange(url, httpMethod, httpEntity, responseCls);

            switch (response.getStatusCode()) {
                case OK: {
                    return response.getBody();
                }
                case BAD_REQUEST: {
                    throw new BadRequestCastleWaterExternalServiceException(getMessage(url, httpMethod, response));
                }
                case NOT_FOUND: {
                    throw new NotFountCastleWaterExternalServiceException(getMessage(url, httpMethod, response));
                }
                case UNAUTHORIZED: {
                    throw new UnauthorizedCastleWaterExternalServiceException(getMessage(url, httpMethod, response));
                }
                default: {
                    throw new ServerErrorCastleWaterExternalServiceException(getMessage(url, httpMethod, response));
                }
            }
        } catch (Exception ex) {
            throw new ServerErrorCastleWaterExternalServiceException("I/O error on connected to External API", ex);
        }
    }

    public <T> T get(String url, Class<T> responseCls) {
        return callAndReceive(url, HttpMethod.GET, null, responseCls);
    }

    public <T> T post(String url, Object request, Class<T> responseCls) {
        return callAndReceive(url, HttpMethod.POST, request, responseCls);
    }

    public <T> T put(String url, Object request, Class<T> responseCls) {
        return callAndReceive(url, HttpMethod.PUT, request, responseCls);
    }

    private HttpEntity<Object> createHttpEntity(Object request) {
        if (IdentityContextHolder.get() != null) {
            final HttpHeaders httpHeaders = new HttpHeaders();

            httpHeaders.add(AUTHORIZATION_HEADER, "Bearer " + IdentityContextHolder.get().getToken());

            return new HttpEntity<>(request, httpHeaders);
        }

        return new HttpEntity<>(request);
    }

    private <T> String getMessage(String url, HttpMethod httpMethod, ResponseEntity<T> response) {
        return httpMethod.name() + " request for \"" + url + "\" resulted in " + response.getStatusCodeValue() + " failed";
    }

}
