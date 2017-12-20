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
import uk.co.castlewater.myaccount.integration.service.model.CreateReadingRequest;
import uk.co.castlewater.myaccount.integration.service.model.MeterExternalModel;
import uk.co.castlewater.myaccount.integration.service.model.ReadingExternalModel;

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
public class MeterExternalApiServiceImplITTest {

    @Spy
    @InjectMocks
    private MeterExternalApiServiceImpl instance;

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
    public void testCreateReading() {
        //prepare
        final CreateReadingRequest request = mock(CreateReadingRequest.class);
        final Object response = mock(Object.class);

        //mock
        doReturn(request).when(instance).createReadingRequest(any());
        doReturn(response).when(httpClient).post(any(), any(), anyObject());

        //test
        instance.createReading(any());

        //verify
        final InOrder inOrder = inOrder(instance, httpClient);

        inOrder.verify(instance).createReadingRequest(any());
        inOrder.verify(httpClient).post(any(), any(), anyObject());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetReadings() {
        //prepare
        final List<ReadingExternalModel> testList = new ArrayList<>();
        testList.add(new ReadingExternalModel());

        //mock
        doReturn(new ArrayList<>()).when(httpClient).get(anyString(), eq(ArrayList.class));
        doReturn(testList).when(instance).createReadingsResponse(any());

        //test
        final List<ReadingExternalModel> result = instance.getReadings();

        //assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(testList, result);

        //verify
        final InOrder inOrder = inOrder(httpClient, instance);

        inOrder.verify(httpClient).get(anyString(), eq(ArrayList.class));
        inOrder.verify(instance).createReadingsResponse(any());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetMeters() {
        //prepare
        final List<MeterExternalModel> testList = new ArrayList<>();
        testList.add(new MeterExternalModel());

        //mock
        doReturn(new ArrayList<>()).when(httpClient).get(anyString(), eq(ArrayList.class));
        doReturn(testList).when(instance).createMetersResponse(any());

        //test
        final List<MeterExternalModel> result = instance.getMeters();

        //assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(testList, result);

        //verify
        final InOrder inOrder = inOrder(httpClient, instance);

        inOrder.verify(httpClient).get(anyString(), eq(ArrayList.class));
        inOrder.verify(instance).createMetersResponse(any());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testCreateReadingRequest() {
        //prepare
        final ReadingExternalModel testReadingExternalModel = new ReadingExternalModel();
        testReadingExternalModel.setReading(123);
        testReadingExternalModel.setMeterSerial("meterSerial");

        //test
        final CreateReadingRequest result = instance.createReadingRequest(testReadingExternalModel);

        //assert
        assertNotNull(result);
        assertEquals(testReadingExternalModel.getReading(), result.getRead());
        assertEquals(testReadingExternalModel.getMeterSerial(), result.getMeterSerialNumber());
    }

    @Test
    public void testCreateReadingsResponse() {
        //prepare
        final ArrayList<ArrayList<Map<String, Object>>> testList = new ArrayList<>();
        testList.add(new ArrayList<>());
        testList.add(new ArrayList<>());
        testList.add(new ArrayList<>());

        //mock
        doReturn(Arrays.asList(new ReadingExternalModel())).when(instance).createReadingsItemResponse(anyObject());

        //test
        final List<ReadingExternalModel> result = instance.createReadingsResponse(testList);

        //assert
        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void testCreateMetersResponse() {
        //prepare
        final ArrayList<ArrayList<Map<String, String>>> testList = new ArrayList<>();
        testList.add(new ArrayList<>());
        testList.add(new ArrayList<>());
        testList.add(new ArrayList<>());

        //mock
        doReturn(Arrays.asList(new MeterExternalModel())).when(instance).createMetersItemResponse(anyObject());

        //test
        final List<MeterExternalModel> result = instance.createMetersResponse(testList);

        //assert
        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void testCreateReadingsItemResponse() {
        //prepare
        final List<Map<String, Object>> testList = new ArrayList<>();
        final ReadingExternalModel model = mock(ReadingExternalModel.class);

        for (int i = 0; i < 5; i++) {
            final Map<String, Object> testMap = new HashMap<>();
            testList.add(testMap);
        }

        //mock
        doReturn(model).when(instance).readingsRecordToModel(anyMap());

        //test
        final List<ReadingExternalModel> result = instance.createReadingsItemResponse(testList);

        //assert
        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void testCreateMetersItemResponse() {
        //prepare
        final List<Map<String, String>> testList = new ArrayList<>();
        final MeterExternalModel model = mock(MeterExternalModel.class);

        for (int i = 0; i < 5; i++) {
            final Map<String, String> testMap = new HashMap<>();
            testList.add(testMap);
        }

        //mock
        doReturn(model).when(instance).metersRecordToModel(anyMap());

        //test
        final List<MeterExternalModel> result = instance.createMetersItemResponse(testList);

        //assert
        assertNotNull(result);
        assertEquals(testList.size(), result.size());
    }

    @Test
    public void testReadingsRecordToModel() {
        //prepare
        final Map<String, Object> testMap = new HashMap<>();
        testMap.put("MeterId", "BYPASS_(With_MN)14224347");
        testMap.put("CoreSPID", "301090486X");
        testMap.put("MeterReadDate", "2016-10-10T00:00:00.000Z");
        testMap.put("RolloverIndicator", "indicator");
        testMap.put("RolloverFlag", "0");
        testMap.put("ApprovedForBilling", true);
        testMap.put("MeterSerial", "14224347");
        testMap.put("Reading", 594);

        //test
        final ReadingExternalModel result = instance.readingsRecordToModel(testMap);

        //assert
        assertEquals(testMap.get("MeterId"), result.getMeterId());
        assertEquals(testMap.get("CoreSPID"), result.getCoreSPID());
        assertEquals(testMap.get("MeterReadDate"), result.getMeterReadDate());
        assertEquals(testMap.get("RolloverIndicator"), result.getRolloverIndicator());
        assertEquals(testMap.get("RolloverFlag"), result.getRolloverFlag());
        assertEquals(testMap.get("ApprovedForBilling"), result.getApprovedForBilling());
        assertEquals(testMap.get("MeterSerial"), result.getMeterSerial());
        assertEquals(testMap.get("Reading"), result.getReading());
    }

    @Test
    public void testMetersRecordToModel() {
        //prepare
        final Map<String, String> testMap = new HashMap<>();
        testMap.put("MeterMake", "BYPASS_(With_MN)");
        testMap.put("MeterSerial", "14224347");
        testMap.put("CoreSPID", "301090486X");
        testMap.put("BuildingName", "Building Name");
        testMap.put("BuildingNumber", "BRADFIELD COLLEGE-NEW SUPPLIES");
        testMap.put("Postcode", "RG76AU");

        //test
        final MeterExternalModel result = instance.metersRecordToModel(testMap);

        //assert
        assertEquals(testMap.get("MeterMake"), result.getMeterMake());
        assertEquals(testMap.get("MeterSerial"), result.getMeterSerial());
        assertEquals(testMap.get("CoreSPID"), result.getCoreSPID());
        assertEquals(testMap.get("BuildingName"), result.getBuildingName());
        assertEquals(testMap.get("BuildingNumber"), result.getBuildingNumber());
        assertEquals(testMap.get("Postcode"), result.getPostcode());
    }
}
