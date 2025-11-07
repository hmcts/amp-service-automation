package uk.gov.hmcts.cp.bdd.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import uk.gov.hmcts.cp.bdd.pages.CommonMethods;
import uk.gov.hmcts.cp.bdd.pages.CommonPage;

import static uk.gov.hmcts.cp.bdd.steps.WebDriverInstance.ACTIVE_WEB_DRIVER;

public class CommonSteps {

    CommonPage commonPage = new CommonPage();
    CommonMethods commonMethods = new CommonMethods();

    @Given("click on submit button")
    public void i_click_on_submit_button() {
        commonPage.clickOnSubmitBtn();
    }

    @Then("Im on {string} page")
    public void im_on_vsip_page(String pageTitle) {
        commonMethods.isPageTitleDisplayed(pageTitle);
    }

    @Then("Move to a new tab")
    public void moveToNewTab() {
        commonMethods.moveToNewTab();
    }

    @Then("I click on {string} button")
    public void click_on_continue_button(String buttonName) {
        commonPage.clickOnContinueBtn(buttonName);
    }

    @And("I click on Manage a Prison option")
    public void iClickOnManageAPrisonOption() {
        commonPage.clickOnManagePrisonOptn();
    }

    @And("I sign out of the service")
    public void iSignOutOfTheService() {
        commonPage.clickOnSignOut();
    }

    @Then("I see {string} page")
    public void iSeePage(String errorMsg) {
        commonPage.errorMsgDisplayed(errorMsg);
    }

}
