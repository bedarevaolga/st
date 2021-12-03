package steampowered.pages;

import framework.BaseSteamPage;

import org.openqa.selenium.*;

public class GamePage extends BaseSteamPage {
    public GamePage() {
        super(By.xpath("//img[@class='game_header_image_full']"));
    }


    private By divGameName = By.xpath("//div[@id='appHubAppName']");


    public void clickInstallSteam(String installBtn) {
        baseElement.clickAndWait(A_BUTTON, installBtn);
    }


    public String getGameName(String year) {

        if (baseElement.getURLPage().contains("agecheck")) {
            ValidationAgePage validationAgePage = new ValidationAgePage();
            validationAgePage.ageChek(year);
        }

        return baseElement.getText(divGameName);
    }


}
