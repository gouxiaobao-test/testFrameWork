package baseClass.apiBaseTest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author gouxiaobao
 * @description 用来对比两个json的整体数据，常用与接口返回和预期数据做对比
 **/
public class JsonDiffUtil {

    int status = 0;
    String kold1;

    public static class DiffResult {

        //如果值为true代表有diff,默认出入的json是相等的
        private Boolean hasDiff = false;
        //diff的数据
        private String diffMessage;

        private JSONObject diffJsonMessage = new JSONObject();

        //获取比较状态
        public Boolean getHasDiff() {
            return hasDiff;
        }

        //构建比较状态
        public void setHasDiff(Boolean hasDiff) {
            this.hasDiff = hasDiff;
        }

        //获取不同的消息
        public String getDiffMessage() {
            return diffMessage;
        }

        public JSONObject getDiffJsonMessage() {
            return diffJsonMessage;
        }

        public void setDiffJsonMessage(JSONObject diffJsonMessage) {
            this.diffJsonMessage = diffJsonMessage;
        }

        //构建不同的信息
        public void setDiffMessage(String diffMessage) {
            this.diffMessage = diffMessage;
        }

        //转为字符串的，同时打印出比较状态和比较消息
        @Override
        public String toString() {
            return "DiffResult{" +
                    "hasDiff=" + hasDiff +
                    ", diffMessage='" + diffMessage + '\'' +
                    '}';
        }
    }

    public JSONObject saveDiffJsonMessage(String key, String message) {
        diffResult.diffJsonMessage.put(key, message);
        return diffResult.diffJsonMessage;
    }

    DiffResult diffResult = new DiffResult();

    //判断传入的数据类型已经比较方式
    public DiffResult compareObject(Object oldObject, Object newObject, String key, int index, List<String> list) {
        if (oldObject == null || newObject == null) {
            diffResult.hasDiff = true;
            diffResult.setHasDiff(true);
            diffResult.setDiffMessage(key + " 的value中old和new有一个或者两个为null");
            diffResult.setDiffJsonMessage(saveDiffJsonMessage(key, "两者的" + key + "所对应的value有一个为null"));
            return diffResult;
        }
        if (oldObject != null && newObject != null) {
            if (diffResult.getHasDiff()) {
                return diffResult;
            }

            if (oldObject != null && newObject != null && oldObject.getClass() != newObject.getClass()) {
                diffResult.hasDiff = true;
                diffResult.setDiffMessage(key + " 的value的old和new 的类型不一致");
                diffResult.setDiffJsonMessage(saveDiffJsonMessage(key, "两者的" + key + "所对应的value类型不一样"));
                return diffResult;
            }

            if (oldObject instanceof JSONObject && newObject instanceof JSONObject) {
                compareJsonObject((JSONObject) oldObject, (JSONObject) newObject, key, index, list);
                if (diffResult.getHasDiff()) {
                    return diffResult;
                }

            } else if (oldObject instanceof JSONArray && newObject instanceof JSONArray) {
                compareJsonArray((JSONArray) oldObject, (JSONArray) newObject, key, index);
                if (diffResult.getHasDiff()) {
                    return diffResult;
                }

            } else {
                String oldStr = oldObject.toString();
                String newStr = newObject.toString();
                if (!oldStr.equals(newStr)) {
                    diffResult.hasDiff = true;
                    diffResult.setDiffMessage("index: " + index + ", " + key + " 的value中old和new 的值不相等");
                    diffResult.setDiffJsonMessage(saveDiffJsonMessage(key, "两者的" + key + "所对应的value不一样"));
                    return diffResult;
                }
            }
        }
        return diffResult;
    }

