package baseClass;

import com.alibaba.fastjson.JSONObject;

/**
 * @author gouxiaobao
 * @description 用来保存测试条件中的数据，可以全局使用，使用时直接调用即可
 * 需要在保存的时候先save，然后在使用的地方get即可
 **/

public class PublicDataSave {

    private JSONObject saveDataJson = new JSONObject();

    public JSONObject getSaveDataJson() {
        return saveDataJson;
    }

    public void setSaveDataJson(JSONObject saveDataJson) {
        this.saveDataJson = saveDataJson;
    }

    public JSONObject saveData(String key,String value){
        saveDataJson.put(key,value);
        return saveDataJson;
    }
}
