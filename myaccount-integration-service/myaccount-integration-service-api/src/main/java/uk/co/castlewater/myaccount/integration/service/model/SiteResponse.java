package uk.co.castlewater.myaccount.integration.service.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SiteResponse implements Serializable {

    private List<Site> sites = new ArrayList<>();

    public SiteResponse() {
        //do nothing
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }

    @Override
    public String toString() {
        return "SiteResponse{" +
                "sites=" + sites +
                '}';
    }
}
