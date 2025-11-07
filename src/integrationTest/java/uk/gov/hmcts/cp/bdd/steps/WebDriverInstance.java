package uk.gov.hmcts.cp.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.hmcts.cp.bdd.driver.Driver;

public interface WebDriverInstance {
    WebDriver ACTIVE_WEB_DRIVER = Driver.GetWebDriver();
    WebDriverWait waitFor = new WebDriverWait(ACTIVE_WEB_DRIVER, java.time.Duration.ofSeconds(10));
}
