package baseClass.apiBaseTest;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * @author gouxiaobao
 * @date 2020年12月16日 下午17:43:30
 **/

public class ApiBase {


    public static class RestAssured {
        public static final String contentType = "application/json";

        /**
         * @param url 接口地址
         * @param body 附带参数 json的格式进行参数传递
         **/
        public String post(String url, JSONObject body) {
            String result = given().contentType(contentType)
                    .body(body.toString())
                    .when().post(url)
                    .then().extract().asString();
            return result;
        }

        /**
         * @param url 接口地址
         **/
        public String get(String url) {
            String result = given().contentType(contentType)
                    .when().get(url)
                    .then().extract().asString();
            return result;
        }

        /**
         * @param url 接口地址
         * @param cookieName cookie的key
         * @param cookieValue  cookie的value
         **/

        public String get(String url, String cookieName,String cookieValue) {
            String result = given().contentType(contentType)
                    .cookie(cookieName, cookieValue)
                    .when().get(url)
                    .then().extract().asString();
            return result;
        }


        /**
         * @param url 接口地址
         * @param parameter 附带参数 以map的形式进行传递，传递参数类型为queryParams
         **/
        public String get(String url, Map<String, Object> parameter) {
            String result = given().contentType(contentType)
                    .queryParams(parameter)
                    .when().get(url)
                    .then().extract().asString();
            return result;
        }


        /**
         * @param url 接口地址
         * @param body 附带参数 以json的形式进行传递
         **/
        public String delete(String url, JSONObject body) {
            String result = given().contentType(contentType)
                    .body(body.toString())
                    .when().delete(url)
                    .then().extract().asString();
            return result;
        }


        /**
         * @param url 接口地址
         * @param body 附带参数 以json的形式进行传递
         **/
        public String put(String url, JSONObject body) {
            String result = given().contentType(contentType)
                    .body(body.toString())
                    .when().put(url)
                    .then().extract().asString();
            return result;
        }
    }

}
