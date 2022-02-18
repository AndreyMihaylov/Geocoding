package RestAssuredVersion;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

import java.util.Map;

import static io.restassured.RestAssured.given;

import static Utils.Constants.*;
import static io.restassured.config.JsonConfig.jsonConfig;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.BIG_DECIMAL;
import static io.restassured.path.json.config.JsonPathConfig.NumberReturnType.DOUBLE;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;

public class HTTPMethods {

    protected ValidatableResponse get (Map<String,String> queryParams){
        ValidatableResponse response = given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE)))
                .filter(new AllureRestAssured())
                .queryParams(queryParams)
                .queryParams("key",KEY)
                .when()
                .get(URL)
                .then();
        return response;
    }

    protected ValidatableResponse getWithoutKey (Map<String,String> queryParams){
        ValidatableResponse response = given()
                .config(RestAssured.config().jsonConfig(jsonConfig().numberReturnType(DOUBLE)))
                .filter(new AllureRestAssured())
                .queryParams(queryParams)
                .when()
                .get(URL)
                .then()
                .statusCode(200);
        return response;
    }

}
