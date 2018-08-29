package com.yuliamz.logic;

import javafx.collections.ObservableList;

import java.util.ArrayList;

public class OperationCongruenceLinear {
    private Congruence_linear congruence;
    private ArrayList<Integer> listXi;
    private ArrayList<Float> listUi;
    private int Xi;
    private float Ui;


    /**
     * constructor de la clase
     *
     * @param congruence_linear
     */
    public OperationCongruenceLinear(Congruence_linear congruence_linear) {
        this.congruence = congruence_linear;
        this.Xi = congruence_linear.getXo();
        listUi = new ArrayList<>();
        listXi = new ArrayList<>();
    }

    /**
     * este metodo permite calcular un Xi seg�n los parametros de entrada, a,b y m, este valor se guarda en un vector
     */
    private void calculateModuloXi() {
        this.Xi = (congruence.getA() * this.Xi + congruence.b) % congruence.getM();
        listXi.add(this.Xi);
    }

    /**
     * este metodo permite calcular los Ui seg�n un datos Xi y el valor de m - 1, este valor se guarda en un vector
     */
    private void calculateUi(ObservableList<Float> floats) {
        this.Ui = (float) (this.Xi) / (congruence.getM() - 1);
        floats.add(Ui);
        listUi.add(Ui);
    }

    /**
     * este metodo permite generar tantas iteraciones  de los metos de calcular Xi y Ui como deseee el usuario
     */
    public void iteration(ObservableList<Float> floats) {
        for (int i = 0; i < congruence.getIteration(); i++) {
            calculateModuloXi();
            calculateUi(floats);
        }
    }


    /**
     * set y gets
     *
     * @return
     */
    public Congruence_linear getCongruence() {
        return congruence;
    }

    public void setCongruence(Congruence_linear congruence) {
        this.congruence = congruence;
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

    public double getUi() {
        return Ui;
    }

    public void setUi(Float ui) {
        Ui = ui;
    }


}
