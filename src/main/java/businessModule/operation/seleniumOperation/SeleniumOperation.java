package businessModule.operation.seleniumOperation;

import businessModule.action.seleniumAction.SeleniumAction;
import businessModule.check.seleniumCheck.SeleniumCheck;
import org.openqa.selenium.WebDriver;

public class SeleniumOperation {

    SeleniumCheck seleniumCheck = new SeleniumCheck();
    SeleniumAction seleniumAction = new SeleniumAction();

    public boolean openBaiDuOperation(WebDriver driver) {
        seleniumAction.openBaiDu(driver, "https://www.baidu.com/");
        if (seleniumCheck.seleniumCheck(driver)) return true;
        return false;
    }
}
