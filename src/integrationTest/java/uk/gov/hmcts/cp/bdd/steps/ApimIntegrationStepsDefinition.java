package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.ScenarioScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import uk.gov.hmcts.cp.openapi.model.CourtSchedule;
import uk.gov.hmcts.cp.openapi.model.CourtScheduleResponse;
import uk.gov.hmcts.cp.openapi.model.CourtSitting;
import uk.gov.hmcts.cp.openapi.model.Hearing;
import uk.gov.hmcts.cp.services.ServiceApimIntegration;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ScenarioScope
@SpringBootTest
public class ApimIntegrationStepsDefinition extends BaseSteps {

    @Autowired
    private ServiceApimIntegration serviceApimIntegration;

    @Given("APIM System is running")
    public void systemIsRunning() {
        cleatLocalData();
    }

    @When("We load court sitting details from APIM by case_urn {string}")
    public void weMakeRequest_getCourtScheduleByCaseUrn(String caseUrn) {
        ResponseEntity<?> responseEntity = serviceApimIntegration.getCourtScheduleByCaseUrn(caseUrn);
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
            setBody(responseEntity.getBody());
        } else {
            assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
            setBody(responseEntity.getBody());
        }
    }

    @Then("We receive CourtScheduleResponse dto from APIM")
    public void weReceiveCourtScheduleResponse() throws IOException {
        CourtScheduleResponse response = getBody();
        assertThat(response.getCourtSchedule().size()).isEqualTo(1);

        CourtSchedule courtSchedule = response.getCourtSchedule().getFirst();
        assertThat(courtSchedule.getHearings().size()).isEqualTo(1);

        Hearing hearing = courtSchedule.getHearings().getFirst();
        assertThat(hearing.getHearingId()).isNotNull();
        assertThat(hearing.getCourtSittings().size()).isEqualTo(1);

        CourtSitting courtSitting = hearing.getCourtSittings().getFirst();
        assertThat(courtSitting.getJudiciaryId()).isNotNull();
        assertThat(courtSitting.getCourtHouse()).isNotNull();
        assertThat(courtSitting.getCourtRoom()).isNotNull();
        assertThat(courtSitting.getSittingStart()).isNotNull();
        assertThat(courtSitting.getSittingEnd()).isNotNull();
    }
}
