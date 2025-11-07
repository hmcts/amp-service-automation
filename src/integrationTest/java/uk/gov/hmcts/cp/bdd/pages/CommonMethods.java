package uk.gov.hmcts.cp.bdd.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import uk.gov.hmcts.cp.bdd.steps.WebDriverInstance;

import java.time.Duration;
import java.util.Set;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

public class CommonMethods implements WebDriverInstance {

    public Wait<WebDriver> wait = new FluentWait<>(ACTIVE_WEB_DRIVER).withTimeout(Duration.ofSeconds(20));

    private WebElement element = null;

    public void navigateToPage(String baseUrl) {
        ACTIVE_WEB_DRIVER.get(baseUrl);
    }

    public void isPageTitleDisplayed(String pageTitle) {
        String currentTitle = ACTIVE_WEB_DRIVER.getTitle();
        assertEquals(currentTitle, pageTitle);
    }

    public boolean isElementDisplayed(String accessType, String accessName) {
        element = wait.until(presenceOfElementLocated(getElementByType(accessType, accessName)));
        return element.isDisplayed();
    }

    public By getElementByType(String type, String access_name) {
        switch (type) {
            case "id":
                return By.id(access_name);
            case "name":
                return By.name(access_name);
            case "class":
                return By.className(access_name);
            case "xpath":
                return By.xpath(access_name);
            case "css":
                return By.cssSelector(access_name);
            case "linkText":
                return By.linkText(access_name);
            case "partialLinkText":
                return By.partialLinkText(access_name);
            case "tagName":
                return By.tagName(access_name);
            default:
                throw new IllegalArgumentException("Unknown element type: " + type);
        }
    }

    public void enterValueInTextField(String accessType, String value, String accessName) {
        wait.until(presenceOfElementLocated(getElementByType(accessType, accessName)));
        ACTIVE_WEB_DRIVER.findElement(getElementByType(accessType, accessName)).sendKeys(value);
    }

    public void clickOnButton(String accessType, String accessName) {
        wait.until(ExpectedConditions.elementToBeClickable(getElementByType(accessType, accessName)));
        ACTIVE_WEB_DRIVER.findElement(getElementByType(accessType, accessName)).click();
    }

    public void click(String accessType, String accessName) {
        element = wait.until(presenceOfElementLocated(getElementByType(accessType, accessName)));
        element.click();
    }

    public void verifyHeading(String text) {
        ACTIVE_WEB_DRIVER.findElement(By.cssSelector("h1")).getText().equals(text);
    }

    public void isElementVisible(String accessType, String value, String accessName) {
        ACTIVE_WEB_DRIVER.findElement(getElementByType(accessType, accessName)).isDisplayed();
    }

    public void selectRadioButton(String accessType, String accessName) {
        WebElement radioButton = wait.until(presenceOfElementLocated(getElementByType(
            accessType,
            accessName
        )));
        if (!radioButton.isSelected()) {
            radioButton.click();
        }
    }

    public void moveToNewTab() {
        Set<String> windowHandles = ACTIVE_WEB_DRIVER.getWindowHandles();
        if (windowHandles.size() > 2) {
            throw new IllegalArgumentException(format("Too many tabs %s", windowHandles.size()));
        }
        String currentTab = ACTIVE_WEB_DRIVER.getWindowHandle();
        for (String browserTab : windowHandles)
            if (!browserTab.equals(currentTab)) {
                ACTIVE_WEB_DRIVER.switchTo().window(browserTab);
            }
    }
}
