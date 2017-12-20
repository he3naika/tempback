package uk.co.castlewater.myaccount.rest.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uk.co.castlewater.myaccount.integration.service.exception.BadRequestCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.exception.NotFountCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.rest.exception.ValidationFormException;
import uk.co.castlewater.myaccount.integration.service.exception.UnauthorizedCastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.rest.model.ErrorMessage;

import java.lang.invoke.MethodHandles;

/**
 * @author Anatol Sialitski
 */
@RestControllerAdvice
public class ApiResourceExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ExceptionHandler(ValidationFormException.class)
    public ResponseEntity<ErrorMessage> handleValidationFailedException(ValidationFormException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ErrorMessage.newInstance(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedCastleWaterExternalServiceException.class)
    public ResponseEntity<ErrorMessage> handleUnauthorizedException(UnauthorizedCastleWaterExternalServiceException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ErrorMessage.newInstance("401 Unauthorized"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(BadRequestCastleWaterExternalServiceException.class)
    public ResponseEntity<ErrorMessage> handleBadRequestException(BadRequestCastleWaterExternalServiceException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ErrorMessage.newInstance("404 Bad request"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFountCastleWaterExternalServiceException.class)
    public ResponseEntity<ErrorMessage> handleNotFoundException(NotFountCastleWaterExternalServiceException ex) {
        LOGGER.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ErrorMessage.newInstance("404 Not Found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> handleServerErrorException(final Exception ex) {
        LOGGER.error(ex.getMessage(), ex);

        return new ResponseEntity<>(ErrorMessage.newInstance("500 Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
