package uk.co.castlewater.myaccount.integration.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uk.co.castlewater.myaccount.integration.service.api.UserExternalApiService;
import uk.co.castlewater.myaccount.integration.service.client.HttpClient;
import uk.co.castlewater.myaccount.integration.service.identity.IdentityContextHolder;
import uk.co.castlewater.myaccount.integration.service.model.BillingAddressExternalModel;
import uk.co.castlewater.myaccount.integration.service.model.ChangePasswordRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static uk.co.castlewater.myaccount.integration.service.util.StringUtil.nullToNothing;

/**
 * @author Anatol Sialitski
 */
@Service
public class UserExternalApiServiceImpl implements UserExternalApiService {

    @Value("${external.api.url}")
    private String apiUrl;

    private HttpClient httpClient;

    @Autowired
    public UserExternalApiServiceImpl(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public void changePassword(String password, String passwordConfirmation) {
        final ChangePasswordRequest request = createChangePasswordRequest(password, passwordConfirmation);

        httpClient.put(apiUrl + "/users/passwordchange", request, Object.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<BillingAddressExternalModel> getBillingAddresses() {
        final String contactNumber = IdentityContextHolder.get().getContactNumber();

        final ArrayList<ArrayList<Map<String, Object>>> dataModel = (ArrayList<ArrayList<Map<String, Object>>>)
                httpClient.get(apiUrl + "/sites/customer/" + contactNumber, ArrayList.class);

        return createResponse(dataModel);
    }

    protected ChangePasswordRequest createChangePasswordRequest(String password, String passwordConfirmation) {
        ChangePasswordRequest request = new ChangePasswordRequest();

        request.setPassword(password);
        request.setPasswordConfirmation(passwordConfirmation);

        return request;
    }

    protected List<BillingAddressExternalModel> createResponse(List<ArrayList<Map<String, Object>>> dataModel) {
        final List<BillingAddressExternalModel> result = new ArrayList<>();

        for (ArrayList<Map<String, Object>> dataItem : dataModel) {
            result.addAll(createItemResponse(dataItem));
        }

        return result;
    }

    protected List<BillingAddressExternalModel> createItemResponse(List<Map<String, Object>> dataModel) {
        return dataModel.stream().map(this::entityToModel).collect(Collectors.toList());
    }

    protected BillingAddressExternalModel entityToModel(Map<String, Object> entity) {
        final BillingAddressExternalModel result = new BillingAddressExternalModel();

        result.setAddressLine1(nullToNothing(entity.get("AddressLine1")));
        result.setAddressLine2(nullToNothing(entity.get("AddressLine2")));
        result.setAddressLine4(nullToNothing(entity.get("AddressLine4")));
        result.setAddressLine5(nullToNothing(entity.get("AddressLine5")));
        result.setCity(nullToNothing(entity.get("City")));
        result.setCountry(nullToNothing(entity.get("Country")));
        result.setCustomerNumber(nullToNothing(entity.get("CustomerNumber")));
        result.setPostCode(nullToNothing(entity.get("Postcode")));

        return result;
    }

}
