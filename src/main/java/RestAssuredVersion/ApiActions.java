package RestAssuredVersion;

import io.restassured.response.ValidatableResponse;

import java.util.HashMap;
import java.util.Map;

public class ApiActions extends HTTPMethods {


    public ValidatableResponse getDataByAddress(String address) {

        Map<String, String> queries = new HashMap<>();
        queries.put("address", address);
        ValidatableResponse response = get(queries);

        return response;
    }

    public ValidatableResponse getDataByCoordinate(String lat, String lng) {
        if (lat != null
                && lng != null &&!lat.isEmpty()
                && !lng.isEmpty()) {
            if (lat.toCharArray()[lat.length() - 1] != ',') {
                lat = new StringBuilder(lat).append(',').toString();

            }
        }
        Map<String, String> queries = new HashMap<>();
        queries.put("latlng", lat + lng);
        ValidatableResponse response = get(queries);

        return response;
    }
//    public getDataBy(){}
//    public getDataBy(){}
//    public getDataBy(){}
//    public getDataBy(){}
//    public getDataBy(){}
}
