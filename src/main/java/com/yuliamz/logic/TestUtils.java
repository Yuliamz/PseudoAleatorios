package com.yuliamz.logic;

import java.util.List;

/**
 * @author Yuliamz
 */
public class TestUtils {

    public static boolean testMeans(List<Double> list, int acceptationGrades) {
        return new MeanTest(list, acceptationGrades).isValid();
    }

    public static boolean testVarianes(List<Double> list, int acceptationGrades) {
        return new VarianceTest(list, acceptationGrades).isValid();
    }

    public static boolean testKS(List<Double> list, int acceptationGrades) {
        KolmogorovSmirnov ks = new KolmogorovSmirnov(acceptationGrades, list);
        ks.calculateFinalValue();
        ks.calculateFrequency();
        ks.calculateFrequencyAcumulated();
        ks.calculatedGetProbability();
        ks.calculatedFrequencyExpected();
        ks.calculatedProbabilityExpected();
        ks.calculatedDiference();
        ks.calculatedDMAX();
        ks.calculatedDMAXP();
        return ks.isPseudo();
    }

    public static boolean passAllTests(List<Double> list, int acceptationGrades) {
        return testMeans(list, acceptationGrades) && testVarianes(list, acceptationGrades) && testKS(list, acceptationGrades);
    }
}
