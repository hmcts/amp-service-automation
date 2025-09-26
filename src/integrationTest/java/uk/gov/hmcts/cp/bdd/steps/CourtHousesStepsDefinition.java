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

public class CourtHousesStepsDefinition extends BaseSteps {

    @Given("courtHouses System is running")
    public void systemIsRunning() {
        cleatLocalData();
    }

    @When("We make a request to courtHouses Swagger Hub by id {string}")
    public void weMakeARequestToWSwaggerHub(String id) {
        RestClient client = RestClient.create();
        setBody(client.get()
                    .uri(COURTHOUSES_BASE_API_URL + COURTHOUSES_API + "/id")
                    .retrieve()
                    .body(HashMap.class));
    }

    @Then("We receive a response from Swagger Hub for courtHouses")
    public void weReceiveAResponseFromSwaggerHub() throws IOException {
        HashMap<String, Object> body = getBody();

        File courtHouseFile = ResourceUtils.getFile("classpath:data/courtHouse.json");
        HashMap courtHouseFileAsMap = objectMapper.readValue(courtHouseFile, HashMap.class);
        assertThat(body).containsKey("courtHouse");
        assertThat(body).isEqualTo(courtHouseFileAsMap);
    }
}
