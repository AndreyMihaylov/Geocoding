package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;

public class CombinationOfNullInAddressTest extends BaseTest {

    ApiActions apiActions;

    //    AddressLocationAddressVerificationTest ala;
    @Description("Create list of possible combination 'null' in address. Could be use for other tests")
    @Test
    public void combinationOfNullInAddress() {
//        ala = new AddressLocationAddressVerificationTest();
        apiActions = new ApiActions();
        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesObj.AddressesEnum.ADDRESS1);
        ArrayList<String> address = paramsOfAddressesToList(addressesObj);
        ArrayList<ArrayList<String>> combinationList = addressesObj.getCombinationList(address);

        SoftAssert softAssert = new SoftAssert();
        combinationList.forEach(ad -> {

            String address2 = createString(ad);
            ValidatableResponse response = apiActions.getDataByAddress(address2);
            softAssert.assertTrue(response.extract().statusCode() == 200, "Problem with address " + address2);
//            ala.addressLocationAddressVerification(response,address2);
        });
//        ala.softAssert.assertAll();
        softAssert.assertAll();
    }
}
