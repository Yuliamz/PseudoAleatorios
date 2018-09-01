package com.yuliamz.logic;

import javafx.application.Platform;
import javafx.collections.ObservableList;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yuliamz
 */
public class MiddleSquares {

    private BigInteger seed;
    private int iterations;
    private int seedSize;
    private ArrayList<BigInteger> extracts;
    private final int maxDigits;

    public MiddleSquares(BigInteger seed, int iterations) {
        this.seed = seed;
        this.iterations = iterations;
        this.seedSize = seed.toString().length();
        this.extracts = new ArrayList<>();
        this.maxDigits = calcMaxDigits();
    }

    public MiddleSquares(BigInteger seed) {
        this.seed = seed;
        this.iterations = Integer.MAX_VALUE;
        this.seedSize = seed.toString().length();
        this.extracts = new ArrayList<>();
        this.maxDigits = calcMaxDigits();
    }

    private BigInteger extract(BigInteger number) {
        StringBuilder num = new StringBuilder(number.toString());
        while (num.length() < maxDigits) num.insert(0, "0");
        int start = (num.length() - seedSize) / 2;
        return new BigInteger(num.substring(start, start + seedSize));
    }

    public ArrayList<BigInteger> generate() {
        BigInteger result = extract(seed.pow(2));
        extracts.add(new BigInteger(result.toString()));
        for (int i = 0; i < iterations - 1; i++) {
            result = extract(result.pow(2));
            if (result.equals(BigInteger.ZERO)) {
                extracts.add(BigInteger.ZERO);
                return extracts;
            }
            if (extracts.contains(result)) return extracts;
            extracts.add(new BigInteger(result.toString()));
        }
        return extracts;
    }

    private String completeNumber(BigInteger value) {
        StringBuilder builder = new StringBuilder(value.toString());
        while (builder.length() < seedSize) builder.insert(0, 0);
        return builder.toString();
    }

    public void generateIn(ObservableList<Double> list) {
        extracts.clear();
        BigInteger pow = seed.pow(2);
        BigInteger extract = extract(pow);
        String add = pow.toString();
        Platform.runLater(() -> list.add(Double.parseDouble("0."+add)));
        for (int i = 0; i < iterations - 1; i++) {
            extract = extract(pow=extract.pow(2));
            if (extracts.contains(extract)) break;
            extracts.add(extract);
            String s = pow.toString();
            Platform.runLater(() -> list.add(Double.parseDouble("0."+s)));
        }
    }

    private List<String> getFullExtracts() {
        ArrayList<String> al = new ArrayList<>();
        extracts.forEach(e -> al.add(completeNumber(e)));
        return al;
    }

    private int calcMaxDigits() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < seedSize; i++) builder.append(9);
        return new BigInteger(builder.toString()).pow(2).toString().length();
    }

    public void printNumbers() {
        extracts.forEach(e -> System.out.println(e.toString()));
    }

    public void printFullExtracts() {
        getFullExtracts().forEach(System.out::println);
    }

    public BigInteger getSeed() {
        return seed;
    }

    public void setSeed(BigInteger seed) {
        this.seed = seed;
    }

    public int getIterations() {
        return iterations;
    }

    public void setIterations(int iterations) {
        this.iterations = iterations;
    }

    public int getSeedSize() {
        return seedSize;
    }

    public void setSeedSize(int seedSize) {
        this.seedSize = seedSize;
    }

    public ArrayList<BigInteger> getExtracts() {
        return extracts;
    }

    public void setExtracts(ArrayList<BigInteger> extracts) {
        this.extracts = extracts;
    }
    
}
