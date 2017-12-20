package uk.co.castlewater.myaccount.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.integration.service.impl.IdentityExternalApiServiceImpl;
import uk.co.castlewater.myaccount.integration.service.model.AuthResponse;
import uk.co.castlewater.myaccount.service.api.model.Account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class IdentityServiceImplITTest {

    @Spy
    @InjectMocks
    private IdentityServiceImpl instance;

    @Mock
    private IdentityExternalApiServiceImpl externalApiService;

    @Test
    public void testLogin() {
        // mock
        final AuthResponse response = mock(AuthResponse.class);
        final Account account = mock(Account.class);

        doReturn(response).when(externalApiService).authenticate(anyString(), anyString());
        doReturn(account).when(instance).createAccount(response);

        // test
        final Account result = instance.login(anyString(), anyString());

        // assert
        assertNotNull(result);
        assertEquals(account, result);

        // verify
        final InOrder inOrder = inOrder(instance, externalApiService);

        inOrder.verify(externalApiService).authenticate(anyString(), anyString());
        inOrder.verify(instance).createAccount(response);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testResetPassword() {
        // mock
        doNothing().when(externalApiService).resetPassword(anyString());

        // test
        instance.resetPassword(anyString());

        // verify
        final InOrder inOrder = inOrder(externalApiService);

        inOrder.verify(externalApiService).resetPassword(anyString());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testCreateAccount() {
        // prepare
        final AuthResponse model = new AuthResponse();

        model.setAccountName("account name");
        model.setContactName("contact name");
        model.setContactNumber("contact number");
        model.setEmail("email");
        model.setMobile("mobile");
        model.setToken("token");

        // test
        final Account result = instance.createAccount(model);

        // assert
        assertNotNull(result);
        assertEquals(model.getToken(), result.getToken());
        assertEquals(model.getMobile(), result.getMobile());
        assertEquals(model.getEmail(), result.getEmail());
        assertNotNull(result.getContact());
        assertEquals(model.getContactNumber(), result.getContact().getNumber());
        assertEquals(model.getContactName(), result.getContact().getName());
        assertEquals(model.getAccountName(), result.getName());
    }

}
