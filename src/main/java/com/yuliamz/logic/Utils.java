package com.yuliamz.logic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static boolean isValidSeed(String seed) {
        try {
            if (seed.isEmpty()) return false;
            int seedSize = new BigInteger(seed).toString().length();
            return seedSize >= 4 && seedSize % 2 == 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidIterationsNumber(String iterate) {
        try {
            if (iterate.isEmpty()) return false;
            int iterations = Integer.parseInt(iterate);
            return iterations > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static List<String> convertFloatToString(List<Float> floats) {
        List<String> strings = new ArrayList<>();
        floats.forEach(e -> strings.add(String.valueOf(e)));
        return strings;
    }
}