    //比较jsonArray
    public DiffResult compareJsonArray(JSONArray oldJarr, JSONArray newJarr, String key, int index) {
        if (diffResult.getHasDiff()) {
            return diffResult;
        }
        if (oldJarr == null || newJarr == null) {
            diffResult.hasDiff = true;
            diffResult.setDiffMessage(key + " 的value中两个结果存在null");
            diffResult.setDiffJsonMessage(saveDiffJsonMessage("message", "有一个jsonArray为null"));
            return diffResult;
        }
        if (oldJarr.size() != newJarr.size()) {
            diffResult.hasDiff = true;
            diffResult.setDiffMessage("index:" + index + ", " + key + " 的value中old和new 数组size不相等");
            diffResult.setDiffJsonMessage(saveDiffJsonMessage("message", "两个jsonArray的size大小不一样"));
            return diffResult;
        }

        //jsonArray中元素是个object，排序之后再比较
        if (oldJarr.size() > 0 && !(oldJarr.get(0) instanceof JSONObject) && !(oldJarr.get(0) instanceof JSONArray)) {
            String[] arrOld = new String[oldJarr.size()];
            String[] arrNew = new String[oldJarr.size()];
            List<String> tmp = new ArrayList<String>();
            for (int i = 0; i < arrOld.length; i++) {
                arrOld[i] = oldJarr.get(i).toString();
                arrNew[i] = newJarr.get(i).toString();
                tmp.add(oldJarr.get(i).toString());
            }
            Arrays.sort(arrOld);
            Arrays.sort(arrNew);
            for (int i = 0; i < arrNew.length; i++) {
                if (!arrOld[i].equals(arrNew[i])) {
                    diffResult.hasDiff = true;
                    diffResult.setDiffMessage("index:" + index + ", " + key + " 的value中第" + tmp.indexOf(arrOld[i]) + "个old和new 值不相等");
                    diffResult.setDiffJsonMessage(saveDiffJsonMessage("message", key + " 的value中第" + tmp.indexOf(arrOld[i]) + "个old和new 值不相等"));
                    return diffResult;
                }
            }

        } else {
            for (int i = 0; i < oldJarr.size(); i++) {
                if (oldJarr.get(i) != null && newJarr.get(i) != null && oldJarr.get(i).getClass() != newJarr.get(i).getClass()) {
                    diffResult.hasDiff = true;
                    diffResult.setDiffMessage("index:" + index + ", " + key + " 的value中old和new 的类型不一致");
                    diffResult.setDiffJsonMessage(saveDiffJsonMessage(key, "数据类型不一样"));

                    return diffResult;
                }
                if (oldJarr.get(i) instanceof JSONObject) {
                    JSONObject jold = oldJarr.getJSONObject(i);
                    JSONObject jnew = newJarr.getJSONObject(i);
                    if (jold.equals(jnew)) {
                        continue;
                    } else {
//                        Boolean cd = customHasDiff(oldJarr,newJarr,key,i,diffResult);
//                        if(!cd) continue;
//                        compareJsonObject((JSONObject)oldJarr.get(i),(JSONObject)newJarr.get(i),key,i);
//                        if(diffResult.getHasDiff()){
//                            return diffResult;
//                        }
                    }
                } else if (oldJarr.get(i) instanceof JSONArray) {
                    compareJsonArray((JSONArray) oldJarr.get(i), (JSONArray) newJarr.get(i), key, i);
                    if (diffResult.getHasDiff()) {
                        return diffResult;
                    }
                }
            }
        }
        return diffResult;
    }

    //比较jsonObject
    public DiffResult compareJsonObject(JSONObject oldJson, JSONObject newJson, String key, int index, List<String> filterDataList) {
        if (diffResult.getHasDiff()) {
            return diffResult;
        }
        if (oldJson == null || newJson == null) {
            diffResult.hasDiff = true;
            diffResult.setDiffMessage(key + " 的value中两个结果有一个为null");
            return diffResult;
        }

        Set<String> sold = oldJson.keySet();
        Set<String> snew = newJson.keySet();
        if (key.isEmpty()) {
            key = "";
        }
        //判断JSONObject的大小
        if (sold.size() != snew.size()) {
            diffResult.hasDiff = true;
            diffResult.setDiffMessage(key + " 的keySet的数量不一致，线上有" + sold.size() + "个key,待测服务有" + snew.size() + "个key");
            diffResult.setDiffJsonMessage(saveDiffJsonMessage("message", "两个json的大小不一样"));
            return diffResult;
        }

        //key是否相同
        for (String kold : sold) {
            if (!snew.contains(kold)) {
                diffResult.hasDiff = true;
                diffResult.setDiffMessage("待测服务的" + key + "的keyset不包含" + kold);
                diffResult.setDiffJsonMessage(saveDiffJsonMessage("message", "两个json的key不一样"));
                return diffResult;
            }
        }
        //需要过滤的key
        for (String kold : sold) {
            boolean a = true;
            for (int i = 0; i < filterDataList.size(); i++) {
                String value = filterDataList.get(i);
                if (kold.equals(value)) {
                    a = false;
                    break;
                }
            }
            if (!a) {
                continue;
            }
            Object oldObject = oldJson.get(kold);
            Object newObject = newJson.get(kold);
            compareObject(oldObject, newObject, key + kold, index, filterDataList);
            if (diffResult.getHasDiff()) {
                return diffResult;
            }
        }
        return diffResult;
    }
}

