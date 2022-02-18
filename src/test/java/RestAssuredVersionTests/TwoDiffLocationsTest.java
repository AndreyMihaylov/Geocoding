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

public class TwoDiffLocationsTest extends BaseTest {

    ApiActions apiActions;

    @Description("Send 2 request with different addresses and make sure responses are different")
    @Test
    public void twoDiffLocation() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddress(address);

        AddressesObj addressesObj2 = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS2);
        String address2 = createString(paramsOfAddressesToList(addressesObj2));

        apiActions = new ApiActions();
        ValidatableResponse response2 = apiActions.getDataByAddress(address2);
        Assert.assertFalse(response.extract().body().asString().equals(response2.extract().body().asString()),"Same response with different addresses");

    }
}
