package uk.co.castlewater.myaccount.integration.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContext;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;
import uk.co.castlewater.myaccount.integration.service.model.BillingAddressExternalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class UserExternalApiServiceImplITTest {

    @Spy
    @InjectMocks
    private UserExternalApiServiceImpl instance;

    @Mock
    private HttpClient httpClient;

    @Before
    public void setUp() {
        IdentityContextHolder.clear();

        final IdentityContext identityContext = new IdentityContext();

        identityContext.setContactNumber("contactNumber");
        identityContext.setToken(UUID.randomUUID().toString());

        IdentityContextHolder.set(identityContext);
    }

    @Test
    public void testGetBillingAddresses() {
        //prepare
        final List<BillingAddressExternalModel> testList = new ArrayList<>();
        testList.add(new BillingAddressExternalModel());

        //mock
        doReturn(new ArrayList<>()).when(httpClient).get(anyString(), eq(ArrayList.class));
        doReturn(testList).when(instance).createResponse(any());

        //test
        final List<BillingAddressExternalModel> result = instance.getBillingAddresses();

        //assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(testList, result);

        //verify
        final InOrder inOrder = inOrder(httpClient, instance);

        inOrder.verify(httpClient).get(anyString(), eq(ArrayList.class));
        inOrder.verify(instance).createResponse(any());
        inOrder.verifyNoMoreInteractions();
    }

}
