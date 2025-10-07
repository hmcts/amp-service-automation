package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.hmcts.cp.bdd.pages.CommonPage;
import uk.gov.hmcts.cp.bdd.pages.HomePage;
import uk.gov.hmcts.cp.controllers.TrackMyCaseClient;

public class HomeStepDefinitions extends HomePage {

    @Autowired
    private TrackMyCaseClient trackMyCaseClient;

    //    HomePage homePage = new HomePage();
    CommonPage commonPage = new CommonPage();

    @Given("I navigate to DPS service")
    public void i_navigate_to_dps_service() {
        commonPage.loginVSIPService();
    }

    @Given("I enter email")
    public void iEnterEmail() {
        enterEmail(trackMyCaseClient.getOneLoginEmail());
    }

    @Given("I enter password")
    public void iEnterPassword() {
        enterPassword(trackMyCaseClient.getOneLoginPassword());
    }

    @Given("I enter 2FA")
    public void iEnter2Fa() {
        enter2Fa(trackMyCaseClient.getNext2FA());
    }
}
