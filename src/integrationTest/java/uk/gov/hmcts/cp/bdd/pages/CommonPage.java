package uk.gov.hmcts.cp.bdd.pages;

public class CommonPage extends CommonMethods {

//    By continueBtnOnAllPages = By.id("submit");

    public void loginVSIPService() {
        navigateToPage("https://track-my-case-dev.apps.live.cloud-platform.service.justice.gov.uk");
    }

    public void deleteBrowserCookies() {
        driver.manage().deleteAllCookies();
    }

    public void clickOnSubmitBtn() {
        clickOnButton("xpath","//*[@id='submit']");
    }

    public void clickOnBookAVisitOption() {
        click("xpath","//*[@id='main-content']/div/div/ul/li[1]/div/h2/a");
    }

    public void clickOnChangeAVisitOption() {
        click("xpath","//*[@id='main-content']/div/div/ul/li[2]/div/h2/a");
    }

    public void selectPrisoner() {
        click("xpath","//*[@id='search-results-true']/tbody/tr/td[1]/a");
    }

    public void clickOnBtn() {
        click("xpath", "//button[contains(@data-test, 'search')]");
    }

    public void clickOnContinueBtn(String buttonText) {
        click("xpath",
              "//*[self::a or self::button][contains(@class, 'govuk-button') and normalize-space()='" + buttonText + "']"
        );
    }

    public void clickOnCancelBtn() {
        click("xpath","//button[contains(@data-test, 'cancel-booking')]");
    }

    public void clickOnBookAVisitBtn() {
        click("xpath","//*[@id='main-content']/div[1]/div/form/button");
    }

    public void clickOnCancelAVisitBtn(){
        click("xpath","//a[contains(@data-test, 'cancel-visit')]");
    }

    public void clickOnManagePrisonOptn() {
        click("xpath","//a[contains(@class, 'hmpps-header__link hmpps-header__title__service-name')]");
    }

    public void chooseAPrisoner() {
        click("xpath","//input[@id='visitor-220914']");
    }
    public void chooseAnotherPrisoner() {
        click("xpath","//input[@id='visitor-3719071']");
    }
    public void clickOnSignOut() {
        click("xpath","/html/body/header/div/nav/ul/li[2]/a");
    }
    public void errorMsgDisplayed(String errorMsg) {
        isElementDisplayed("xpath","//*[@id='error-detail']");
    }

}
