package framework;

import framework.elements.BaseElement;
import framework.elements.Button;
import org.openqa.selenium.By;

import org.testng.Assert;

public class BaseSteamPage  {

    protected BaseElement baseElement;
    protected static By titleLocator;

    protected Button btnLanguage = new Button(By.xpath("//span[@class='pulldown global_action_link']"));
  protected By spnLanguage = By.xpath("//span[@class='pulldown global_action_link']");//убрать
    protected By aLanguages = By.xpath("//a[@class=\"popup_menu_item tight\"]");//переделать
    protected static final String A_BUTTON = "//a[contains(text(),'%s')]";

    protected BaseSteamPage(final By locator) {
        init(locator);
        baseElement = new BaseElement();
        Assert.assertTrue(baseElement.isElementPresentedOnPage(locator));
    }


    private void init(final By locator) {
        titleLocator = locator;
    }
}
