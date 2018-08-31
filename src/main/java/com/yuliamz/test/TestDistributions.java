/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yuliamz.test;

import org.apache.commons.math3.distribution.NormalDistribution;

/**
 *
 * @author Yuliamz
 */
public class TestDistributions {
    
    public static void main(String[] args) {
        NormalDistribution distribution = new NormalDistribution();
        System.out.println(distribution.cumulativeProbability(0.975));
        System.out.println(distribution.inverseCumulativeProbability(0.975));
    }
}
