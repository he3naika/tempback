package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;

/**
 * @author Anatol Sialitski
 */
public class ResetPasswordRequest implements Serializable {

    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
