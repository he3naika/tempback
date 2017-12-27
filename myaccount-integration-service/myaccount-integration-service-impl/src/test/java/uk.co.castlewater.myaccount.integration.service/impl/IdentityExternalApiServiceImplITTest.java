package uk.co.castlewater.myaccount.integration.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.model.AuthRequest;
import uk.co.castlewater.myaccount.integration.service.model.AuthResponse;
import uk.co.castlewater.myaccount.integration.service.model.ResetPasswordRequest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class IdentityExternalApiServiceImplITTest {

    @Spy
    @InjectMocks
    private IdentityExternalApiServiceImpl instance;

    @Mock
    private HttpClient httpClient;

    @Test
    public void testCreateAuthRequest() {
        // test
        final AuthRequest result = instance.createAuthRequest("email", "password");

        // assert
        assertEquals("email", result.getEmail());
        assertEquals("password", result.getPassword());
    }

    @Test
    public void testAuthenticate() {
        // prepare
        final AuthResponse response = mock(AuthResponse.class);
        final AuthRequest request = mock(AuthRequest.class);

        // mock
        doReturn(request).when(instance).createAuthRequest(anyString(), anyString());
        doReturn(response).when(httpClient).post(any(), any(AuthRequest.class), anyObject());

        // test
        final AuthResponse result = instance.authenticate(anyString(), anyString());

        // assert
        assertNotNull(result);
//        assertEquals(response, result);
        assertEquals(response, null);

        // verify
        final InOrder inOrder = inOrder(instance, httpClient);

        inOrder.verify(instance).createAuthRequest(anyString(), anyString());
        inOrder.verify(httpClient).post(any(), any(AuthRequest.class), anyObject());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testResetPassword() {
        // prepare
        final ResetPasswordRequest request = mock(ResetPasswordRequest.class);
        final Object response = mock(Object.class);

        // mock
        doReturn(request).when(instance).createResetPasswordRequest(anyString());
        doReturn(response).when(httpClient).post(any(), any(ResetPasswordRequest.class), anyObject());

        // test
        instance.resetPassword(anyString());

        // verify
        final InOrder inOrder = inOrder(instance, httpClient);

        inOrder.verify(instance).createResetPasswordRequest(anyString());
        inOrder.verify(httpClient).post(any(), any(ResetPasswordRequest.class), anyObject());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testCreateResetPasswordRequest() {
        // prepare
        final String expected = "email";

        // test
        final ResetPasswordRequest result = instance.createResetPasswordRequest(expected);

        // assert
        assertNotNull(result);
        assertEquals(expected, result.getEmail());
    }
}
