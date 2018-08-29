package com.yuliamz.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean isValidSeed(String seed) {
            if (!isNumber(seed)) return false;
            int seedSize = new BigInteger(seed).toString().length();
            return seedSize >= 4 && seedSize % 2 == 0;
    }

    public static boolean isValidIterationsNumber(String iterate) {
            if (!isNumber(iterate)) return false;
            return Integer.parseInt(iterate)>0;
    }

    public static List<String> convertFloatToString(List<Float> floats) {
        List<String> strings = new ArrayList<>();
        floats.forEach(e -> strings.add(String.valueOf(e)));
        return strings;
    }
    
    public static boolean isNumber(String num){
        if (num==null) return false;
        if (num.isEmpty()) return false;
        return num.chars().allMatch(Character::isDigit);
    }
}
