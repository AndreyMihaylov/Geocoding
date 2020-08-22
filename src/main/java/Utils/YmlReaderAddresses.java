package Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static Utils.CommonUtils.addComma;

public class YmlReaderAddresses extends YmlReader {

    private HashMap<?, ?> addresses;
    private HashMap<String, ?> address;

    private String number;
    private String street;
    private String city;
    private String county;
    private String state;
    private String country;


    public YmlReaderAddresses(TypeOfAddress typeOfAddress, AddressesEnum addressesEnum) {
        if (typeOfAddress.equals(TypeOfAddress.LONG)) {
            this.addresses = getData();
            this.address = getDataByAddress(addressesEnum);
            this.number = getNumberFromFile();
            this.street =getStreetFromFile();
            this.city = getCityFromFile();
            this.county = getCountyFromFile();
            this.state = getStateFromFile();
            this.country = getCountryFromFile();
        } else if (typeOfAddress.equals(TypeOfAddress.SHORT)) {
            this.addresses = getData();
            this.address = getDataByAddress(addressesEnum);
            this.number = getNumberFromFile();
            this.street =getStreetFromFile();
            this.city = getCityFromFile();
            this.county = getCountyFromFile();
            this.state = getStateFromFile();
            this.country = getCountryFromFile();
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


    public HashMap<?, ?> getData() {
        return super.getData("addresses.yml");
    }

    private HashMap<String, ?> getDataByAddress(AddressesEnum addressesEnum) {
        return (HashMap<String, HashMap<String,String>>) addresses.get(addressesEnum.toString().toLowerCase());
    }

    private String getNumberFromFile() {
        HashMap<String,String> number = (HashMap<String,String>) address.get("number");
        return number.get("long_name");
    }

    private String getStreetFromFile() {
        HashMap<String,String> street = (HashMap<String,String>) address.get("street");
        return addComma(street.get("long_name"));
    }

    private String getCityFromFile() {
        HashMap<String,String> city = (HashMap<String,String>) address.get("city");
        return addComma(city.get("long_name"));
    }

    private String getCountyFromFile() {
        HashMap<String,String> county = (HashMap<String,String>) address.get("county");
        return addComma(county.get("long_name"));
    }

    private String getStateFromFile() {
        HashMap<String,String> state = (HashMap<String,String>) address.get("state");
        return addComma(state.get("long_name"));
    }

    private String getCountryFromFile() {
        HashMap<String,String> country = (HashMap<String,String>) address.get("country");
        return country.get("long_name");
    }



//    private String getNumberFromFile() {
//        return (String) address.get("number");
//    }
//
//    private String getStreetFromFile() {
//        return (String) address.get("street");
//    }
//
//    private String getCityFromFile() {
//        return (String) address.get("city");
//    }
//
//    private String getCountyFromFile() {
//        return (String) address.get("county");
//    }
//
//    private String getStateFromFile() {
//        return (String) address.get("state");
//    }
//
//    private String getCountryFromFile() {
//        return (String) address.get("country");
//    }

    public static ArrayList<String> paramsOfAddressesToList(YmlReaderAddresses obj){

        ArrayList<String> addresses = new ArrayList<>();
        addresses.add(obj.getNumber());
        addresses.add(obj.getStreet());
        addresses.add(obj.getCity());
        addresses.add(obj.getCounty());
        addresses.add(obj.getState());
        addresses.add(obj.getCountry());
        return addresses;
    }

    // take from addresses.yml. Add to enum if created new address in the file
    public enum AddressesEnum{
        ADDRESS1
    }

    public enum TypeOfAddress{
        LONG, SHORT
    }

}
