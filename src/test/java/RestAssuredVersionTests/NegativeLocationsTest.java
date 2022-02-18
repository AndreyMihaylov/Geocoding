package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

import static Utils.CommonUtils.getRandomString;

public class NegativeLocationsTest extends BaseTest {

    ApiActions apiActions;

    String error_message = "Invalid request. Invalid 'latlng' parameter.";

    @Description("Send request with 'latlng' key without comma between 'lat' and 'lng' ")
    @Test
    public void smokeRequestByLocationNoComma() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();

        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinates(lat, lng);
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address");

    }

    @Description("Send request with 'latlng' key with spaces between 'lat' and 'lng' ")
    @Test
    public void smokeRequestByLocationWithSpace() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();

        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinates("   " + lat + "   ", "   " + lng + "   ");
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address");

    }

    @Description("Send request with 'latlng' key with extra digits")
    @Test
    public void smokeRequestByLocationWithExtraDigits() {

        Random random = new Random();
        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();

        String lat = addressesObj.getLat() + String.valueOf(Math.abs(random.nextLong())) + String.valueOf(Math.abs(random.nextLong()));
        String lng = addressesObj.getLng() + String.valueOf(Math.abs(random.nextLong())) + String.valueOf(Math.abs(random.nextLong()));
        ValidatableResponse response = apiActions.getDataByCoordinates(lat, lng);
        lat = addressesObj.getLat();
        lng = addressesObj.getLng();
        ValidatableResponse response2 = apiActions.getDataByCoordinates(lat, lng);
        Assert.assertTrue(response.extract().body().asString().equals(response2.extract().body().asString()), "Problem with location of address");

    }

    @Description("Send request with 'latlng' key with non-digits")
    @Test
    public void smokeRequestByLocationNonDigit() {

        apiActions = new ApiActions();

        String lat = getRandomString(5);
        String lng = getRandomString(7);
        ValidatableResponse response = apiActions.getDataByCoordinates(lat, lng);
        Assert.assertTrue(response.extract().statusCode() == 400
                && response.extract().body().path("error_message").equals(error_message), "Problem with location of address");

    }

    @Description("Send request with 'latlng' key with Out Of Boundary coordinates")
    @Test
    public void smokeRequestByLocationOutOfBoundary() {

        SoftAssert softAssert = new SoftAssert();
        apiActions = new ApiActions();
        ValidatableResponse response1 = apiActions.getDataByCoordinates("-91", "100");
        ValidatableResponse response2 = apiActions.getDataByCoordinates("91", "100");
        ValidatableResponse response3 = apiActions.getDataByCoordinates("70", "181");
        ValidatableResponse response4 = apiActions.getDataByCoordinates("70", "-181");
        ValidatableResponse response5 = apiActions.getDataByCoordinates("90", "-101");
        ValidatableResponse response6 = apiActions.getDataByCoordinates("-90", "-101");
        ValidatableResponse response7 = apiActions.getDataByCoordinates("70", "0");
        ValidatableResponse response8 = apiActions.getDataByCoordinates("70", "-180");

        softAssert.assertTrue(response1.extract().statusCode() == 400
                && response1.extract().body().path("error_message").equals(error_message), "Problem with out of 'lat' location");
        softAssert.assertTrue(response2.extract().statusCode() == 400
                && response2.extract().body().path("error_message").equals(error_message), "Problem with out of 'lat' location");
        softAssert.assertTrue(response3.extract().statusCode() == 400
                && response3.extract().body().path("error_message").equals(error_message), "Problem with out of 'lng' location");
        softAssert.assertTrue(response4.extract().statusCode() == 400
                && response4.extract().body().path("error_message").equals(error_message), "Problem with out of 'lng' location");
        softAssert.assertTrue(response5.extract().statusCode() == 200, "Problem with boundary 'lat' location");
        softAssert.assertTrue(response6.extract().statusCode() == 200, "Problem with boundary 'lat' location");
        softAssert.assertTrue(response7.extract().statusCode() == 200, "Problem with boundary 'lng' location");
        softAssert.assertTrue(response8.extract().statusCode() == 200, "Problem with boundary 'lng' location");

        softAssert.assertAll();
    }

    @Description("Send request with 'latlng' key is empty or 'null'")
    @Test
    public void smokeRequestByLocationEmpty() {

        apiActions = new ApiActions();

        ValidatableResponse response = apiActions.getDataByCoordinates("", "");
        ValidatableResponse response2 = apiActions.getDataByCoordinates(null, null);
        Assert.assertTrue(response.extract().statusCode() == 400
                && response.extract().body().path("error_message").equals("Invalid request. Missing the 'address', 'components', 'latlng' or 'place_id' parameter."), "Problem with empty locations");
        Assert.assertTrue(response2.extract().statusCode() == 400
                && response.extract().body().path("error_message").equals("Invalid request. Missing the 'address', 'components', 'latlng' or 'place_id' parameter."), "Problem with 'null' locations");

    }
}
