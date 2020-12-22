package businessModule.check.apiCheck;

import com.alibaba.fastjson.JSONObject;

public class ExampleOneCheck {

    public boolean exampleOneCheck(String result){
        if (result.contains("百度一下，你就知道"))return true;
        return false;
    }
}
