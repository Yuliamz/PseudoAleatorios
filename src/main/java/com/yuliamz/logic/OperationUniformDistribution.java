package com.yuliamz.logic;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.DoubleStream;

public class OperationUniformDistribution {
    private int limitA;
    private int limitB;
    private int quantityNumber;
ArrayList<Double> listNumber;
ArrayList<Double> listNumberPseudo;


    public OperationUniformDistribution(int limitA, int limitB, int quantityNumber) {
        this.limitA = limitA;
        this.limitB = limitB;
        this.quantityNumber = quantityNumber;
        listNumber = new ArrayList<>();
        listNumberPseudo = new ArrayList<>();
    }

    public void generatePseudo(){
        for (Double aDouble: this.listNumber
             ) {
            this.listNumberPseudo.add(limitA + ((limitB-limitA)*aDouble));
        }
    }

    /**
     * metodo que permite generar numero pseudoaleatorios de manera uniforme
     */
    public void generateNumber(){
        for (int i = 0; i < quantityNumber; i++) {
            double random = Math.random();
            listNumber.add(random);
        }
    }

    public int getQuantityNumber() {
        return quantityNumber;
    }

    public void setQuantityNumber(int quantityNumber) {
        this.quantityNumber = quantityNumber;
    }

    public ArrayList<Double> getListNumber() {
        return listNumber;
    }

    public void setListNumber(ArrayList<Double> listNumber) {
        this.listNumber = listNumber;
    }

    public ArrayList<Double> getListNumberPseudo() {
        return listNumberPseudo;
    }

    public void setListNumberPseudo(ArrayList<Double> listNumberPseudo) {
        this.listNumberPseudo = listNumberPseudo;
    }

    public int getLimitA() {
        return limitA;
    }

    public void setLimitA(int limitA) {
        this.limitA = limitA;
    }

    public int getLimitB() {
        return limitB;
    }

    public void setLimitB(int limitB) {
        this.limitB = limitB;
    }


}
