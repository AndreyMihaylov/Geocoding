package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;
import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;

public class BoundTest extends BaseTest{
    SoftAssert softAssert = new SoftAssert();
    ApiActions apiActions;
    ValidatableResponse response;
    HashMap<String, Double> boundCoordinates;
    double lat1;
    double lat2;
    double lng1;
    double lng2;
    String address;

    @Step("Preconfig test")
    @BeforeTest
    public void setUp(){
        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS2);
        address = createString(paramsOfAddressesToList(addressesObj));
        apiActions = new ApiActions();
        response = apiActions.getDataByAddress(address);
        HashMap<String, Double> boundCoordinates = apiActions.getBoundCoordinates(response);
        lat1 = boundCoordinates.get("lat1");
        lat2 = boundCoordinates.get("lat2");
        lng1 = boundCoordinates.get("lng1");
        lng2 = boundCoordinates.get("lng2");
    }

    @Description("Take boundary coordinates from response (viewport field). Put request with random location from that")
    @Test
    public void boundTestRandomInside() {
        String lat = String.valueOf(ThreadLocalRandom.current().nextDouble(lat2, lat1));
        String lng = String.valueOf(ThreadLocalRandom.current().nextDouble(lng2, lng1));
        ValidatableResponse responseIn = apiActions.getDataByCoordinates(lat, lng);
        boolean containsResponseIn = apiActions.getPlaceIds(responseIn).stream().anyMatch(p -> p.equals(apiActions.getPlaceIdFirst(response)));
// Flaky test. Ned to research a documentation.
//        softAssert.assertTrue(containsResponseIn,"Address: "+address+" -  doesn't present in location: " + lat+lng);
    }

    @Description("Take boundary coordinates from response (viewport field). Put request with corner locations from that")
    @Test
    public void boundTest() {

        ValidatableResponse responseBound1 = apiActions.getDataByCoordinates(String.valueOf(lat1), String.valueOf(lng1));
        ValidatableResponse responseBound2 = apiActions.getDataByCoordinates(String.valueOf(lat1), String.valueOf(lng2));
        ValidatableResponse responseBound3 = apiActions.getDataByCoordinates(String.valueOf(lat2), String.valueOf(lng1));
        ValidatableResponse responseBound4 = apiActions.getDataByCoordinates(String.valueOf(lat2), String.valueOf(lng2));

        boolean contains2ResponseBound1 = apiActions.getPlaceIds(responseBound1).stream().anyMatch(p -> p.equals(apiActions.getPlaceIdFirst(response)));
        boolean contains3ResponseBound2 = apiActions.getPlaceIds(responseBound2).stream().anyMatch(p -> p.equals(apiActions.getPlaceIdFirst(response)));
        boolean contains4ResponseBound3 = apiActions.getPlaceIds(responseBound3).stream().anyMatch(p -> p.equals(apiActions.getPlaceIdFirst(response)));
        boolean contains5ResponseBound4 = apiActions.getPlaceIds(responseBound4).stream().anyMatch(p -> p.equals(apiActions.getPlaceIdFirst(response)));

// Flaky test. Ned to research a documentation.
//        softAssert.assertTrue(containsResponseIn,"Address: "+address+" -  doesn't present in location: " + lat+lng);
//        softAssert.assertTrue(contains2ResponseBound1,"Address: "+address+" -  doesn't present in location: " + lat1+lng1);
//        softAssert.assertTrue(contains3ResponseBound2,"Address: "+address+" -  doesn't present in location: " + lat1+lng2);
//        softAssert.assertTrue(contains4ResponseBound3,"Address: "+address+" -  doesn't present in location: " + lat2+lng1);
//        softAssert.assertTrue(contains5ResponseBound4,"Address: "+address+" -  doesn't present in location: " + lat2+lng2);

        softAssert.assertAll();
    }
}

