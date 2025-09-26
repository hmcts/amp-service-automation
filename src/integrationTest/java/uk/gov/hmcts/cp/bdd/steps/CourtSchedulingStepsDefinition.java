package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestClient;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class CourtSchedulingStepsDefinition extends BaseSteps {
    @Given("courtScheduling System is running")
    public void systemIsRunning() {
        cleatLocalData();
    }

    @When("We make a request to courtScheduling Swagger Hub by id {string}")
    public void weMakeARequestToWSwaggerHub(String id) {
        RestClient client = RestClient.create();
        setBody(client.get()
                    .uri(COURTSCHEDULING_BASE_API_URL + COURTSCHEDULING_API + "/id" + COURTSCHEDULING)
                    .retrieve()
                    .body(HashMap.class));
    }

    @Then("We receive a response from Swagger Hub for courtScheduling")
    public void weReceiveAResponseFromSwaggerHub() throws IOException {
        HashMap<String, Object> body = getBody();

        File courtSchedulingFile = ResourceUtils.getFile("classpath:data/courtSchedule.json");
        HashMap courtSchedulingFileAsMap = objectMapper.readValue(courtSchedulingFile, HashMap.class);
        assertThat(body).containsKey("courtSchedule");
        assertThat(body).isEqualTo(courtSchedulingFileAsMap);
    }
}
