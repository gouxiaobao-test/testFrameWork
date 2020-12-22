package dataProvider;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class dataProvider {

    public static JSONArray json = null;

    public  String readTestDataFromJson(String filePath) {
        File file = new File(filePath);
        String str;
        StringBuffer strBuffer = new StringBuffer();
        if (!file.exists()) {
            System.out.println("Can't Find" + filePath);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            BufferedReader in = new BufferedReader(inputStreamReader);

            while ((str = in.readLine()) != null) {
                strBuffer.append(str);
            }
            in.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        json = JSONObject.parseArray(strBuffer.toString());
        String data = strBuffer.toString();
        return data;
    }

    /**
     * 将jsonArray类型的数据转化成二维数组，作为testng的dataProvider的数据传递
     **/
    public  Object[][] getDataProviderFromJson(JSONArray jsonArray) {
        int size = jsonArray.size();
        Object objects[][] = new Object[size][1];
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            objects[i][0] = jsonObject;
        }
        for (Object[] jsonObjects : objects) {
            for (Object jsonObject : jsonObjects) {
            }
        }
        return objects;
    }
}
