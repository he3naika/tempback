package uk.co.castlewater.myaccount.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.castlewater.myaccount.integration.service.api.SiteExternalApiService;
import uk.co.castlewater.myaccount.integration.service.model.SiteExternalModel;
import uk.co.castlewater.myaccount.service.api.SiteService;
import uk.co.castlewater.myaccount.service.api.model.Address;
import uk.co.castlewater.myaccount.service.api.model.Site;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteServiceImpl implements SiteService {

    private SiteExternalApiService service;

    @Autowired
    public SiteServiceImpl(SiteExternalApiService service) {
        this.service = service;
    }

    @Override
    public List<Site> getSites() {
        final List<Site> result = new ArrayList<>();

        for (SiteExternalModel entity : service.getSites()) {
            result.add(entityToModel(entity));
        }

        return result;
    }

    @Override
    public Site getSite(String spid) {

        uk.co.castlewater.myaccount.integration.service.model.Site siteExt = service.getSite(spid);
        Site site = createSite(siteExt);
        site.setSpid(spid);

        return site;
    }

    protected Site entityToModel(SiteExternalModel entity) {
        final Site result = new Site();

        result.setSpid(entity.getCoreSPID());
        result.setPostCode(entity.getPostCode());

        return result;
    }

    private Site createSite(uk.co.castlewater.myaccount.integration.service.model.Site siteExt) {
        Site site = new Site();

        Address address = new Address();
        address.setCity(siteExt.getAddress().getCity());
        address.setPostCode(siteExt.getAddress().getPostCode());
        address.setSpid(siteExt.getSpid());
        address.setStreetAddresses(siteExt.getAddress().getAddressLine());
        //site.setAddresses(Arrays.asList(address));

        return site;
    }
}
