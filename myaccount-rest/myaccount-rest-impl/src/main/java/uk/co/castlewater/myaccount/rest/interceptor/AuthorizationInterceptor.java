package uk.co.castlewater.myaccount.rest.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContext;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.invoke.MethodHandles;
import java.util.LinkedHashMap;

import static uk.co.castlewater.myaccount.integration.service.util.StringUtil.nullToNothing;

/**
 * @author Anatol Sialitski
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final String HEADER_PREFIX = "Bearer ";
    private static final int PAYLOAD_INDEX = 1;

    private ObjectMapper objectMapper;

    @Autowired
    public AuthorizationInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authorizationHeader == null || !authorizationHeader.startsWith(HEADER_PREFIX)) {
            LOGGER.error("The header \"Authorization\" is absent.");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return false;
        }

        final String token = authorizationHeader.substring(HEADER_PREFIX.length());

        if (!validateToken(token)) {
            LOGGER.error("Token is incorrect.");

            response.setStatus(HttpStatus.UNAUTHORIZED.value());

            return false;
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        IdentityContextHolder.clear();
    }

    @SuppressWarnings("unchecked")
    protected boolean validateToken(final String token) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return false;
        }

        final String[] parts = token.split("\\.", -1);

        if (parts.length != 3) {
            return false;
        }

        final String payload = parts[PAYLOAD_INDEX];

        final String decodedPayload = new String(Base64Utils.decode(payload.getBytes("UTF-8")));

        return validateAndSetIdentityContext(token, decodedPayload);
    }

    @SuppressWarnings("unchecked")
    protected boolean validateAndSetIdentityContext(String token, String decodedPayload) throws Exception {
        final LinkedHashMap<String, Object> payloadAsMap =
                (LinkedHashMap<String, Object>) objectMapper.readValue(decodedPayload, LinkedHashMap.class);

        if (!isValidPayload(payloadAsMap)) {
            return false;
        }

        final IdentityContext identityContext = createIdentityContext(token, payloadAsMap);

        IdentityContextHolder.set(identityContext);

        return true;
    }

    protected IdentityContext createIdentityContext(String token, LinkedHashMap<String, Object> payloadMap) {
        final IdentityContext result = new IdentityContext();

        result.setUserId(nullToNothing(payloadMap.get("id")));
        result.setAccountName(nullToNothing(payloadMap.get("accountName")));
        result.setContactName(nullToNothing(payloadMap.get("contactName")));
        result.setContactNumber(nullToNothing(payloadMap.get("contactNumber")));
        result.setEmail(nullToNothing(payloadMap.get("email")));
        result.setMobile(nullToNothing(payloadMap.get("mobile")));
        result.setToken(token);

        return result;
    }

    protected boolean isValidPayload(LinkedHashMap<String, Object> payloadMap) {
        return payloadMap.containsKey("id")
                && payloadMap.containsKey("accountName")
                && payloadMap.containsKey("contactName")
                && payloadMap.containsKey("contactNumber")
                && payloadMap.containsKey("email")
                && payloadMap.containsKey("mobile");
    }

}
