package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashMap;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;

public class AddressLocationAddressVerificationTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();
    ApiActions apiActions = new ApiActions();


    @Test
    @Description("Send request with address {AddressesObj.AddressesEnum.ADDRESS1}. Grab location from response. Send request with coordinates")
    public void addressLocationAddressVerificationTest() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));


        ValidatableResponse responseByAddress = apiActions.getDataByAddress(address);
        addressLocationAddressVerification(responseByAddress, address);
        softAssert.assertAll();
    }

    @Step("Call 'addressLocationAddressVerification'")
    public void addressLocationAddressVerification(ValidatableResponse response, String address) {

        HashMap<String, Double> coordinates = apiActions.getCoordinates(response);
        String lat = String.valueOf(coordinates.get("lat"));
        String lng = String.valueOf(coordinates.get("lng"));
        ValidatableResponse responseByLocation = apiActions.getDataByCoordinates(lat, lng);

        String placeIdAddress = apiActions.getPlaceIdFirst(response);
        ArrayList<String> placeIdLocation = apiActions.getPlaceIds(responseByLocation);

        softAssert.assertTrue(placeIdLocation.stream().anyMatch(p -> p.equals(placeIdAddress)), "Address: " + address + " -  doesn't present in location: " + lat + lng);
    }
}
