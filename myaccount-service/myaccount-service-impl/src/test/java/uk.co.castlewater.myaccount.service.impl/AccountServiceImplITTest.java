package uk.co.castlewater.myaccount.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.integration.service.api.UserExternalApiService;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContext;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;
import uk.co.castlewater.myaccount.integration.service.model.BillingAddressExternalModel;
import uk.co.castlewater.myaccount.service.api.model.Account;
import uk.co.castlewater.myaccount.service.api.model.Address;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceImplITTest {

    @Spy
    @InjectMocks
    private AccountServiceImpl instance;

    @Mock
    private UserExternalApiService externalApiService;

    @Test
    public void testChangePassword() {
        // mock
        doNothing().when(externalApiService).changePassword(anyString(), anyString());

        // test
        instance.changePassword(anyString(), anyString());

        // verify
        final InOrder inOrder = Mockito.inOrder(externalApiService);
        inOrder.verify(externalApiService).changePassword(anyString(), anyString());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetAccount() {
        // prepare
        final IdentityContext context = mock(IdentityContext.class);
        final Account account = mock(Account.class);

        IdentityContextHolder.clear();
        IdentityContextHolder.set(context);

        // mock
        doReturn(account).when(instance).identityContextToAccount(context);

        // test
        final Account result = instance.getAccount();

        // assert
        assertNotNull(result);
        assertEquals(account, result);

        // verify
        final InOrder inOrder = Mockito.inOrder(instance);

        inOrder.verify(instance).identityContextToAccount(context);
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetBillingAddresses() {
        // prepare
        final List<BillingAddressExternalModel> values = Collections.singletonList(new BillingAddressExternalModel());
        final Address value = new Address();

        // mock
        doReturn(values).when(externalApiService).getBillingAddresses();
        doReturn(value).when(instance).entityToModel(any(BillingAddressExternalModel.class));

        // test
        final List<Address> result = instance.getBillingAddresses();

        // assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(value, result.get(0));
    }

    @Test
    public void testIdentityContextToAccount() {
        // prepare
        final IdentityContext context = new IdentityContext();

        context.setAccountName("account name");
        context.setContactName("contact name");
        context.setContactNumber("contact number");
        context.setEmail("email");
        context.setMobile("mobile");

        // test
        final Account result = instance.identityContextToAccount(context);

        // assert
        assertNotNull(result);
        assertEquals(context.getAccountName(), result.getName());
        assertNotNull(result.getContact());
        assertEquals(context.getContactName(), result.getContact().getName());
        assertEquals(context.getContactNumber(), result.getContact().getNumber());
        assertEquals(context.getEmail(), result.getEmail());
        assertEquals(context.getMobile(), result.getMobile());
    }

    @Test
    public void testEntityToModel() {
        // prepare
        final BillingAddressExternalModel entity = new BillingAddressExternalModel();

        entity.setAddressLine1("AddressLine1");
        entity.setAddressLine2("AddressLine2");
        entity.setAddressLine4("AddressLine4");
        entity.setAddressLine5("AddressLine5");
        entity.setPostCode("PostCode");
        entity.setCity("City");
        entity.setCountry("Country");

        // test
        final Address result = instance.entityToModel(entity);

        //assert
        assertNotNull(result);
        assertFalse(result.getStreetAddresses().isEmpty());
        assertEquals(entity.getAddressLine1() + " " + entity.getAddressLine2(), result.getStreetAddresses().get(0));
        assertEquals(entity.getAddressLine4() + " " + entity.getAddressLine5(), result.getStreetAddresses().get(1));
        assertEquals(entity.getPostCode(), result.getPostCode());
        assertEquals(entity.getCity(), result.getCity());
        assertEquals(entity.getCountry(), result.getCounty());
    }

}
