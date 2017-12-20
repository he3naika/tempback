package uk.co.castlewater.myaccount.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import uk.co.castlewater.myaccount.integration.service.impl.MeterExternalApiServiceImpl;
import uk.co.castlewater.myaccount.integration.service.model.MeterExternalModel;
import uk.co.castlewater.myaccount.integration.service.model.ReadingExternalModel;
import uk.co.castlewater.myaccount.service.api.model.Meter;
import uk.co.castlewater.myaccount.service.api.model.MeterReading;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.inOrder;

@RunWith(MockitoJUnitRunner.class)
public class MeterServiceImplITTest {

    @Spy
    @InjectMocks
    private MeterServiceImpl instance;

    @Mock
    private MeterExternalApiServiceImpl externalApiService;

    @Test
    public void testGetMeterReadings() {
        //prepare
        final MeterReading testMeterReading = new MeterReading();
        testMeterReading.setDate(LocalDateTime.now());
        testMeterReading.setValue(123456);
        final List<ReadingExternalModel> testList = new ArrayList<>();
        testList.add(new ReadingExternalModel());

        //mock
        doReturn(testList).when(externalApiService).getReadings();
        doReturn(testMeterReading).when(instance).entityToModel(any(ReadingExternalModel.class));

        //test
        final List<MeterReading> result = instance.getMeterReadings();

        //assert
        assertNotNull(result);
        assertEquals(testMeterReading.getValue(), result.get(0).getValue());
        assertEquals(testMeterReading.getDate(), result.get(0).getDate());

        //verify
        final InOrder inOrder = inOrder(instance, externalApiService);

        inOrder.verify(externalApiService).getReadings();
        inOrder.verify(instance).entityToModel(any(ReadingExternalModel.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testGetMeters() {
        //prepare
        final Meter testMeter = new Meter();
        testMeter.setAutomatic(true);
        testMeter.setNumber("number");
        testMeter.setSpid("spid");
        final List<MeterExternalModel> testList = new ArrayList<>();
        testList.add(new MeterExternalModel());

        //mock
        doReturn(testList).when(externalApiService).getMeters();
        doReturn(testMeter).when(instance).entityToModel(any(MeterExternalModel.class));

        //test
        final List<Meter> result = instance.getMeters();

        //assert
        assertNotNull(result);
        assertEquals(testMeter.getAutomatic(), result.get(0).getAutomatic());
        assertEquals(testMeter.getNumber(), result.get(0).getNumber());
        assertEquals(testMeter.getSpid(), result.get(0).getSpid());

        //verify
        final InOrder inOrder = inOrder(instance, externalApiService);

        inOrder.verify(externalApiService).getMeters();
        inOrder.verify(instance).entityToModel(any(MeterExternalModel.class));
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testCreateMeterReading() {
        //mock
        doNothing().when(externalApiService).createReading(any(ReadingExternalModel.class));
        doReturn(new ReadingExternalModel()).when(instance).modelToEntity(anyString(), any(MeterReading.class));

        //test
        instance.createMeterReading(anyString(), any(MeterReading.class));

        //verify
        InOrder inOrder = inOrder(externalApiService, instance);

        inOrder.verify(instance).modelToEntity(anyString(), any(MeterReading.class));
        inOrder.verify(externalApiService).createReading(any());
        inOrder.verifyNoMoreInteractions();
    }

    @Test
    public void testMeterReadingEntityToModel() {
        //prepare
        final ReadingExternalModel entity = new ReadingExternalModel();
        entity.setReading(123456);
        entity.setMeterReadDate("2016-10-10T00:00:00.000Z");

        //test
        final MeterReading result = instance.entityToModel(entity);

        //assert
        assertEquals(entity.getReading(), result.getValue());
        assertEquals(LocalDateTime.ofInstant(Instant.parse(entity.getMeterReadDate()), ZoneOffset.UTC), result.getDate());
    }

    @Test
    public void testMeterEntityToModel() {
        //prepare
        final MeterExternalModel entity = new MeterExternalModel();
        entity.setBuildingName("BuildingName");
        entity.setBuildingNumber("BuildingNumber");
        entity.setCoreSPID("CoreSPID");
        entity.setMeterMake("MeterMake");
        entity.setMeterSerial("MeterSerial");
        entity.setPostcode("Postcode");

        //test
        final Meter result = instance.entityToModel(entity);

        //assert
        assertEquals(entity.getMeterSerial(), result.getNumber());
        assertEquals(entity.getCoreSPID(), result.getSpid());
    }

    @Test
    public void testModelToEntity() {
        //prepare
        final MeterReading model = new MeterReading();
        model.setValue(123);
        String testMeterSerialNumber = "meter serial number";

        //test
        final ReadingExternalModel result = instance.modelToEntity(testMeterSerialNumber, model);

        //assert
        assertEquals(model.getValue(), result.getReading());
        assertEquals(testMeterSerialNumber, result.getMeterSerial());
    }
}
