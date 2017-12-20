package uk.co.castlewater.myaccount.integration.service.exception;

/**
 * @author Anatol Sialitski
 */
public abstract class CastleWaterExternalServiceException extends RuntimeException {

    public CastleWaterExternalServiceException() {
    }

    public CastleWaterExternalServiceException(String message) {
        super(message);
    }

    public CastleWaterExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
