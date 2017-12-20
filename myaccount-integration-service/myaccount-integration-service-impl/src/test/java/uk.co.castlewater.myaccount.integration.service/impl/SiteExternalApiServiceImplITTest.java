package uk.co.castlewater.myaccount.integration.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpMethod;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContext;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;
import uk.co.castlewater.myaccount.integration.service.model.SiteExternalModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyMap;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class SiteExternalApiServiceImplITTest {

    @Spy
    @InjectMocks
    private SiteExternalApiServiceImpl instance;

    @Mock
    private HttpClient httpClient;

    @Before
    public void setUp() {
        IdentityContextHolder.clear();
        IdentityContext identityContext = new IdentityContext();
        identityContext.setToken(UUID.randomUUID().toString());
        IdentityContextHolder.set(identityContext);
    }

    @Test
    public void testRecordToModel() {
        //prepare
        final Map<String, Object> testMap = new HashMap<>();
        testMap.put("PremiseProviderId", 123456);
        testMap.put("wholesalerId", "testwholesalerId");
        testMap.put("PremiseDiscriminator", 123456);
        testMap.put("CoreSPID", "testSpid");
        testMap.put("BuildingName", "testBillingName");
        testMap.put("BuildingNumber", "testBillingNumber");
        testMap.put("ThoroughfareName", "testThoroughfareName");
        testMap.put("Postcode", "testPostCode");

        //test
        final SiteExternalModel result = instance.recordToModel(testMap);

        //assert
        assertEquals(testMap.get("PremiseProviderId"), result.getPremiseProviderId());
        assertEquals(testMap.get("wholesalerId"), result.getWholesalerId());
        assertEquals(testMap.get("PremiseDiscriminator"), result.getPremiseDiscriminator());
        assertEquals(testMap.get("CoreSPID"), result.getCoreSPID());
        assertEquals(testMap.get("BuildingName"), result.getBuildingName());
        assertEquals(testMap.get("BuildingNumber"), result.getBuildingNumber());
        assertEquals(testMap.get("ThoroughfareName"), result.getThoroughfareName());
        assertEquals(testMap.get("Postcode"), result.getPostCode());
    }

    @Test
    public void testCreateItemResponse() {
        //prepare
        final List<Map<String, Object>> testList = new ArrayList<>();
        final SiteExternalModel model = mock(SiteExternalModel.class);

        for (int i = 0; i < 5; i++) {
            final Map<String, Object> testMap = new HashMap<>();
            testList.add(testMap);
        }

        //mock
        doReturn(model).when(instance).recordToModel(anyMap());

        //test
        final List<SiteExternalModel> result = instance.createItemResponse(testList);

        //assert
        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void testCreateResponse() {
        //prepare
        final ArrayList<ArrayList<Map<String, Object>>> testList = new ArrayList<>();
        testList.add(new ArrayList<>());
        testList.add(new ArrayList<>());
        testList.add(new ArrayList<>());

        //mock
        doReturn(Arrays.asList(new SiteExternalModel())).when(instance).createItemResponse(anyObject());

        //test
        final List<SiteExternalModel> result = instance.createResponse(testList);

        //assert
        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void testGetSites() {
        //prepare
        final List<SiteExternalModel> testList = new ArrayList<>();
        testList.add(new SiteExternalModel());
        testList.add(new SiteExternalModel());

        //mock
        doReturn(new ArrayList<>()).when(httpClient).get(anyString(), eq(ArrayList.class));
        doReturn(testList).when(instance).createResponse(any());

        //test
        final List<SiteExternalModel> result = instance.getSites();

        //assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(testList, result);

        //verify
        final InOrder inOrder = inOrder(instance, httpClient);

        inOrder.verify(httpClient).get(anyString(), eq(ArrayList.class));
        inOrder.verify(instance).createResponse(any());
        inOrder.verifyNoMoreInteractions();
    }
}
