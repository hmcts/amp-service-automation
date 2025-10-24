package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClient;
import uk.gov.hmcts.cp.controllers.TrackMyCaseClient;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackMyCaseStepsDefinition extends BaseSteps {

    @Autowired
    private TrackMyCaseClient trackMyCaseClient;

    @Given("Track My Case UI is running")
    public void trackMyCaseUiIsRunning() {
        RestClient client = RestClient.create();
        String body = client.get()
            .uri(trackMyCaseClient.getTrackMyCaseUrl() + TRACK_MY_CASE_HEALTHZ)
            .retrieve()
            .body(String.class);

        assertThat(body).isEqualTo("OK");
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

    @When("Wait {string} seconds")
    public void waitXSeconds(String seconds) {
        try {
            Thread.sleep(Duration.of(Integer.valueOf(seconds), ChronoUnit.SECONDS));
        } catch (Exception e) {
        }
    }

    @When("Stop")
    public void stop() {
        System.out.println("Test finished");
    }
}
