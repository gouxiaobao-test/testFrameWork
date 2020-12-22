import businessModule.operation.apiOperation.ExampleOneOperation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ApiCaseTest {

    ExampleOneOperation exampleOneOperation = new ExampleOneOperation();


    @Test
    public void TextOne(){
        Assert.assertTrue(exampleOneOperation.visitBaiDuOperation());
    }
}
