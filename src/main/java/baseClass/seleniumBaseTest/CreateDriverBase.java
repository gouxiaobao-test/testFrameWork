package baseClass.seleniumBaseTest;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.net.MalformedURLException;

public class CreateDriverBase {
    public WebDriver driver;

    public WebDriver getDriver(){
     return driver;
    }

    public CreateDriverBase() throws MalformedURLException, InterruptedException {
        init();
    }

    /**
     * 初始化浏览器
     * 注释部分为链接selenium.hub的的代码，如需要可开启
     **/
    public void init() throws MalformedURLException, InterruptedException {
        /**
         System.out.println("start create driver");
         driver = new RemoteWebDriver(new URL("http://192.168.199.154:4444/wd/hub"), chromeCapabilities);
         System.out.println("driver create success");
         }
         */
        System.out.println("start create driver");
        System.setProperty("webdriver.chrome.driver", "./file/driverFile/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.addArguments(new String[]{"test-type"});
        /**
         * 加载测试网页所需要的插件
         **/
//        loadingPlugIns(options,"插件路径");
        driver = new ChromeDriver(options);
        /**
         * 配置插件所需要执行的js代码
         **/
//        configurePlugs(driver,"这里是插件所配合要使用的js代码");
        System.out.println("driver create success");
        driver.manage().window().maximize();
    }
    // }


    /**
     * 用来配置chrome的插
     * 可以用来执行js语言
     **/
    public void configurePlugs(WebDriver driver,String jsCode) throws InterruptedException {
        String js = "var scriptEle = document.createElement(\"script\");\n" +
                "  scriptEle.async = true;\n" +
                "  scriptEle.src = \"https://sharingan-resource.mastergo.com/static/js/app.js?v=\" + Date.now();\n" +
                "  document.body.appendChild(scriptEle);";
        JavascriptExecutor driver_js = ((JavascriptExecutor) driver);
        Thread.sleep(1000);
        driver_js.executeScript(jsCode);
    }

    /**
     * 加载插件
     * 如测试网页需要加载插件可以调用
     **/
    public void loadingPlugIns(ChromeOptions options,String filePath){
        options.addExtensions(new File(filePath));

    }


}
