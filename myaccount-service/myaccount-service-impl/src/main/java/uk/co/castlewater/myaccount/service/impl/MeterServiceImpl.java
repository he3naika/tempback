package uk.co.castlewater.myaccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.castlewater.myaccount.integration.service.api.MeterExternalApiService;
import uk.co.castlewater.myaccount.integration.service.model.MeterExternalModel;
import uk.co.castlewater.myaccount.integration.service.model.ReadingExternalModel;
import uk.co.castlewater.myaccount.service.api.MeterService;
import uk.co.castlewater.myaccount.service.api.model.Meter;
import uk.co.castlewater.myaccount.service.api.model.MeterReading;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Anatol Sialitski
 */
@Service
public class MeterServiceImpl implements MeterService {

    private MeterExternalApiService service;

    @Autowired
    public MeterServiceImpl(MeterExternalApiService service) {
        this.service = service;
    }

    @Override
    public List<Meter> getMeters() {
        return service.getMeters().stream().map(this::entityToModel).collect(Collectors.toList());
    }

    @Override
    public List<MeterReading> getMeterReadings() {
        return service.getReadings().stream().map(this::entityToModel).collect(Collectors.toList());
    }

    @Override
    public void createMeterReading(String meterSerialNumber, MeterReading meterReading) {
        service.createReading(modelToEntity(meterSerialNumber, meterReading));
    }

    protected Meter entityToModel(MeterExternalModel entity) {
        final Meter result = new Meter();

        result.setSpid(entity.getCoreSPID());
        result.setNumber(entity.getMeterSerial());
        result.setAutomatic(false);

        return result;
    }

    protected MeterReading entityToModel(ReadingExternalModel entity) {
        final MeterReading result = new MeterReading();

        result.setDate(LocalDateTime.ofInstant(Instant.parse(entity.getMeterReadDate()), ZoneOffset.UTC));
        result.setValue(entity.getReading());

        return result;
    }

    protected ReadingExternalModel modelToEntity(String meterSerialNumber, MeterReading model) {
        ReadingExternalModel entity = new ReadingExternalModel();

        entity.setMeterSerial(meterSerialNumber);
        entity.setReading(model.getValue());

        return entity;
    }
}
