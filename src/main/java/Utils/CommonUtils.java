package Utils;

import java.util.ArrayList;


import java.util.Objects;
import java.util.stream.Collectors;

public class CommonUtils {

    public static String createString(ArrayList<String> list){
        return list.stream().filter(Objects::nonNull).collect(Collectors.joining("+"));
    }

    public static String addComma(String str){
        if(str.charAt(str.length()-1)!=','){
            return str+",";
        }
        return str;
    }

    public static String replaceSpaceToPlus(String str){
        return  str.replaceAll(" ","+");
    }



}
