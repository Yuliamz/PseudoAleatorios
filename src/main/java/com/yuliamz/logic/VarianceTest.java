package com.yuliamz.logic;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.util.ArrayList;
import java.util.List;

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
    private final double chiSqaure1;
    private final double chiSqaure2;
    private final double LS;
    private final double LI;
    private final List<Float> list;

    public VarianceTest(ArrayList<Float> numbers, int acceptationGrade) {
        this.list = numbers;
        this.numbersQuantity = list.size();
        DescriptiveStatistics ds = new DescriptiveStatistics(Utils.convertFloatToDoubleArray(list));
        ChiSquaredDistribution chiSquaredDistribution = new ChiSquaredDistribution(this.numbersQuantity - 1);
        this.acceptationGrade = acceptationGrade;
        this.errorGrade = 100 - acceptationGrade;
        this.average = ds.getMean();
        this.variance = ds.getPopulationVariance();
        this.aMedios = (errorGrade / 100.0) / 2.0;
        this.unoAMedios = 1 - aMedios;
        this.chiSqaure1 = chiSquaredDistribution.inverseCumulativeProbability(unoAMedios);
        this.chiSqaure2 = chiSquaredDistribution.inverseCumulativeProbability(aMedios);
        this.LS = chiSqaure2 / (12 * (numbersQuantity - 1));
        this.LI = chiSqaure1 / (12 * (numbersQuantity - 1));
    }

    public boolean isValid() {
        return this.variance >= this.LI && this.variance <= this.LS;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("Grado de Aceptacion: ").append(acceptationGrade).append("\n")
                .append("Grado de Error: ").append(errorGrade).append("\n")
                .append("Media: ").append(average).append("\n")
                .append("Varianza: ").append(variance).append("\n")
                .append("N: ").append(numbersQuantity).append("\n")
                .append("aMedios: ").append(aMedios).append("\n")
                .append("1-aMedios: ").append(unoAMedios).append("\n")
                .append("chiCuadrado1: ").append(chiSqaure1).append("\n")
                .append("chiCuadrado2: ").append(chiSqaure2).append("\n")
                .append("Limite Superior: ").append(LS).append("\n")
                .append("Limite Inferior: ").append(LI).append("\n")
                .append("Numbers: {");
        list.forEach(e -> sb.append(e).append(", "));
        sb.delete(sb.length() - 2, sb.length()).append("}");
        return sb.toString();
    }
}
