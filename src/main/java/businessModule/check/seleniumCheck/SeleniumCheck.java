package businessModule.check.seleniumCheck;

import baseClass.seleniumBaseTest.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SeleniumCheck {

    SeleniumBase seleniumBase = new SeleniumBase();

    public boolean seleniumCheck(WebDriver driver) {
        if (seleniumBase.elementIsExitBase(driver, By.id("form"))) {
            return true;
        }
        return false;
    }
}
