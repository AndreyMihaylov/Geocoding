package Utils;

import java.security.SecureRandom;
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

    public static String getRandomString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

}
