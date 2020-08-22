package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import Utils.YmlReaderAddresses;
import Utils.YmlReaderAddresses.AddressesEnum;
import org.testng.annotations.Test;

import static Utils.CommonUtils.createString;
import static Utils.YmlReaderAddresses.paramsOfAddressesToList;

public class SmokeRequestTest {

ApiActions apiActions;

    @Test
    public void smokeRequest(){

        YmlReaderAddresses ymlReaderAddresses = new YmlReaderAddresses(YmlReaderAddresses.TypeOfAddress.LONG,AddressesEnum.ADDRESS1);
        String address = createString(paramsOfAddressesToList(ymlReaderAddresses));

        apiActions = new ApiActions();
        System.out.println(apiActions.getDataByAddress(address));
//        System.out.println(apiActions.getDataByCoordinate("37.09024,","95.712891"));
    }
}
