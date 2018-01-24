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
public class CalculateScores {

    private int numReds, numGreens, numAmbers;
    private int[] ages, genders;
    private int[] height, weight, waist;

    public CalculateScores(int numReds, int numGreens, int numAmbers, int[] ages, int[] genders) {
        this.numReds = numReds;
        this.numGreens = numGreens;
        this.numAmbers = numAmbers;
        this.ages = ages;
        this.genders = genders;
    }

    public double[] calculateBFP() {
        int covered = numGreens;
        int height, weight, waist;
        double[] bfp = new double[ages.length];

        //<editor-fold defaultstate="collapsed" desc="Green BodyFat">
        for (int i = 0; i < covered; i++) {
            if (genders[i] == 1) {//males
                do {
                    height = (int) (Math.random() * 35 + 155);
                    weight = (int) (Math.random() * 30 + (55 + (50 - (ages[i] < 61 ? ages[i] : 60))));
                    waist = (int) (Math.random() * 35 + 60);
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * height)
                            + (0.149443 * weight)
                            + (0.149109 * waist);
                } while (bfp[i] < 13 || bfp[i] >= 22);
            } else {//females
                do {
                    height = (int) (Math.random() * 35 + 150);
                    weight = (int) (Math.random() * 35 + (45 + (50 - (ages[i] < 61 ? ages[i] : 60))));
                    waist = (int) (Math.random() * 40 + 40);
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * height)
                            + (0.149443 * weight)
                            + (0.149109 * waist);
                } while (bfp[i] < 24 || bfp[i] >= 33);
            }
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Amber BodyFat">
        covered += numAmbers;

        for (int i = numGreens; i < covered; i++) {
            int rand = (int) ((Math.random() * 500) * (Math.random() * 501));

            //<editor-fold defaultstate="collapsed" desc="Male">
            if (genders[i] == 1) {

                //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                if (ages[i] < 40) {
                    if (rand % 3 == 0) {
                        do {
                            height = (int) (Math.random() * 15 + 190);
                            weight = (int) (Math.random() * 6 + (29 + (50 - ages[i])));
                            waist = (int) (Math.random() * 10 + 50);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 8);
                    } else if (rand % 3 == 1) {
                        do {
                            height = (int) (Math.random() * 10 + 190);
                            weight = (int) (Math.random() * 5 + (114 + (50 - ages[i])));
                            waist = (int) (Math.random() * 5 + 84);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 22 || bfp[i] >= 25);
                    } else {
                        do {
                            height = (int) (Math.random() * 6 + 148);
                            weight = (int) (Math.random() * 6 + (60 + (50 - ages[i] < 61 ? ages[i] : 60)));
                            waist = (int) (Math.random() * 15 + 25);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 22 || bfp[i] >= 25);
                    }
                } else //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="40 -> 59">
                if (ages[i] < 60 && ages[i] > 39) {
                    if (rand % 3 == 0) {
                        do {
                            height = (int) (Math.random() * 15 + 190);
                            weight = (int) (Math.random() * 6 + (43 + (50 - (ages[i] < 61 ? ages[i] : 60))));
                            waist = (int) (Math.random() * 9 + 55);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 11);
                    } else if (rand % 3 == 1) {
                        do {
                            height = (int) (Math.random() * 10 + 195);
                            weight = (int) (Math.random() * 4 + (127 + (50 - (ages[i] < 61 ? ages[i] : 60))));
                            waist = (int) (Math.random() * 4 + 97);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 25 || bfp[i] >= 28);
                    } else {
                        do {
                            height = (int) (Math.random() * 10 + 156);
                            weight = (int) (Math.random() * 4 + (101 + (50 - (ages[i] < 61 ? ages[i] : 60))));
                            waist = (int) (Math.random() * 4 + 78);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 25 || bfp[i] >= 28);
                    }
                } else //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="60 -> 120">
                if (ages[i] > 60) {
                    if (rand % 3 == 0) {
                        do {
                            height = (int) (Math.random() * 15 + 190);
                            weight = (int) (Math.random() * 4 + (30 + (50 - ages[i] / 2)));
                            waist = (int) (Math.random() * 4 + 40);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 11);
                    } else if (rand % 3 == 1) {
                        do {
                            height = (int) (Math.random() * 10 + 185);
                            weight = (int) (Math.random() * 3 + (111 + (50 - ages[i] / 2)));
                            waist = (int) (Math.random() * 3 + 88);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 28 || bfp[i] >= 30);
                    } else {
                        do {
                            height = (int) (Math.random() * 10 + 156);
                            weight = (int) (Math.random() * 3 + (88 + (50 - ages[i] / 2)));
                            waist = (int) (Math.random() * 3 + 75);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 28 || bfp[i] >= 30);
                    }
                }
//</editor-fold>
            } //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Female">
            else {
                //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                if (ages[i] < 40) {
                    if (rand % 2 == 0) {
                        do {
                            height = (int) (Math.random() * 50 + 140);
                            weight = (int) (Math.random() * 35 + 30);
                            waist = (int) (Math.random() * 40 + 55);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 21);
                    } else {
                        do {
                            height = (int) (Math.random() * 25 + 150);
                            weight = (int) (Math.random() * 10 + (125 + (40 - ages[i])));
                            waist = (int) (Math.random() * 10 + (90 + (50 - ages[i])));
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 33 || bfp[i] >= 39);
                    }
                }//</editor-fold> 
                else //<editor-fold defaultstate="collapsed" desc="40 -> 59">
                if (ages[i] < 60 && ages[i] > 39) {
                    if (rand % 2 == 0) {
                        do {
                            height = (int) (Math.random() * 40 + 140);
                            weight = (int) (Math.random() * 30 + 30);
                            waist = (int) (Math.random() * 30 + 55);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 23);
                    } else {
                        do {
                            height = (int) (Math.random() * 20 + 160);
                            weight = (int) (Math.random() * 20 + (150));
                            waist = (int) (Math.random() * 20 + 100);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 35 || bfp[i] >= 40);
                    }
                } //</editor-fold>
                else //<editor-fold defaultstate="collapsed" desc="60 -> 120">
                if (ages[i] > 60) {
                    if (rand % 3 == 0) {
                        do {
                            height = (int) (Math.random() * 15 + 190);
                            weight = (int) (Math.random() * 4 + (30 + (50 - ages[i] / 2)));
                            waist = (int) (Math.random() * 4 + 40);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 24);
                    } else if (rand % 3 == 1) {
                        do {
                            height = (int) (Math.random() * 10 + 185);
                            weight = (int) (Math.random() * 3 + (111 + (50 - ages[i] / 2)));
                            waist = (int) (Math.random() * 3 + 88);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 28 || bfp[i] >= 30);
                    } else {
                        do {
                            height = (int) (Math.random() * 10 + 156);
                            weight = (int) (Math.random() * 3 + (88 + (50 - ages[i] / 2)));
                            waist = (int) (Math.random() * 3 + 75);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 28 || bfp[i] >= 30);
                    }
                }
//</editor-fold>
            }
//</editor-fold>
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Red BodyFat">
//</editor-fold>
        return new double[1];
    }
}
