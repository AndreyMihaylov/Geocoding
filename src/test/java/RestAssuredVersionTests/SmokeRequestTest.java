package RestAssuredVersionTests;

import RestAssuredVersion.ApiActions;
import org.testng.annotations.Test;

public class SmokeRequestTest {

ApiActions apiActions;

    @Test
    public void smokeRequest(){
        apiActions = new ApiActions();
        System.out.println(apiActions.getDataByAddress("1600+Amphitheatre+Parkway,+Mountain+View,+CA"));
        System.out.println(apiActions.getDataByCoordinate("37.09024,","95.712891"));
    }
}
