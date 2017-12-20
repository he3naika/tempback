package uk.co.castlewater.myaccount.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.integration.service.impl.SiteExternalApiServiceImpl;
import uk.co.castlewater.myaccount.integration.service.model.SiteExternalModel;
import uk.co.castlewater.myaccount.service.api.model.Site;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class SiteServiceImplITTest {

    @Spy
    @InjectMocks
    private SiteServiceImpl instance;

    @Mock
    private SiteExternalApiServiceImpl externalApiService;

    @Test
    public void testEntityToModel() {
        //prepare
        final SiteExternalModel entity = new SiteExternalModel();
        entity.setCoreSPID("coreSpid");
        entity.setPostCode("postCode");

        //mock
        doReturn(Arrays.asList(entity)).when(externalApiService).getSites();

        //test
        final Site result = instance.entityToModel(entity);

        //assert
        assertEquals(entity.getCoreSPID(), result.getSpid());
        assertEquals(entity.getPostCode(), result.getPostCode());
    }

    @Test
    public void testGetSites() {
        //prepare
        final Site testSite = new Site();
        testSite.setSensitiveCustomer(true);
        testSite.setPremiseAddress("premise address");
        testSite.setSic("sic");
        testSite.setPostCode("post code");
        testSite.setCustomerRefNumber("ref number");
        testSite.setSpid("spid");
        final List<SiteExternalModel> testList = new ArrayList<>();
        testList.add(new SiteExternalModel());

        //mock
        doReturn(testList).when(externalApiService).getSites();
        doReturn(testSite).when(instance).entityToModel(any());

        //test
        final List<Site> result = instance.getSites();

        //assert
        assertNotNull(result);
        assertEquals(testSite.getSpid(), result.get(0).getSpid());
        assertEquals(testSite.getPostCode(), result.get(0).getPostCode());
        assertEquals(testSite.getCustomerRefNumber(), result.get(0).getCustomerRefNumber());
        assertEquals(testSite.getPremiseAddress(), result.get(0).getPremiseAddress());
        assertEquals(testSite.getSensitiveCustomer(), result.get(0).getSensitiveCustomer());
        assertEquals(testSite.getSic(), result.get(0).getSic());

        //verify
        final InOrder inOrder = inOrder(instance, externalApiService);
        inOrder.verify(externalApiService).getSites();
        inOrder.verify(instance).entityToModel(any());
        inOrder.verifyNoMoreInteractions();
    }
}
