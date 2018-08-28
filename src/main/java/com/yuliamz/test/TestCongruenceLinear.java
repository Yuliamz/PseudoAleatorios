package com.yuliamz.test;

import com.yuliamz.logic.Congruence_linear;
import com.yuliamz.logic.OperationCongruenceLinear;
import javafx.collections.FXCollections;

/**
 * main del programa, permite su ejecuci�n
 *
 * @author Walter
 */
public class TestCongruenceLinear {
    public static void main(String[] args) {
        OperationCongruenceLinear linear = new OperationCongruenceLinear(new Congruence_linear(5, 7, 8, 1, 15));
        linear.iteration(FXCollections.observableArrayList());
        System.out.println("Xi+1 : " + linear.getListXi() + " Ui: " + linear.getListUi());


    }
}
