package uk.co.castlewater.myaccount.rest.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.co.castlewater.myaccount.service.api.model.State;

/**
 * @author Anatol Sialitski
 */
@RestController
@RequestMapping("/state")
public class StateResource {

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @GetMapping
    public State state() {
        return new State(buildVersion, buildTimestamp);
    }

}
