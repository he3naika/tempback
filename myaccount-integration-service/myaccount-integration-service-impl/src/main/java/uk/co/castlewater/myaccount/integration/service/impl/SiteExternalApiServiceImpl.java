package uk.co.castlewater.myaccount.integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.co.castlewater.myaccount.integration.service.api.SiteExternalApiService;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.exception.CastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.model.Site;
import uk.co.castlewater.myaccount.integration.service.model.SiteExternalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SiteExternalApiServiceImpl implements SiteExternalApiService {

    @Value("${external.api.url}")
    private String apiUrl;

    private HttpClient httpClient;

    @Autowired
    public SiteExternalApiServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<SiteExternalModel> getSites() throws CastleWaterExternalServiceException {
        final ArrayList<ArrayList<Map<String, Object>>> dataModel =
                (ArrayList<ArrayList<Map<String, Object>>>) httpClient.get(apiUrl + "/sites", ArrayList.class);

        return createResponse(dataModel);
    }

    @Override
    public Site getSite(String spid) throws CastleWaterExternalServiceException {
        return httpClient.get(apiUrl + "/sites/" + spid, Site.class);
    }

    protected List<SiteExternalModel> createResponse(ArrayList<ArrayList<Map<String, Object>>> dataModel) {
        final List<SiteExternalModel> result = new ArrayList<>();

        for (ArrayList<Map<String, Object>> dataItem : dataModel) {
            result.addAll(createItemResponse(dataItem));
        }

        return result;
    }

    protected List<SiteExternalModel> createItemResponse(List<Map<String, Object>> records) {
        return records.stream().map(this::recordToModel).collect(Collectors.toList());
    }

    protected SiteExternalModel recordToModel(Map<String, Object> record) {
        final SiteExternalModel result = new SiteExternalModel();

        if (!StringUtils.isEmpty(record.get("PremiseProviderId"))) {
            result.setPremiseProviderId((Integer) record.get("PremiseProviderId"));
        }
        if (!StringUtils.isEmpty(record.get("PremiseDiscriminator"))) {
            result.setPremiseDiscriminator((Integer) record.get("PremiseDiscriminator"));
        }
        result.setWholesalerId(resolveValue(record.get("wholesalerId")));
        result.setCoreSPID(resolveValue(record.get("CoreSPID")));
        result.setBuildingName(resolveValue(record.get("BuildingName")));
        result.setBuildingNumber(resolveValue(record.get("BuildingNumber")));
        result.setThoroughfareName(resolveValue((record.get("ThoroughfareName"))));
        result.setPostCode(resolveValue(record.get("Postcode")));

        return result;
    }

    private String resolveValue(Object value) {
        return value != null ? value.toString() : null;
    }

}
