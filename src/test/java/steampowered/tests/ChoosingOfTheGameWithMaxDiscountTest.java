package steampowered.tests;

import framework.BaseSteamPage;
import framework.Browser;
import framework.ConfigLoader;
import framework.Page;
import framework.elements.BaseElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import steampowered.pages.*;


import static framework.Browser.configLoader;


public class ChoosingOfTheGameWithMaxDiscountTest {

    private static Browser currentBrowser;
    public static ConfigLoader localLoader;

    @BeforeClass
    public static void testSetup() {
        currentBrowser = Browser.getInstance();
        currentBrowser.navigate(configLoader.getProperty("url"));
        BaseElement.deleteInstalledFile();
    }

    @Test
    @Parameters({"language", "year"})
    public void testChooseGameWithMaxDiscount(String language, String year) {

        MainPage mainPage = new MainPage();
        mainPage.changeLanguage(language);
        localLoader = new ConfigLoader(String.format("localization/local_%1$s.properties", language.toLowerCase()));
        mainPage.navigateSection("section", "subSection");

        ActionPage actionPage = new ActionPage();
        actionPage.choseGameWithMaxDiscount();

        GamePage gamePage = new GamePage();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(gamePage.getGameName(year), ActionPage.getGameNameWithMaxDiscount());
        gamePage.clickInstallSteam("Install Steam");

        InstallPage installPage = new InstallPage();
        installPage.installGame();
        softAssert.assertTrue(installPage.isDownloadsExists());
    }

    @AfterClass
    public void closeBrowser() {
        Browser.teardown();
    }
}
