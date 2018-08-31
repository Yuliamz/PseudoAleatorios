package com.yuliamz.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Utils {
    
    public static final NormalDistribution NORMAL_DISTRIBUTION = new NormalDistribution();

    public static boolean isValidSeed(String seed) {
            if (!isNumber(seed)) return false;
            int seedSize = new BigInteger(seed).toString().length();
            return seedSize >= 4 && seedSize % 2 == 0;
    }

    public static boolean isValidIterationsNumber(String iterate) {
            if (!isNumber(iterate)) return false;
            return Integer.parseInt(iterate)>0;
    }

    public static List<String> convertFloatToString(List<Float> nums) {
        List<String> strings = new ArrayList<>();
        nums.forEach(e -> strings.add(String.valueOf(e)));
        return strings;
    }
    
    public static List<BigInteger> convertStringToBigInteger(List<String> nums) {
        List<BigInteger> integers = new ArrayList<>();
        nums.forEach(e -> integers.add(new BigInteger(e)));
        return integers;
    }
    
    public static List<Float> convertStringToFloat(List<String> nums) {
        List<Float> floats = new ArrayList<>();
        nums.forEach(e -> floats.add(Float.valueOf(e)));
        return floats;
    }
    
    
    public static boolean isNumber(String num){
        if (num==null) return false;
        if (num.isEmpty()) return false;
        return num.chars().allMatch(Character::isDigit);
    }
}
