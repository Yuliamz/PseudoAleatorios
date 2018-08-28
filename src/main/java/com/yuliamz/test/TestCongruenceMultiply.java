package com.yuliamz.test;

import com.yuliamz.logic.Congruence_Multiply;
import com.yuliamz.logic.OperationCongruenceMultiply;
import javafx.collections.FXCollections;

public class TestCongruenceMultiply {
    public static void main(String[] args) {
        OperationCongruenceMultiply multiply = new OperationCongruenceMultiply(new Congruence_Multiply(19, 32, 5, 10));
        multiply.iteration(FXCollections.observableArrayList());
        System.out.println("Xi+1 : " + multiply.getListXi() + " Ui: " + multiply.getListUi());
    }

}
