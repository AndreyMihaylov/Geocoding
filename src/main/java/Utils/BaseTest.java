package Utils;

import RestAssuredVersion.ApiActions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class BaseTest {

    protected SoftAssert softAssert = new SoftAssert();
    protected ApiActions apiActions = new ApiActions();

    @BeforeTest
    public void setUp(){

    }

    @AfterTest
    public void tearDown(){

    }
}
