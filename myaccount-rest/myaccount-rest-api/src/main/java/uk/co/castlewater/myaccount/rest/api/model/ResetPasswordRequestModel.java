package uk.co.castlewater.myaccount.rest.api.model;

import java.io.Serializable;

/**
 * @author Anatol Sialitski
 */
public class ResetPasswordRequestModel implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
