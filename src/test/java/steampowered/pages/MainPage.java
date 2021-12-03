package steampowered.pages;

import framework.BaseSteamPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static steampowered.tests.ChoosingOfTheGameWithMaxDiscountTest.localLoader;


public class MainPage extends BaseSteamPage {

    public MainPage() {
        super(By.xpath("//div[@class='home_page_gutter']"));
    }

    private static  String aSection = "//a[@class= 'pulldown_desktop' and text()='%s']";
    private static  String aSubSection = "//div[contains(@class, 'popup_menu_subheader popup_genre_expand_header')]/child::a[contains(text(), '%s')]";

    public void changeLanguage(String language) {
        baseElement.clickElement(spnLanguage);
        List<WebElement> languages = baseElement.findElements(aLanguages);
        for (WebElement element : languages) {
            if (baseElement.getText(element).contains(language)) {
                baseElement.clickElement(element);
                return;
            }
        }
        baseElement.sendKeysEsc();
    }

    public void navigateSection(String section, String subsection) {

        baseElement.clickElement(baseElement.findElementByName(aSection, localLoader.getProperty(section)));
       baseElement.clickElement(baseElement.findElementByName(aSubSection, localLoader.getProperty(subsection)));

    }
}


