package uk.co.castlewater.myaccount.rest.validation;

import uk.co.castlewater.myaccount.rest.api.model.ChangePasswordRequestModel;
import uk.co.castlewater.myaccount.rest.api.model.LoginRequestModel;
import uk.co.castlewater.myaccount.rest.api.model.ResetPasswordRequestModel;
import uk.co.castlewater.myaccount.rest.exception.ValidationFormException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormValidator {

    public static void validateChangePasswordRequest(ChangePasswordRequestModel requestModel) {
        try {
            Objects.requireNonNull(requestModel, "Request cannot be null");
            Objects.requireNonNull(requestModel.getPassword(), "Incorrect password");
            Objects.requireNonNull(requestModel.getPasswordConfirmation(), "Incorrect password confirmation");
            validatePassword(requestModel.getPassword());
            if (!requestModel.getPassword().equals(requestModel.getPasswordConfirmation())) {
                throw new IllegalArgumentException("Passwords should be equal");
            }
        } catch (Exception ex) {
            throw new ValidationFormException(ex.getMessage());
        }


    }

    public static void validateResetPasswordRequest(ResetPasswordRequestModel requestModel) {
        try {
            Objects.requireNonNull(requestModel, "Request can not be empty");
            Objects.requireNonNull(requestModel.getEmail(), "Incorrect email");
            validateEmail(requestModel.getEmail());
        } catch (Exception ex) {
            throw new ValidationFormException(ex.getMessage());
        }
    }

    public static void validateLoginRequest(LoginRequestModel requestModel) {
        try {
            Objects.requireNonNull(requestModel, "Request can not be empty");
            Objects.requireNonNull(requestModel.getUsername(), "Incorrect email");
            Objects.requireNonNull(requestModel.getPassword(), "Incorrect Password");
            validateEmail(requestModel.getUsername());
        } catch (Exception ex) {
            throw new ValidationFormException(ex.getMessage());
        }
    }

    protected static void validatePassword(String password) {
        if (!(validateDigitsAndSpecialSymbols(password) && validateLengthAndCases(password))) {
            throw new IllegalArgumentException("Your password is not strong enough. New passwords must:\n" +
                    "Be at least eight characters long\n" +
                    "Contain three or more numbers\n" +
                    "Contain at least one letter in upper case and ine letter in lower case\n" +
                    "Include at least two of the following special characters: !\"#$%&\'()*+,-./:;<=>?@[\\]^_`{|}~, or a space");
        }
    }

    protected static void validateEmail(String email) {
        final String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Enter the email address in the format someone@example.com.");
        }
    }

    private static boolean validateDigitsAndSpecialSymbols(String password) {
        return validateForDigitsNumber(password) && validateForSpecialSymbols(password);
    }

    private static boolean validateForDigitsNumber(String password) {
        // validation that password has at least 3 numbers
        Pattern pattern = Pattern.compile("\\d");
        Matcher matcher = pattern.matcher(password);
        int i = 0;

        while (matcher.find()) {
            i++;
            if (i == 3) {
                return true;
            }
        }

        return false;
    }

    private static boolean validateForSpecialSymbols(String password) {
        //validation for special symbols
        Pattern pattern = Pattern.compile("\\p{Punct}");
        Matcher matcher = pattern.matcher(password);
        int i = 0;

        while (matcher.find()) {
            i++;
            if (i == 2) {
                return true;
            }
        }

        return false;
    }

    private static boolean validateLengthAndCases(String password) {
        //validation for password length, lower case and upper case letters
        return password.matches("^(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }
}
