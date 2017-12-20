package uk.co.castlewater.myaccount.integration.service.exception;

/**
 * @author Anatol Sialitski
 */
public class NotFountCastleWaterExternalServiceException extends CastleWaterExternalServiceException {

    public NotFountCastleWaterExternalServiceException() {
    }

    public NotFountCastleWaterExternalServiceException(String message) {
        super(message);
    }

    public NotFountCastleWaterExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
