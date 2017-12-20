package uk.co.castlewater.myaccount.integration.service.api;

import uk.co.castlewater.myaccount.integration.service.exception.CastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.model.Site;
import uk.co.castlewater.myaccount.integration.service.model.SiteExternalModel;

import java.util.List;

/**
 * @author Anatol Sialitski
 */
public interface SiteExternalApiService {

    List<SiteExternalModel> getSites() throws CastleWaterExternalServiceException;

    Site getSite(String spid) throws CastleWaterExternalServiceException;
}
