package businessModule.action.apiAction;

import baseClass.apiBaseTest.ApiBase;

public class ExampleOneAction {

    ApiBase.RestAssured restAssured = new ApiBase.RestAssured();

    public String visitBaiDu() {
       String result =  restAssured.get("https://www.baidu.com/");
       return result;
    }
}
