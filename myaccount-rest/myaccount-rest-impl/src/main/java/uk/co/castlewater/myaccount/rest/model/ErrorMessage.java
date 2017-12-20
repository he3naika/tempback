package uk.co.castlewater.myaccount.rest.model;

/**
 * @author Anatol Sialitski
 */
public class ErrorMessage {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static ErrorMessage newInstance(final String message) {
        final ErrorMessage result = new ErrorMessage();

        result.setMessage(message);

        return result;
    }

}
