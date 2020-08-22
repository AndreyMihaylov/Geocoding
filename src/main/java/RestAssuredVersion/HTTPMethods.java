package RestAssuredVersion;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

import static Utils.Constants.*;

public class HTTPMethods {

    protected ValidatableResponse get(Map<String,String> queryParams){
        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .queryParams(queryParams)
                .queryParams("key",KEY)
                .when()
                .get(URL)
                .then();
        return response;
    }


}
