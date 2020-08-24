package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;
import static Utils.CommonUtils.getRandomString;

public class KeyVerification {

    ApiActions apiActions;

    @Test
    public void negativeRequestWitoutKey() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddressWithKey(address,null);
        Assert.assertTrue(response.extract().body().path("error_message").toString().contains("You must use an API key to authenticate"));

    }

    @Test
    public void negativeRequestWitWrongKey() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddressWithKey(address, getRandomString(10));
        Assert.assertTrue(response.extract().body().path("error_message").equals("The provided API key is invalid."));
    }
}
