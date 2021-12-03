package framework.elements;

import framework.Browser;
import framework.ConfigLoader;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.List;

import static framework.Browser.*;


public class BaseElement {

    protected static Browser browser = Browser.getInstance();
    protected String name;
    protected By locator;
    protected WebElement element;

    public BaseElement() {
    }

    protected BaseElement(final By loc) {
        locator = loc;
    }


    protected BaseElement(final By loc, final String nameOf) {
        locator = loc;
        name = nameOf;
    }

    protected BaseElement(String nameOf, final String nameOfElement) {
        name = nameOf;
    }


    public WebElement findElement(By locator) {
        return new WebDriverWait(browser.getDriver(), getImplicitlyWait())
                .until(driver -> driver.findElement(locator));
    }

    public List<WebElement> findElements(By locator) {
        return new WebDriverWait(browser.getDriver(), getImplicitlyWait())
                .until(driver -> driver.findElements(locator));
    }

    public WebElement findElementByName(String locator, String name) {
        return new WebDriverWait(browser.getDriver(), getImplicitlyWait())
                .until(driver -> driver.findElement(By.xpath(String.format(locator, name))));
    }

    public List<WebElement> findElementsByName(String locator, String name) {
        return new WebDriverWait(browser.getDriver(), getImplicitlyWait())
                .until(driver -> driver.findElements(By.xpath(String.format(locator, name))));
    }

    public void waitForPageToLoad() {
        new WebDriverWait(browser.getDriver(), Integer.parseInt(configLoader.getProperty("waitForPageToLoad"))).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void waitElementBeClickable(WebElement element) {
        new WebDriverWait(browser.getDriver(), Integer.parseInt(configLoader.getProperty("waitElementBeClickable"))).until(ExpectedConditions
                .elementToBeClickable(element));
    }

    public void waitElementBeClickable(By locator) {
        new WebDriverWait(browser.getDriver(), Integer.parseInt(configLoader.getProperty("waitElementBeClickable"))).until(ExpectedConditions
                .elementToBeClickable(locator));
    }

    public boolean isElementPresentedOnPage(By locator) {
        boolean isElementDisplayed = true;
        try {
            new WebDriverWait(browser.getDriver(), Integer.parseInt(configLoader.getProperty("waitElementVisible")))
                    .until(driver -> driver.findElement(locator));
        } catch (NoSuchElementException | TimeoutException e) {
            isElementDisplayed = false;
        }
        return isElementDisplayed;
    }

    public String getText() {
        waitElementBeClickable(element);
        return element.getText();
    }

    public String getText(By locator) {
        waitElementBeClickable(locator);
        return findElement(locator).getText();
    }

    public String getText(WebElement element) {
        waitElementBeClickable(element);
        return element.getText();
    }

    public void clickElement(By locator) {
        waitElementBeClickable(locator);
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", findElement(locator));
        }
        findElement(locator).click();
    }

    public void clickElement(WebElement element) {
        waitElementBeClickable(element);
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void clickElement() {
        waitElementBeClickable(element);
        if (browser.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) browser.getDriver()).executeScript("arguments[0].style.border='3px solid red'", element);
        }
        element.click();
    }

    public void clickAndWait(String xpath, String name) {
        clickElement(findElementByName(xpath, name));
        waitForPageToLoad();
    }

    public void clickAndWait(WebElement element) {
        clickElement(element);
        waitForPageToLoad();
    }

    public void select(By locator, String value) {
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(value);
    }

    public void sendKeysEsc() {
        Actions action = new Actions(browser.getDriver());
        action.sendKeys(Keys.ESCAPE).build().perform();
    }

    public void moveToElement(WebElement element) {
        Actions action = new Actions(browser.getDriver());
        action.moveToElement(element).build().perform();
    }

    public String getURLPage() {
        System.out.println(browser.getDriver().getCurrentUrl());
        return browser.getDriver().getCurrentUrl();
    }


    public boolean isDownloadsExists() {

        WebDriverWait wait = new WebDriverWait(browser.getDriver(), getForPageToLoadWait());
        String downloadPath = System.getProperty("user.dir") + "\\" + configLoader.getProperty("downloadPath");
        File file = new File(downloadPath + configLoader.getProperty("downloadedFile"));
        wait.until(driver -> file.exists());
        return file.exists();
    }

    public static void deleteInstalledFile() {
        File file = new File(configLoader.getProperty("downloadPath") + configLoader.getProperty("downloadedFile"));
        if (file.exists()) {
            file.delete();
        }
    }
}
