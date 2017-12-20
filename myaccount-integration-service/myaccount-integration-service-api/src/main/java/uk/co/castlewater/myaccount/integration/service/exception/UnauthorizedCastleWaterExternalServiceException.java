package uk.co.castlewater.myaccount.integration.service.exception;

/**
 * @author Anatol Sialitski
 */
public class UnauthorizedCastleWaterExternalServiceException extends CastleWaterExternalServiceException {

    public UnauthorizedCastleWaterExternalServiceException() {
    }

    public UnauthorizedCastleWaterExternalServiceException(String message) {
        super(message);
    }

    public UnauthorizedCastleWaterExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
