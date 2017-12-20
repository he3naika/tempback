package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;

/**
 * @author Anatol Sialitski
 */
public class AuthRequest implements Serializable {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
