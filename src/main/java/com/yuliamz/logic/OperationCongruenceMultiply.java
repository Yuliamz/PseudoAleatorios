package com.yuliamz.logic;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OperationCongruenceMultiply {
    private Congruence_Multiply multiply;
    private ArrayList<Integer> listXi;
    private ArrayList<Float> listUi;
    private int Xi;
    private float Ui;

    public OperationCongruenceMultiply(Congruence_Multiply multiply) {
        listUi = new ArrayList<>();
        listXi = new ArrayList<>();
        this.multiply = multiply;
        this.Xi = multiply.getXo();
    }


    /**
     * este metodo permite calcular un Xi seg�n los parametros de entrada, a,b y m, este valor se guarda en un vector
     */
    private void calculateModuloXi() {
        this.Xi = (multiply.getA() * this.Xi) % multiply.getM();
        listXi.add(this.Xi);
    }

    /**
     * este metodo permite calcular los Ui seg�n un datos Xi y el valor de m - 1, este valor se guarda en un vector
     */
    private void calculateUi(ObservableList<Float> floats) {
        this.Ui = (float) (this.Xi) / (multiply.getM() - 1);
        floats.add(Ui);
        listUi.add(Ui);
    }

    /**
     * este metodo permite generar tantas iteraciones  de los metos de calcular Xi y Ui como deseee el usuario
     */
    public void iteration(ObservableList<Float> floats) {
        for (int i = 0; i < multiply.getIteration(); i++) {
            calculateModuloXi();
            calculateUi(floats);
        }
    }


    public Congruence_Multiply getMultiply() {
        return multiply;
    }


    public void setMultiply(Congruence_Multiply multiply) {
        this.multiply = multiply;
    }


    public ArrayList<Integer> getListXi() {
        return listXi;
    }


    public void setListXi(ArrayList<Integer> listXi) {
        this.listXi = listXi;
    }


    public ArrayList<Float> getListUi() {
        return listUi;
    }


    public void setListUi(ArrayList<Float> listUi) {
        this.listUi = listUi;
    }


    public int getXi() {
        return Xi;
    }


    public void setXi(int xi) {
        Xi = xi;
    }


    public float getUi() {
        return Ui;
    }


    public void setUi(float ui) {
        Ui = ui;
    }


}
