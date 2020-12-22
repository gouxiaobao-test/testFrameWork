package baseClass.seleniumBaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class SeleniumAction {

    private Actions actions;
    private SeleniumBase seleniumBase = new SeleniumBase();

    /**
     * 模拟键盘输入文本消息
     **/
    public boolean keyBoardInput(WebDriver driver, String input) {
        actions = new Actions(driver);
        try {
            actions.sendKeys(input).perform();
            return true;
        } catch (Exception e) {
            e.fillInStackTrace();
            return false;
        }
    }

    /**
     * 获取input输入框的内容
     **/
    public String getInputElementValue(WebDriver driver, By elementBy) {
        String inputValue = null;
        try {
            inputValue = seleniumBase.getElementBase(driver, elementBy).getAttribute("value");
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return inputValue;
    }
}
