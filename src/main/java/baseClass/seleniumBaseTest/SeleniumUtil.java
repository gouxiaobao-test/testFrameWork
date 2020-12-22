package baseClass.seleniumBaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.logging.Level;

public class SeleniumUtil extends SeleniumBase {


    /**
     * 元素点击
     **/
    public boolean elementClick(WebDriver driver, By element) throws InterruptedException {
        if (!elementWaitHandle(driver, element, new SeleniumUtilInterface() {
            @Override
            public void action(WebElement element) {
                element.click();
            }
        })) return false;
        return true;
    }

    /**
     * 数据输入
     **/
    public boolean elementSendKeys(WebDriver driver, By elementBy, final String elementInput) throws InterruptedException {
        if (!elementWaitHandle(driver, elementBy, new SeleniumUtilInterface() {
            @Override
            public void action(WebElement element) {
                element.sendKeys(elementInput);
            }
        })) return false;
        return true;
    }

    /**
     * 检查元素是否存在
     **/
    public boolean elementIsExit(WebDriver driver, By elementBy) {
        if (!elementIsExitBase(driver, elementBy)) return false;
        return true;
    }


    /**
     * 查找父类节点
     *
     * @return 返回当前的父节点
     **/
    public WebElement findFatherElement(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        return element;
    }


    /**
     * 二次定位
     **/
    public WebElement findChildrenElement(WebDriver driver, By fatherBy, By childrenBy) {
        WebElement element = findFatherElement(driver, fatherBy);
        return element.findElement(childrenBy);
    }

    /**
     * 获取装元素的list
     **/
    public List<WebElement> getElements(WebDriver driver, By elementBy) {
        return getElementsBase(driver, elementBy);
    }

    /**
     * 获取元素的文本消息
     **/
    public String getElementText(WebDriver driver, final By elementBy) {
        return getElementBase(driver, elementBy).getText();
    }


    /**
     * 获取元素key所对应的value
     **/
    public String getElementAttribute(WebDriver driver, final By elementBy, String key) {
        return getElementBase(driver, elementBy).getAttribute(key);
    }


    /**
     * 判断元素的文本消息是否等于常量
     **/
    public boolean elementTextIsEqual(WebDriver driver, By elementBy, String text) {
        if (getElementBase(driver, elementBy).getText().equals(text)) return true;
        return false;
    }

    /**
     * 判断元素的文本消息是否包含特定常量
     **/
    public boolean elementTextIsContains(WebDriver driver, By elementBy, String text) {
        if (getElementBase(driver, elementBy).getText().contains(text)) return true;
        return false;
    }

    /**
     * 判断元素是否可见
     **/
    public boolean elementIsShow(WebDriver driver, By elementBy) {
        if (getElementBase(driver, elementBy).isDisplayed()) return true;
        return false;
    }

    /**
     * 刷新当前页面
     **/
    public void refreshPage(WebDriver driver) {
        try {
            driver.navigate().refresh();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     * 关闭当前浏览器
     **/
    public void quitBrowser(WebDriver driver) {
        try {
            driver.quit();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }

    /**
     *
     **/
}
