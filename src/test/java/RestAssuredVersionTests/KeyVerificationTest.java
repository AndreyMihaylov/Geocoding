package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;
import static Utils.CommonUtils.getRandomString;

public class KeyVerificationTest extends BaseTest {

    ApiActions apiActions;

    @Description("Send request with key property is'null'")
    @Test
    public void negativeRequestWitoutKey() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddressWithKey(address, null);
        Assert.assertTrue(response.extract().body().path("error_message").toString().contains("You must use an API key to authenticate"));

    }

    @Description("Send request with key property is wrong")
    @Test
    public void negativeRequestWitWrongKey() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddressWithKey(address, getRandomString(10));
        Assert.assertTrue(response.extract().body().path("error_message").equals("The provided API key is invalid."));
    }
}
