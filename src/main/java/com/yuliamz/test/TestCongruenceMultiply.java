package com.yuliamz.test;

import com.yuliamz.logic.CongruenceMultiply;
import com.yuliamz.logic.OperationCongruenceMultiply;
import javafx.collections.FXCollections;

class TestCongruenceMultiply {
    public static void main(String[] args) {
        OperationCongruenceMultiply multiply = new OperationCongruenceMultiply(new CongruenceMultiply(19, 32, 5, 10));
        multiply.iteration(FXCollections.observableArrayList());
        System.out.println("Xi+1 : " + multiply.getListXi() + " Ui: " + multiply.getListUi());
    }

}
