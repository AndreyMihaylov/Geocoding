package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;

public class DataVerification {
    SoftAssert softAssert =new SoftAssert();
    ApiActions apiActions;

    @Test
    public void verifyMainComponentsLocation() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();

        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinate(lat,lng);
        int size = response.extract().body().path("results[0].size()");
        for(int i=0; i<size;i++){
            HashMap<String,?> fields = response.extract().body().path("results["+i+"]");
            softAssert.assertTrue(apiActions.verifyMainComponentsLocation(fields),"Result #"+i+" doesn't contain main field(s)");

        }

        softAssert.assertAll();
    }

    @Test
    public void verifyBoundsComponentLocation() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();

        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinate(lat,lng);
        int size = response.extract().body().path("results[0].size()");
        for(int i=0; i<size;i++){
            HashMap<String,?> fields = response.extract().body().path("results["+i+"]");
            softAssert.assertTrue(apiActions.verifyBoundComponentLocation(fields),"Result #"+i+" doesn't contain main field(s)");

        }

        softAssert.assertAll();
    }
}
