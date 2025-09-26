package uk.gov.hmcts.cp.bdd.pages;

public class HomePage extends CommonMethods {

    //Enter userName in textbox
    public void enterUserName(String user_Name) {
        enterValueInTextField("xpath",user_Name,"//input[@id='username']");
    }
    //Enter password in password textbox
    public void enterPassword(String password) {
        enterValueInTextField("xpath",password,"//input[@id='password']");
    }
}
