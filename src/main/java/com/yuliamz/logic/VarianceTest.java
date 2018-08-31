package com.yuliamz.logic;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Yuliamz
 */
public class VarianceTest {

    private final int acceptationGrade;
    private final int errorGrade;
    private final double average;
    private final int numbersQuantity;
    private final double variance;
    private final double aMedios;
    private final double unoAMedios;
    private final double LS;
    private final double LI;
    private final List<Float> list;

    public VarianceTest(ArrayList<Float> numbers, int acceptationGrade) {
        this.list = numbers;
        DescriptiveStatistics ds = new DescriptiveStatistics(Utils.convertFloatToDouble(list));
        this.acceptationGrade = acceptationGrade;
        this.errorGrade = 100 - acceptationGrade;
        this.average = ds.getMean();
        this.numbersQuantity = list.size();
        this.variance = ds.getPopulationVariance();
        this.aMedios = (errorGrade / 100.0) / 2.0;
        this.unoAMedios = 1 - aMedios;
        this.LS = 0;
        this.LI = 0;


    }


}
