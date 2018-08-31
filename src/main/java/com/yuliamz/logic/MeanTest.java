package com.yuliamz.logic;

import java.util.List;

/**
 *
 * @author Yuliamz
 */
public class MeanTest {
    
    private final int acceptationGrade;
    private final int errorGrade;
    private final double average;
    private final int numbersQuantity;
    private final double aMedios;
    private final double Z;
    private final double LS;
    private final double LI;
    private final List<Float> list;

    public MeanTest(List<Float> numbers, int acceptationGrade) {
        this.acceptationGrade=acceptationGrade;
        this.errorGrade=100-acceptationGrade;
        this.list=numbers;
        this.average=list.stream().mapToDouble(a->a).average().orElse(Double.NaN);
        this.numbersQuantity=list.size();
        this.aMedios = 1 - ((this.errorGrade / 100.0) / 2.0);
        this.Z=Utils.NORMAL_DISTRIBUTION.inverseCumulativeProbability(aMedios);
        this.LI=(0.5)-Z*(1/(Math.sqrt(12*numbersQuantity)));
        this.LS=(0.5)+Z*(1/(Math.sqrt(12*numbersQuantity)));
    }
    
    public boolean isValid(){
        return this.average>=this.LI && this.average<=this.LS;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("Grado de Aceptacion: ").append(acceptationGrade).append("\n")
                .append("Grado de Error: ").append(errorGrade).append("\n")
                .append("R: ").append(average).append("\n")
                .append("N: ").append(numbersQuantity).append("\n")
                .append("aMedios: ").append(aMedios).append("\n")
                .append("Z: ").append(this.Z).append("\n")
                .append("Limite Superior: ").append(LS).append("\n")
                .append("Limite Inferior: ").append(LI).append("\n")
                .append("Numbers: {");
        list.forEach(e-> sb.append(e).append(", "));
        sb.delete(sb.length() - 2, sb.length()).append("}");
        return sb.toString();
    }
    
    
}
