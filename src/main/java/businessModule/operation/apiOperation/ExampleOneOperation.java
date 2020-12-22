package businessModule.operation.apiOperation;

import businessModule.action.apiAction.ExampleOneAction;
import businessModule.check.apiCheck.ExampleOneCheck;

public class ExampleOneOperation {

    ExampleOneCheck exampleOneCheck = new ExampleOneCheck();
    ExampleOneAction exampleOneAction = new ExampleOneAction();

    public boolean visitBaiDuOperation() {
        String result = exampleOneAction.visitBaiDu();
        System.out.println(result);
        if (exampleOneCheck.exampleOneCheck(result)) return true;
        return false;
    }
}
