package framework.elements;

import org.openqa.selenium.By;

public class Button extends BaseElement{

    public Button(final By locator, final String name) {
        super(locator, name);
    }


    public Button(By locator) {
        super(locator);
    }

}
