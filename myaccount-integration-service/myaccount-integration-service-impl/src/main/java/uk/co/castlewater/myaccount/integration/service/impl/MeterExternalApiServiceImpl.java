package uk.co.castlewater.myaccount.integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import uk.co.castlewater.myaccount.integration.service.api.MeterExternalApiService;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.exception.CastleWaterExternalServiceException;
import uk.co.castlewater.myaccount.integration.service.model.CreateReadingRequest;
import uk.co.castlewater.myaccount.integration.service.model.MeterExternalModel;
import uk.co.castlewater.myaccount.integration.service.model.ReadingExternalModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MeterExternalApiServiceImpl implements MeterExternalApiService {

    @Value("${external.api.url}")
    private String apiUrl;

    private HttpClient httpClient;

    @Autowired
    public MeterExternalApiServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<MeterExternalModel> getMeters() throws CastleWaterExternalServiceException {
        final ArrayList<ArrayList<Map<String, String>>> dataModel =
                (ArrayList<ArrayList<Map<String, String>>>) httpClient.get(apiUrl + "/meters", ArrayList.class);

        return createMetersResponse(dataModel);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ReadingExternalModel> getReadings() throws CastleWaterExternalServiceException {
        final ArrayList<ArrayList<Map<String, Object>>> dataModel =
                (ArrayList<ArrayList<Map<String, Object>>>) httpClient.get(
                        apiUrl + "/reads", ArrayList.class);

        return createReadingsResponse(dataModel);
    }

    @Override
    public void createReading(ReadingExternalModel entity) throws CastleWaterExternalServiceException {
        final CreateReadingRequest request = createReadingRequest(entity);

        httpClient.post(apiUrl + "/reads", request, Void.class);
    }

    protected CreateReadingRequest createReadingRequest(ReadingExternalModel entity) {
        CreateReadingRequest request = new CreateReadingRequest();

        request.setMeterSerialNumber(entity.getMeterSerial());
        request.setRead(entity.getReading());

        return request;
    }

    protected List<MeterExternalModel> createMetersResponse(ArrayList<ArrayList<Map<String, String>>> dataModel) {
        final List<MeterExternalModel> result = new ArrayList<>();

        for (ArrayList<Map<String, String>> dataItem : dataModel) {
            result.addAll(createMetersItemResponse(dataItem));
        }

        return result;
    }

    protected List<ReadingExternalModel> createReadingsResponse(ArrayList<ArrayList<Map<String, Object>>> dataModel) {
        final List<ReadingExternalModel> result = new ArrayList<>();

        for (ArrayList<Map<String, Object>> dataItem : dataModel) {
            result.addAll(createReadingsItemResponse(dataItem));
        }

        return result;
    }

    protected List<MeterExternalModel> createMetersItemResponse(List<Map<String, String>> records) {
        return records.stream().map(this::metersRecordToModel).collect(Collectors.toList());
    }

    protected List<ReadingExternalModel> createReadingsItemResponse(List<Map<String, Object>> records) {
        return records.stream().map(this::readingsRecordToModel).collect(Collectors.toList());
    }

    protected MeterExternalModel metersRecordToModel(Map<String, String> record) {
        final MeterExternalModel result = new MeterExternalModel();

        result.setMeterMake(record.get("MeterMake"));
        result.setMeterSerial(record.get("MeterSerial"));
        result.setBuildingName(record.get("BuildingName"));
        result.setBuildingNumber(record.get("BuildingNumber"));
        result.setCoreSPID(record.get("CoreSPID"));
        result.setPostcode(record.get("Postcode"));

        return result;
    }

    protected ReadingExternalModel readingsRecordToModel(Map<String, Object> record) {
        final ReadingExternalModel result = new ReadingExternalModel();

        if (!StringUtils.isEmpty(record.get("ApprovedForBilling"))) {
            result.setApprovedForBilling((Boolean) record.get("ApprovedForBilling"));
        }
        if (!StringUtils.isEmpty(record.get("Reading"))) {
            result.setReading((Integer) record.get("Reading"));
        }
        result.setMeterId(resolveValue(record.get("MeterId")));
        result.setCoreSPID(resolveValue(record.get("CoreSPID")));
        result.setMeterReadDate(resolveValue(record.get("MeterReadDate")));
        result.setRolloverIndicator(resolveValue(record.get("RolloverIndicator")));
        result.setRolloverFlag(resolveValue(record.get("RolloverFlag")));
        result.setMeterSerial(resolveValue(record.get("MeterSerial")));

        return result;
    }

    private String resolveValue(Object value) {
        return value != null ? value.toString() : null;
    }
}
