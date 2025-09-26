package uk.gov.hmcts.cp.bdd.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import uk.gov.hmcts.cp.bdd.driver.Driver;

public interface WebDriverInstance {
    WebDriver driver = Driver.GetWebDriver();
    WebDriverWait waitFor = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
}
