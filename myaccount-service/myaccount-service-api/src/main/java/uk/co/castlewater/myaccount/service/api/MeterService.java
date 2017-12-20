package uk.co.castlewater.myaccount.service.api;

import uk.co.castlewater.myaccount.service.api.model.Meter;
import uk.co.castlewater.myaccount.service.api.model.MeterReading;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
public interface MeterService {

    List<Meter> getMeters();

    List<MeterReading> getMeterReadings();

    void createMeterReading(String meterSerialNumber, MeterReading meterReading);

}
