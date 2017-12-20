package uk.co.castlewater.myaccount.rest.exception;

public class ValidationFormException extends RuntimeException {

    public ValidationFormException() {
    }

    public ValidationFormException(String message) {
        super(message);
    }

    public ValidationFormException(String message, Throwable cause) {
        super(message, cause);
    }

}
