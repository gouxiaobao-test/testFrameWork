package businessModule.action.seleniumAction;

import org.openqa.selenium.WebDriver;

public class SeleniumAction {

    /**
     * 打开某一个网址
     **/
    public void openBaiDu(WebDriver driver, String url){
        try{
            driver.get(url);
        }catch (Exception e){
            e.fillInStackTrace();
        }
    }
}
