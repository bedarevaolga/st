package steampowered.pages;

import framework.BaseSteamPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ActionPage extends BaseSteamPage {

    public ActionPage() {
        super(By.xpath("//h2[contains(text(), 'Action')]"));
    }

    private final By divDiscount = By.xpath("//div[@class='capsule header']/following-sibling::div/descendant::div[@class=\"discount_pct\"]");
    private static final String DIV_MAX_DISCOUNT = "//div[@class='capsule header']/following-sibling::div/descendant::div[@class=\"discount_pct\" and text()='%s']";
    private static String gameNameWithMaxDiscount = " ";
    private static String gemeName = "//h4[@class='hover_title']";

    public int findMaxDiscounts() {
        List<WebElement> discounts = baseElement.findElements(divDiscount);

        int maxDiscount = parseDiscounts(discounts.get(0).getText());

        for (WebElement element : discounts) {
            if (parseDiscounts(baseElement.getText(element)) > maxDiscount) {
                maxDiscount = parseDiscounts(baseElement.getText(element));
            }
        }
        return maxDiscount;
    }

    public void choseGameWithMaxDiscount() {
        String nameDisc = "-" + findMaxDiscounts() + "%";

        List<WebElement> maxDiscountsList = baseElement.findElementsByName(DIV_MAX_DISCOUNT, nameDisc);
        if (maxDiscountsList.size() == 1) {
            baseElement.moveToElement(maxDiscountsList.get(0));
            gameNameWithMaxDiscount = baseElement.getText(By.xpath(gemeName));
            baseElement.clickAndWait(DIV_MAX_DISCOUNT, nameDisc);
        } else {
            int random = (int) (Math.random() * (maxDiscountsList.size()));
            baseElement.moveToElement(maxDiscountsList.get(random));
            gameNameWithMaxDiscount = baseElement.getText(By.xpath(gemeName));
            baseElement.clickAndWait(maxDiscountsList.get(random));
        }
    }

    public static String getGameNameWithMaxDiscount() {
        return gameNameWithMaxDiscount;
    }


    public int parseDiscounts(String text) {
        return Integer.parseInt(text.substring(1, text.indexOf("%")));
    }
}
