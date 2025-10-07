package uk.gov.hmcts.cp.bdd.pages;

public class HomePage extends CommonMethods {

    public void enterEmail(String email) {
        enterValueInTextField("xpath", email, "//input[@id='email']");
    }

    public void enterPassword(String password) {
        enterValueInTextField("xpath", password, "//input[@id='password']");
    }

    public void enter2Fa(String code) {
        enterValueInTextField("xpath", code, "//input[@id='code']");
    }

}
