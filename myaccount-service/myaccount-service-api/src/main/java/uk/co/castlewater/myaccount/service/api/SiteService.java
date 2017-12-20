package uk.co.castlewater.myaccount.service.api;

import uk.co.castlewater.myaccount.service.api.model.Site;

import java.util.List;

public interface SiteService {

    List<Site> getSites();

    Site getSite(String spid);

}
