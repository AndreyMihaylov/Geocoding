package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.AddressesObj.AddressesEnum;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import static Utils.CommonUtils.createString;
import static Utils.AddressesObj.paramsOfAddressesToList;

public class SmokeRequestTest extends BaseTest {

    ApiActions apiActions;

    @Description("Smoke request with short address")
    @Test
    public void smokeRequestByAddressShort() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddress(address);
        Assert.assertTrue(response.extract().statusCode()==200,"Problem with short address");
    }

    @Description("Smoke request with long address")

    @Test
    public void smokeRequestByAddressLong() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        ValidatableResponse response = apiActions.getDataByAddress(address);
        Assert.assertTrue(response.extract().statusCode()==200,"Problem with long address");

    }

    @Description("Smoke request with location")
    @Test
    public void smokeRequestByLocation() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();

        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinates(lat,lng);
        Assert.assertTrue(response.extract().statusCode()==200,"Problem with location of address");

    }
}
