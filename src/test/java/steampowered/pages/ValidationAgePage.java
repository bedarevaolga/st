package steampowered.pages;

import framework.BaseSteamPage;
import org.openqa.selenium.By;

public class ValidationAgePage extends BaseSteamPage {
    public ValidationAgePage() {
        super( By.xpath("//select[@name='ageYear']"));
    }

    private  By divAgeValidation = By.xpath("//div[text()='Please enter your birth date to continue:']");
    private  By sltAgeYear = By.xpath("//select[@name='ageYear']");
    private  By aViewPage = By.xpath("//span[text()='View Page']/..");

    public void ageChek(String year){
        if (baseElement.isElementPresentedOnPage(divAgeValidation)) {
            baseElement.select(sltAgeYear, year);
            baseElement.clickElement(aViewPage);
        }
    }

}
