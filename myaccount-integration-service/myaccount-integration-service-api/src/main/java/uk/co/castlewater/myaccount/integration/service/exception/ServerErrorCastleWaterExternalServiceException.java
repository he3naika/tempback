package uk.co.castlewater.myaccount.integration.service.exception;

/**
 * @author Anatol Sialitski
 */
public class ServerErrorCastleWaterExternalServiceException extends CastleWaterExternalServiceException {

    public ServerErrorCastleWaterExternalServiceException() {
    }

    public ServerErrorCastleWaterExternalServiceException(String message) {
        super(message);
    }

    public ServerErrorCastleWaterExternalServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
