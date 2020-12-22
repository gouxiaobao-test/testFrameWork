import baseClass.seleniumBaseTest.CreateDriverBase;
import businessModule.operation.seleniumOperation.SeleniumOperation;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SeleniumCaseTest {

     public CreateDriverBase createDriverBase;
     private SeleniumOperation seleniumOperation = new SeleniumOperation();

    {
        try {
            createDriverBase = new CreateDriverBase();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void openBaiDuTest(){
        Assert.assertTrue(seleniumOperation.openBaiDuOperation(createDriverBase.getDriver()));
    }

    @AfterClass
    public void after(){
        createDriverBase.getDriver().quit();
    }
}
