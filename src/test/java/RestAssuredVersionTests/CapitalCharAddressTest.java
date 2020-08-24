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

public class CapitalCharAddressTest extends BaseTest {

    ApiActions apiActions;

    @Description("Send request with upper case address with short format")
    @Test
    public void smokeRequestByAddressShortCapital() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj)).toUpperCase();

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddress(address);
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with short address");
    }

    @Description("Send request with upper case address with long format")
    @Test
    public void smokeRequestByAddressLongCapital() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj)).toUpperCase();

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddress(address);
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with long address");

    }
}
