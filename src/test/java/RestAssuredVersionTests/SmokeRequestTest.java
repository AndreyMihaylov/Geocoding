package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.AddressesObj;
import Utils.AddressesObj.AddressesEnum;
import org.testng.annotations.Test;

import static Utils.CommonUtils.createString;
import static Utils.AddressesObj.paramsOfAddressesToList;

public class SmokeRequestTest {

    ApiActions apiActions;

    @Test
    public void smokeRequest() {

        AddressesObj addressesObj = new AddressesObj(AddressesObj.TypeOfAddress.SHORT, AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(addressesObj));

        apiActions = new ApiActions();
        System.out.println(apiActions.getDataByAddress(address));
//        System.out.println(apiActions.getDataByCoordinate("37.09024,","95.712891"));
    }
}
