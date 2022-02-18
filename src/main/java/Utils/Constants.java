package Utils;

import java.io.File;
import java.util.regex.Matcher;

public class Constants {

    public final static String KEY = "AIzaSyAH58w37HzgdAZiNy63akzvyIr6efeGReU";
    public final static String URL = "https://maps.googleapis.com/maps/api/geocode/json";
    static String absolutePath = System.getProperty("user.dir");
    static String pathToBookingId = absolutePath + "/src/main/resources/";

    public static String getPathToResouces(String fileName) {
        return (pathToBookingId + fileName).replaceAll("/", Matcher.quoteReplacement(File.separator));
    }
}
