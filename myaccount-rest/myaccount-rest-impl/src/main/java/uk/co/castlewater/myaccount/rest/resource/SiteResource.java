package uk.co.castlewater.myaccount.rest.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.castlewater.myaccount.rest.api.resource.SiteResourceApi;
import uk.co.castlewater.myaccount.service.api.SiteService;
import uk.co.castlewater.myaccount.service.api.model.Site;

import java.util.Arrays;
import java.util.List;

/**
 * @author Anatol Sialitski
 */
@RestController
@RequestMapping("/sites")
public class SiteResource implements SiteResourceApi {

    private SiteService siteService;

    @Autowired
    public SiteResource(SiteService siteService) {
        this.siteService = siteService;
    }

    @Override
    @GetMapping
    public List<Site> getSitesForCurrentAccount() {
        return Arrays.asList(createDummySite("123"), createDummySite("123456"));
        //return siteService.getSites();
    }

    @Override
    @GetMapping(value = "/{siteNumber}")
    public Site getSite(@PathVariable("siteNumber") String siteNumber) {
        //return siteService.getSite(siteNumber);
        return createDummySite(siteNumber);
    }

    private Site createDummySite(String siteNumber) {
        Site site = new Site();

        site.setCustomerRefNumber(siteNumber);
        site.setPremiseAddress("21-16 testStreet");
        site.setPostCode("123456789");
        site.setSpid("123456789");
        site.setSic("123456");
        site.setSensitiveCustomer(true);

        return site;
    }
}
