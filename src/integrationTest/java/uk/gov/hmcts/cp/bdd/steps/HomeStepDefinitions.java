package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.Given;
import uk.gov.hmcts.cp.bdd.pages.CommonPage;
import uk.gov.hmcts.cp.bdd.pages.HomePage;

public class HomeStepDefinitions extends HomePage {
//    HomePage homePage = new HomePage();
    CommonPage commonPage = new CommonPage();

    @Given("I navigate to DPS service")
    public void i_navigate_to_dps_service() {
        commonPage.loginVSIPService();
    }

    @Given("I enter {string}")
    public void i_enter(String uName) {
        enterUserName(uName);
    }

    @Given("I enter the {string}")
    public void i_enter_the(String password) {
        enterPassword(password);
    }
}
