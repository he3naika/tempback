package uk.co.castlewater.myaccount.rest.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.rest.api.model.ChangePasswordRequestModel;
import uk.co.castlewater.myaccount.rest.api.model.LoginRequestModel;
import uk.co.castlewater.myaccount.rest.api.model.ResetPasswordRequestModel;
import uk.co.castlewater.myaccount.rest.exception.ValidationFormException;

import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateChangePasswordRequest;
import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateEmail;
import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateLoginRequest;
import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validatePassword;
import static uk.co.castlewater.myaccount.rest.validation.FormValidator.validateResetPasswordRequest;

@RunWith(MockitoJUnitRunner.class)
public class FormValidatorTest {

    @Test(expected = ValidationFormException.class)
    public void testLoginRequestWithNullEmail() {
        //prepare
        LoginRequestModel requestModel = new LoginRequestModel();
        requestModel.setPassword("blablaPass123!@");

        //test
        validateLoginRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testLoginRequestWithNullPassword() {
        //prepare
        LoginRequestModel requestModel = new LoginRequestModel();
        requestModel.setUsername("testuser@test.com");

        //test
        validateLoginRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testLoginRequestWithNullRequestModel() {
        //prepare
        LoginRequestModel requestModel = null;

        //test
        validateLoginRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testChangePasswordRequestWithNullRequestModel() {
        //prepare
        ChangePasswordRequestModel requestModel = null;

        //test
        validateChangePasswordRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testChangePasswordRequestWithNullPassword() {
        //prepare
        ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel();
        requestModel.setPasswordConfirmation("passWor@#123");

        //test
        validateChangePasswordRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testChangePasswordRequestWithNullPasswordConfirmation() {
        //prepare
        ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel();
        requestModel.setPassword("passWor@#123");

        //test
        validateChangePasswordRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testResetPasswordRequestWithNullEmail() {
        //prepare
        ResetPasswordRequestModel requestModel = new ResetPasswordRequestModel();

        //test
        validateResetPasswordRequest(requestModel);
    }

    @Test(expected = ValidationFormException.class)
    public void testResetPasswordRequestWithNullRequest() {
        //prepare
        ResetPasswordRequestModel requestModel = null;

        //test
        validateResetPasswordRequest(requestModel);
    }

    @Test
    public void testPasswordHappy() {
        //prepare
        String testPassword = "1@2#sEcret1";

        //test
        validatePassword(testPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordTooShort() {
        //prepare
        String badPassword = "123@#Ea";

        //test
        validatePassword(badPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNoUpperCaseSymbols() {
        //prepare
        String badPassword = "123!@aabbccdd";

        //test
        validatePassword(badPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNoLowerCaseSymbols() {
        //prepare
        String badPassword = "123$%AABBCCDD";

        //test
        validatePassword(badPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNoSpecialSymbols() {
        //prepare
        String badPassword = "123AABBCCdd";

        //test
        validatePassword(badPassword);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPasswordNoNumerals() {
        //prepare
        String badPassword = "AAbbCCdd$%";

        //test
        validatePassword(badPassword);
    }

    @Test
    public void testEmailHappy() {
        //prepare
        String happyEmail = "testmail@test.com";

        //test
        validateEmail(happyEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailNoDoggy() {
        //prepare
        String badEmail = "blabla.test.com";

        //test
        validateEmail(badEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailNoDot() {
        //prepare
        String badEmail = "blabla@testcom";

        //test
        validateEmail(badEmail);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmailTooShort() {
        //prepare
        String badEmail = "b@t.c";

        //test
        validateEmail(badEmail);
    }
}
