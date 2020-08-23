package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;

public class BoundTest {
    ApiActions apiActions;

    @Test
    public void boundTest() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddress(address);
        HashMap<String, Double> boundCoordinates = apiActions.getBoundCoordinates(response);

        System.out.println(response.extract().body().asString());
        double lat1 = boundCoordinates.get("lat1");
        double lat2 = boundCoordinates.get("lat2");
        double lng1 = boundCoordinates.get("lng1");
        double lng2 = boundCoordinates.get("lng2");
        String lat = String.valueOf(ThreadLocalRandom.current().nextDouble(lat2, lat1));
        String lng = String.valueOf(ThreadLocalRandom.current().nextDouble(lng2, lng1));
        ValidatableResponse responseIn = apiActions.getDataByCoordinate(lat, lng);

        System.out.println(responseIn.extract().body().asString());
        Assert.assertTrue(response.extract().body().asString().equals(responseIn.extract().body().asString()),"Different response in same bound");
    }
}

