/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momentum.multiply.misc;

/**
 *
 * @author zachristophers
 */
public class CalculateBodyFat {

    private int numReds, numGreens, numAmbers;
    private int[] ages, genders;

    public CalculateBodyFat(int numReds, int numGreens, int numAmbers, int[] ages, int[] genders) {
        this.numReds = numReds;
        this.numGreens = numGreens;
        this.numAmbers = numAmbers;
        this.ages = ages;
        this.genders = genders;
    }

    public double[] calculate() {
        int covered = numGreens;
        double[] bfp = new double[ages.length];

        for (int i = 0; i < covered; i++) {
            bfp[i] = 10.32970
                    + (0.155437 * ages[i])
                    + (11.19118 * genders[i])
                    + (-0.184118 * (int) (Math.random() * 40 + 165))
                    + (0.149443 * 10 + 70)
                    + (0.149109 * 35 + 55);
        }
        covered += numAmbers;

        for (int i = numGreens; i < covered; i++) {
            int rand = (int) ((Math.random() * 500) * (Math.random() * 501));
            if (rand % 4 == 0) {
                do {
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * (int) (Math.random() * 20 + 140))
                            + (0.149443 * 10 + 30)
                            + (0.149109 * 15 + 25);
                } while (bfp[i] < 0 || bfp[i] > 8);
            } else if (rand % 4 == 1) {
                do {
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * (int) (Math.random() * 20 + 200))
                            + (0.149443 * 10 + 30)
                            + (0.149109 * 15 + 25);
                } while (bfp[i] < 0 || bfp[i] > 8);
            }
        }

        return 0.0;
    }
}
