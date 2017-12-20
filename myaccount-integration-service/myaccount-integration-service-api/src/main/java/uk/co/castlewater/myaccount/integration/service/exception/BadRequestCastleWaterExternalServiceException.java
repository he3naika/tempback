package uk.co.castlewater.myaccount.integration.service.exception;

/**
 * @author Anatol Sialitski
 */
public class BadRequestCastleWaterExternalServiceException extends CastleWaterExternalServiceException {

    public BadRequestCastleWaterExternalServiceException() {
    }

    public BadRequestCastleWaterExternalServiceException(String message) {
        super(message);
    }

    public BadRequestCastleWaterExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
