package RestAssuredVersion;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import java.util.*;
import java.util.stream.Collectors;

public class ApiActions extends HTTPMethods {

    @Step("Send request to get data by address: {address}")
    public ValidatableResponse getDataByAddress(String address) {
        Map<String, String> queries = new HashMap<>();
        queries.put("address", address);

        return get(queries);
    }

    @Step("Send request to get data by address, need to provide a key : {key}")
    public ValidatableResponse getDataByAddressWithKey(String address,String key) {
        Map<String, String> queries = new HashMap<>();
        queries.put("address", address);
        queries.put("key", key);

        return get(queries);
    }

    @Step("Send request to get data by location 'lat': {lat} 'lng' : {lng}")
    public ValidatableResponse getDataByCoordinates(String lat, String lng) {
        if (lat != null
                && lng != null && !lat.isEmpty()
                && !lng.isEmpty()) {
            if (lat.toCharArray()[lat.length() - 1] != ',') {
                lat = lat + ',';

            }
        }
        Map<String, String> queries = new HashMap<>();
        queries.put("latlng", lat + lng);

        return get(queries);
    }

    @Step("Verify main components: {fields} are present in response ")
    public boolean verifyMainComponentsLocation(HashMap<String,?> fields){
        MainFields[] list = MainFields.values();
        return fields.keySet().containsAll(Arrays.stream(list).map(MainFields::getValue).collect(Collectors.toList()));
    }

    @SuppressWarnings("unchecked")
    @Step("Verify 'bound' field is present in response or not")
    public boolean verifyBoundComponentLocation(HashMap<String,?> fields){
        ArrayList<String> types = (ArrayList<String>) fields.get("types");
        HashMap<String,?> geometry = (HashMap<String, ?>) fields.get("geometry");
        boolean street = types.stream().anyMatch(e -> e.equals("street_address"));
        boolean contains = geometry.keySet().contains("bounds");

        return street != contains;

    }

    @Step("Send request to get data by address, by language: {lang}")
    public ValidatableResponse getDataByAddressLang(String address, String lang) {
        Map<String, String> queries = new HashMap<>();
        queries.put("address", address);
        queries.put("language", lang);

        return get(queries);
    }

    @Step("Send request to get data by coordinates({lat}, {lng}), by language: {lang}")
    public ValidatableResponse getDataByCoordinatesLang(String lat, String lng, String lang) {
        if (lat != null
                && lng != null && !lat.isEmpty()
                && !lng.isEmpty()) {
            if (lat.toCharArray()[lat.length() - 1] != ',') {
                lat = lat + ',';

            }
        }
        Map<String, String> queries = new HashMap<>();
        queries.put("latlng", lat + lng);
        queries.put("language", lang);

        return get(queries);
    }

    @Step("Get all place IDs from response")
    public ArrayList<String> getPlaceIds(ValidatableResponse response) {
        ArrayList<String> placeIds = new ArrayList<>();
        int size = response.extract().body().path("results[0].size()");
        for (int i = 0; i < size; i++) {
            String placeId = response.extract().body().path("results[" + i +"].place_id");
            placeIds.add(placeId);
        }
        return placeIds;
    }

    @Step("Get first place ID from response")
    public String getPlaceIdFirst(ValidatableResponse response) {
       if(getPlaceIds(response).size()==0){
           return "";
       }
        return getPlaceIds(response).get(0);
    }

    @Step("Get 4 corner coordinates from response based on 'viewport' field")
    public HashMap<String, Double> getBoundCoordinates(ValidatableResponse response) {
        HashMap<String, Double> boundCoordinates = new HashMap<>();
        boundCoordinates.put("lat1", response.extract().body().path("results.geometry.viewport.northeast.lat[0]"));
        boundCoordinates.put("lng1", response.extract().body().path("results.geometry.viewport.northeast.lng[0]"));
        boundCoordinates.put("lat2", response.extract().body().path("results.geometry.viewport.southwest.lat[0]"));
        boundCoordinates.put("lng2", response.extract().body().path("results.geometry.viewport.southwest.lng[0]"));

        return boundCoordinates;
    }

    @Step("Get coordinates of address")
    public HashMap<String, Double> getCoordinates(ValidatableResponse response) {
        HashMap<String, Double> coordinates = new HashMap<>();
        coordinates.put("lat", response.extract().body().path("results.geometry.location.lat[0]"));
        coordinates.put("lng", response.extract().body().path("results.geometry.location.lng[0]"));

        return coordinates;
    }

    enum MainFields{
        ADDRESS_COMPONENTS("address_components"), FORMATTED_ADDRESS("formatted_address"), GEOMETRY("geometry"), PLACE_ID("place_id"), TYPES("types");

        final private String value;

        MainFields(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public enum Language{
        af,ja,sq,kn,am,kk,ar,km, hy,ko,az,ky,eu,lo,be,lv,bn,lt, bs,mk,bg,ms,my,ml,ca,mr,zh,mn,pl,hr,pt,cs,da,nl,
        pa,ro,ru,sr,et,si,fa,sk,fi,sl,fil,es,fr,sw,gl,sv,ka,ta,de,te,el,th,gu,tr,iw,uk,hi,ur,hu,uz,is,vi,id,zu,it
        }
}
