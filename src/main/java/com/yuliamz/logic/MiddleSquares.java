package com.yuliamz.logic;

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
    private ArrayList<BigInteger> numbers;
    private final int maxDigits;

    public MiddleSquares(BigInteger seed, int iterations) {
        this.seed = seed;
        this.iterations = iterations;
        this.seedSize = seed.toString().length();
        this.numbers = new ArrayList<>();
        this.maxDigits = calcMaxDigits();

    }

    public MiddleSquares(BigInteger seed) {
        this.seed = seed;
        this.iterations = Integer.MAX_VALUE;
        this.seedSize = seed.toString().length();
        this.numbers = new ArrayList<>();
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
        numbers.add(new BigInteger(result.toString()));
        for (int i = 0; i < iterations; i++) {
            result = extract(result.pow(2));
            if (result.equals(BigInteger.ZERO)) {
                numbers.add(BigInteger.ZERO);
                return numbers;
            }
            if (numbers.contains(result)) return numbers;
            numbers.add(new BigInteger(result.toString()));
        }
        return numbers;
    }

    private String completeNumber(BigInteger value) {
        StringBuilder builder = new StringBuilder(value.toString());
        while (builder.length() < seedSize) builder.insert(0, 0);
        return builder.toString();
    }

    public void generateIn(ObservableList<String> list) {
        BigInteger result = extract(seed.pow(2));
        list.add(completeNumber(result));
        for (int i = 0; i < iterations; i++) {
            result = extract(result.pow(2));
            if (result.equals(BigInteger.ZERO)) {
                list.add(completeNumber(BigInteger.ZERO));
                break;
            }
            if (list.contains(completeNumber(result))) break;
            list.add(completeNumber(result));
        }
    }

    private List<String> getFullNumbers() {
        ArrayList<String> al = new ArrayList<>();
        numbers.forEach(e -> al.add(completeNumber(e)));
        return al;
    }

    private int calcMaxDigits() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < seedSize; i++) builder.append(9);
        return new BigInteger(builder.toString()).pow(2).toString().length();
    }

    public void printNumbers() {
        numbers.forEach(e -> System.out.println(e.toString()));
    }

    public void printFullNumbers() {
        getFullNumbers().forEach(System.out::println);
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

    public ArrayList<BigInteger> getNumbers() {
        return numbers;
    }

    public void setNumbers(ArrayList<BigInteger> numbers) {
        this.numbers = numbers;
    }

}
