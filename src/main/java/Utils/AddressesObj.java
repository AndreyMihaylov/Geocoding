package Utils;

import java.util.ArrayList;
import java.util.HashMap;

import static Utils.CommonUtils.addComma;

@SuppressWarnings("unchecked")
public class AddressesObj extends YmlReader {

    private HashMap<?, ?> addresses;
    private HashMap<String, ?> address;

    private String number;
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;
    private String zip;
    private String lat;
    private String lng;
    private ArrayList<ArrayList<String>> combinationList = null;

    public AddressesObj(TypeOfAddress typeOfAddress, AddressesEnum addressesEnum) {
        this.addresses = getData();
        this.address = getDataByAddress(addressesEnum);
        this.zip = getZipFromFile();

        if (typeOfAddress.equals(TypeOfAddress.LONG)) {
            this.number = getNumberFromFile();
            this.street = getStreetFromFile();
            this.city = getCityFromFile();
            this.county = getCountyFromFile();
            this.state = getStateFromFile();
            this.country = getCountryFromFile();
            this.lat = getLatFromFile();
            this.lng = getLngFromFile();
        } else if (typeOfAddress.equals(TypeOfAddress.SHORT)) {
            this.number = getNumberFromFileShort();
            this.street = getStreetFromFileShort();
            this.city = getCityFromFileShort();
            this.county = getCountyFromFileShort();
            this.state = getStateFromFileShort();
            this.country = getCountryFromFileShort();
        }
    }

    public String getNumber() {
        return number;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }


    public HashMap<?, ?> getData() {
        return super.getData("addresses.yml");
    }

    private HashMap<String, ?> getDataByAddress(AddressesEnum addressesEnum) {
        return (HashMap<String, HashMap<String, String>>) addresses.get(addressesEnum.getValue());
    }

    private String getNumberFromFile() {
        HashMap<String, String> number = (HashMap<String, String>) address.get("number");
        return number.get("long_name");
    }

    private String getStreetFromFile() {
        HashMap<String, String> street = (HashMap<String, String>) address.get("street");
        return addComma(street.get("long_name"));
    }

    private String getCityFromFile() {
        HashMap<String, String> city = (HashMap<String, String>) address.get("city");
        return addComma(city.get("long_name"));
    }

    private String getCountyFromFile() {
        HashMap<String, String> county = (HashMap<String, String>) address.get("county");
        return addComma(county.get("long_name"));
    }

    private String getStateFromFile() {
        HashMap<String, String> state = (HashMap<String, String>) address.get("state");
        return addComma(state.get("long_name"));
    }

    private String getCountryFromFile() {
        HashMap<String, String> country = (HashMap<String, String>) address.get("country");
        return country.get("long_name");
    }

    private String getZipFromFile() {
        HashMap<String, String> zip = (HashMap<String, String>) address.get("zip");
        return zip.get("long_name");
    }

    private String getLatFromFile() {
        HashMap<String, String> zip = (HashMap<String, String>) address.get("location");
        return zip.get("lat");
    }

    private String getLngFromFile() {
        HashMap<String, String> zip = (HashMap<String, String>) address.get("location");
        return zip.get("lng");
    }

    private String getNumberFromFileShort() {
        HashMap<String, String> number = (HashMap<String, String>) address.get("number");
        return number.get("short_name");
    }

    private String getStreetFromFileShort() {
        HashMap<String, String> street = (HashMap<String, String>) address.get("street");
        return addComma(street.get("short_name"));
    }

    private String getCityFromFileShort() {
        HashMap<String, String> city = (HashMap<String, String>) address.get("city");
        return addComma(city.get("short_name"));
    }

    private String getCountyFromFileShort() {
        HashMap<String, String> county = (HashMap<String, String>) address.get("county");
        return addComma(county.get("short_name"));
    }

    private String getStateFromFileShort() {
        HashMap<String, String> state = (HashMap<String, String>) address.get("state");
        return addComma(state.get("short_name"));
    }

    private String getCountryFromFileShort() {
        HashMap<String, String> country = (HashMap<String, String>) address.get("country");
        return country.get("short_name");
    }

    public static ArrayList<String> paramsOfAddressesToList(AddressesObj obj) {

        ArrayList<String> addresses = new ArrayList<>();
        addresses.add(obj.getNumber());
        addresses.add(obj.getStreet());
        addresses.add(obj.getCity());
        addresses.add(obj.getCounty());
        addresses.add(obj.getState());
        addresses.add(obj.getCountry());
        addresses.add(obj.getZip());
        return addresses;
    }

    public ArrayList<ArrayList<String>> getCombinationList(ArrayList<String> list) {
        combinationList = new ArrayList<>();
        combinationOfNullParams(list, 0);
        return combinationList;
    }

    private ArrayList<String> combinationOfNullParams(ArrayList<String> list, int j) {
        ArrayList<String> list2 = null;
        for (int i = j; i < 6; i++) {
            list2 = new ArrayList<>(list);
            list2.set(i, null);
            combinationList.add(list2);
            combinationOfNullParams(list2, j + 1);
            j++;
        }
        return list2;
    }


    // take from addresses.yml. Add to enum if created new address in the file
    public enum AddressesEnum {
        ADDRESS1("address1"), ADDRESS2("address2");

        private final String value;

        AddressesEnum(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public enum TypeOfAddress {
        LONG, SHORT
    }

}
