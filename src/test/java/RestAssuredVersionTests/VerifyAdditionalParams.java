package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import io.restassured.response.ValidatableResponse;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static Utils.AddressesObj.paramsOfAddressesToList;
import static Utils.CommonUtils.createString;
import static Utils.CommonUtils.getRandomString;

public class VerifyAdditionalParams {

    ApiActions apiActions;
    ApiActions.Language[] languages = ApiActions.Language.values();
    SoftAssert softAssert = new SoftAssert();

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

    @Test
    public void smokeRequestByLocationLang() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();
        List<String> languagesList = Arrays.stream(languages).map(Enum::toString).collect(Collectors.toList());
        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();

        languagesList.forEach(l -> {
            ValidatableResponse response = apiActions.getDataByCoordinateLang(lat, lng, l);
            softAssert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address with language");
        });

        softAssert.assertAll();
    }

    @Test
    public void smokeRequestByLocationLangNull() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();
        List<String> languagesList = Arrays.stream(languages).map(Enum::toString).collect(Collectors.toList());
        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();

        ValidatableResponse response = apiActions.getDataByCoordinateLang(lat, lng, null);
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address with wrong language");

    }

    @Test
    public void smokeRequestByLocationLanWrong() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.LONG, AddressesObj.AddressesEnum.ADDRESS1);
        apiActions = new ApiActions();
        String lat = addressesObj.getLat();
        String lng = addressesObj.getLng();
        ValidatableResponse response = apiActions.getDataByCoordinateLang(lat, lng, getRandomString(5));
        Assert.assertTrue(response.extract().statusCode() == 200, "Problem with location of address with 'null' language");

    }
}
