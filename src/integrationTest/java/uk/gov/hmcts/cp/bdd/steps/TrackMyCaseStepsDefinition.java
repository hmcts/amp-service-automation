package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;
import uk.gov.hmcts.cp.controllers.TrackMyCaseClient;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackMyCaseStepsDefinition extends BaseSteps {

    @Autowired
    private TrackMyCaseClient trackMyCaseClient;

    @Given("Track My Case UI is running")
    public void trackMyCaseUiIsRunning() {
        RestClient client = RestClient.create();
        Map body = client.get()
            .uri(trackMyCaseClient.getTrackMyCaseUrl() + TRACK_MY_CASE_HEALTH)
            .retrieve()
            .body(HashMap.class);

        assertThat(body.get("status")).isEqualTo("UP");
    }

    @When("User clicks button {string}")
    public void userClicksButton(String button) {
        String next2FA = trackMyCaseClient.getNext2FA();
        assertThat(next2FA).isNotBlank();

        // selector page click by text(button)

        String oneLoginEmail = trackMyCaseClient.getOneLoginEmail();
        // selector input, enter value, click submit
        assertThat(oneLoginEmail).isNotBlank();

        String oneLoginPassword = trackMyCaseClient.getOneLoginPassword();
        assertThat(oneLoginPassword).isNotBlank();
    }
}
