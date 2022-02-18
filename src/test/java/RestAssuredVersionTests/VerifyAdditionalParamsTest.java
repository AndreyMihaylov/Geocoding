package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.BaseTest;
import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;
import static Utils.CommonUtils.getRandomString;

public class VerifyAdditionalParamsTest extends BaseTest {

    ApiActions apiActions;
    ApiActions.Language[] languages = ApiActions.Language.values();
    SoftAssert softAssert = new SoftAssert();

    @Description("Smoke request with long address and language (all possible)")
    @Test
    public void smokeRequestByAddressLongLang() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));
        apiActions = new ApiActions();
        List<String> languagesList = Arrays.stream(languages).map(Enum::toString).collect(Collectors.toList());
        languagesList.forEach(l -> {
            ValidatableResponse response = apiActions.getDataByAddressLang(address, l);
            softAssert.assertTrue(response.extract().statusCode() == 200, "Problem with long address with language");
        });

        softAssert.assertAll();
    }

    @Description("Smoke request with location and language (all possible)")
    @Test
    public void smokeRequestByLocationLang() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();
        List<String> languagesList = Arrays.stream(languages).map(Enum::name).collect(Collectors.toList());
        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();

        languagesList.forEach(l -> {
            ValidatableResponse response = apiActions.getDataByCoordinatesLang(lat, lng, l);
            softAssert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address with language");
        });

        softAssert.assertAll();
    }

    @Description("Smoke request with location and language is null")
    @Test
    public void smokeRequestByLocationLangNull() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();
        List<String> languagesList = Arrays.stream(languages).map(Enum::name).collect(Collectors.toList());
        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();

        ValidatableResponse response = apiActions.getDataByCoordinatesLang(lat, lng, null);
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address with wrong language");

    }

    @Description("Smoke request with location and language is wrong")
    @Test
    public void smokeRequestByLocationLanWrong() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();
        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinatesLang(lat, lng, getRandomString(5));
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address with 'null' language");

    }
}
