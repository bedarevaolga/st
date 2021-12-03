package steampowered.pages;

import framework.BaseSteamPage;
import framework.Browser;
import framework.ConfigLoader;
import framework.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;

import static framework.Browser.configLoader;


public class InstallPage extends BaseSteamPage {

    public InstallPage() {
        super(By.xpath("//div[@class='online_stats']"));
    }

    private final By aInstallBtn = By.xpath("//a[@class='about_install_steam_link']");

    public void installGame() {
        baseElement.clickElement(aInstallBtn);
    }

    public boolean isDownloadsExists() {

      return baseElement.isDownloadsExists();
    }


}
