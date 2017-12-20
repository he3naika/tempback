package uk.co.castlewater.myaccount.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.castlewater.myaccount.rest.api.resource.MeterResourceApi;
import uk.co.castlewater.myaccount.service.api.MeterService;
import uk.co.castlewater.myaccount.service.api.model.Meter;
import uk.co.castlewater.myaccount.service.api.model.MeterReading;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author Anatol Sialitski
 */
@RestController
@RequestMapping("/meters")
public class MeterResource implements MeterResourceApi {

    private MeterService service;

    @Autowired
    public MeterResource(MeterService service) {
        this.service = service;
    }

    @Override
    @PostMapping(value = "/{meterNumber}/meterReadings")
    public void createMeterReading(@PathVariable("meterNumber") String meterNumber, @RequestBody MeterReading meterReading) {
        Objects.requireNonNull(meterNumber, "Meter serial number cannot be empty");
        Objects.requireNonNull(meterReading, "Request body cannot be empty");
        Objects.requireNonNull(meterReading.getValue(), "Read value cannot be null");

        //service.createMeterReading(meterNumber, meterReading);
    }

    @Override
    @GetMapping
    public List<Meter> getMetersForCurrentAccount() {
        return Arrays.asList(createDummyMeter(false), createDummyMeter(false));
        //return service.getMeters();
    }

    @Override
    @GetMapping(value = "/{meterNumber}/meterReadings")
    public List<MeterReading> getMeterReadingsByMeter(@PathVariable("meterNumber") String meterNumber) {
        //return service.getMeterReadings();
        List<MeterReading> meterReadingList = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            meterReadingList.add(createDummyMeterReading());
        }
        return meterReadingList;
    }

    private Meter createDummyMeter(Boolean isAutomatic) {
        Meter meter = new Meter();
        meter.setSpid("123456789");
        meter.setNumber("987654321");
        meter.setAutomatic(isAutomatic);
        return meter;
    }

    private MeterReading createDummyMeterReading() {
        MeterReading reading = new MeterReading();
        reading.setDate(LocalDateTime.now());
        reading.setValue(0);
        return reading;
    }

}
