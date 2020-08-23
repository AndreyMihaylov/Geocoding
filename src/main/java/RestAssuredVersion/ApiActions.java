package RestAssuredVersion;

import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
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
                && lng != null && !lat.isEmpty()
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

    public ArrayList<String> getPlaceIds(ValidatableResponse response) {
        ArrayList<String> placeIds = new ArrayList<>();
        int size = response.extract().body().path("results.size()");
        for (int i = 0; i < size; i++) {
            String placeId = response.extract().body().path("results[" + i +"].place_id");
            placeIds.add(placeId);
        }
        return placeIds;
    }

    public String getPlaceIdFirst(ValidatableResponse response) {
       if(getPlaceIds(response).size()==0){
           return "";
       }
        return getPlaceIds(response).get(0);
    }

    public HashMap<String, Double> getBoundCoordinates(ValidatableResponse response) {
        HashMap<String, Double> boundCoordinates = new HashMap<>();
        boundCoordinates.put("lat1", response.extract().body().path("results.geometry.viewport.northeast.lat[0]"));
        boundCoordinates.put("lng1", response.extract().body().path("results.geometry.viewport.northeast.lng[0]"));
        boundCoordinates.put("lat2", response.extract().body().path("results.geometry.viewport.southwest.lat[0]"));
        boundCoordinates.put("lng2", response.extract().body().path("results.geometry.viewport.southwest.lng[0]"));

        return boundCoordinates;
    }

    public HashMap<String, Double> getCoordinates(ValidatableResponse response) {
        HashMap<String, Double> coordinates = new HashMap<>();
        coordinates.put("lat", response.extract().body().path("results.geometry.location.lat[0]"));
        coordinates.put("lng", response.extract().body().path("results.geometry.location.lng[0]"));

        return coordinates;
    }
}
