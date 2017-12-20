package uk.co.castlewater.myaccount.rest.api.model;

import java.io.Serializable;

/**
 * @author Anatol Sialitski
 */
public class ChangePasswordRequestModel implements Serializable {

    private String password;
    private String passwordConfirmation;

    public ChangePasswordRequestModel() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirmation() {
        return passwordConfirmation;
    }

    public void setPasswordConfirmation(String passwordConfirmation) {
        this.passwordConfirmation = passwordConfirmation;
    }

}
