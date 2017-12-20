package uk.co.castlewater.myaccount.integration.service.api;

import uk.co.castlewater.myaccount.integration.service.exception.CastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.model.MeterExternalModel;
import uk.co.castlewater.myaccount.integration.service.model.ReadingExternalModel;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
public interface MeterExternalApiService {

    List<MeterExternalModel> getMeters() throws CastleWaterExternalServiceException;

    List<ReadingExternalModel> getReadings() throws CastleWaterExternalServiceException;

    void createReading(ReadingExternalModel entity) throws CastleWaterExternalServiceException;
}
