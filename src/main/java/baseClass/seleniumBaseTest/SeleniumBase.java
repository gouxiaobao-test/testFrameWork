package baseClass.seleniumBaseTest;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

public class SeleniumBase {

    protected WebDriver driver;
    private static final int elementWaitTime = 30;


    public boolean elementWaitHandle(WebDriver driver, By elementBy, SeleniumUtilInterface handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
            if (handle != null) {
                handle.action(element);
            }
            return true;
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            return false;
        } catch (TimeoutException e) {
            e.fillInStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.fillInStackTrace();
            return false;
        }
    }

    public boolean elementIsExitBase(WebDriver driver, By elementBy){
        try {
            WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
            wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));;
            return true;
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            return false;
        } catch (Exception e) {
            e.fillInStackTrace();
            System.out.println(e.getClass().getName());
            return true;
        }
    }


    public WebElement getElementBase(WebDriver driver, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
        return element;
    }

    public List<WebElement> getElementsBase(WebDriver driver, By elementBy) {
        WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
        List<WebElement> element = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(elementBy));
        return element;
    }


    public boolean elementWaitHandle(By elementBy, SeleniumUtilInterface handle) throws InterruptedException {
        try {
            WebDriverWait wait = new WebDriverWait(driver, elementWaitTime);
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(elementBy));
            if (handle != null) {
                handle.action(element);
            }
            return true;
        } catch (NoSuchElementException e) {
            e.fillInStackTrace();
            return false;
        } catch (TimeoutException e) {
            e.fillInStackTrace();
            return false;
        } catch (NullPointerException e) {
            e.fillInStackTrace();
            return false;
        }
    }
}
