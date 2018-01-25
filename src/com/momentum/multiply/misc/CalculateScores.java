/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momentum.multiply.misc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author zachristophers
 */
public class CalculateScores {

    private int numReds, numGreens, numAmbers, size;
    private int[] ages, genders;
    private int[] height, weight, waist;
    private String[] clientIDNums, rag;

    public CalculateScores(int numReds, int numGreens, int numAmbers, ResultSet rs) throws SQLException {
        this.numReds = numReds;
        this.numGreens = numGreens;
        this.numAmbers = numAmbers;
        int usedGreens = 0;
        int usedAmbers = 0;
        int usedReds = 0;
        int rand;
        boolean flag = false;
        this.clientIDNums = new String[(numReds + numAmbers + numGreens)];
        this.rag = new String[(numReds + numAmbers + numGreens)];
        for (int i = 0; i < (numReds + numAmbers + numGreens); i++) {
            this.clientIDNums[i] = "" + rs.getInt("CCIDNR");
            do {
                rand = (int) (Math.random() * 3);
                if (rand == 0 && usedGreens < numGreens) {
                    rag[i] = "green";
                    usedGreens++;
                    flag = true;
                } else if (rand == 1 && usedAmbers < numAmbers) {
                    rag[i] = "amber";
                    usedAmbers++;
                    flag = true;
                } else if (rand == 2 && usedReds < numReds) {
                    rag[i] = "red";
                    usedReds++;
                    flag = true;
                } else {
                    flag = true;
                }
            } while (flag == false);
            rs.next();

        }

        this.ages = generateAges();

        this.genders = generateGenders();
    }

    public double[] calculateBFP(int greenBFPScores, int amberBFPScores, int redBFPScores) {
        int covered = greenBFPScores;
        int height = 0, weight = 0, waist = 0;
        double[] bfp = new double[ages.length];
        this.height = new int[ages.length];
        this.weight = new int[ages.length];
        this.waist = new int[ages.length];

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
            this.height[i] = height;
            this.weight[i] = weight;
            this.waist[i] = waist;
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
                            height = (int) (Math.random() * 40 + 155);
                            weight = (int) (Math.random() * 25 + 25);
                            waist = (int) (Math.random() * 10 + 45);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 21);
                    } else {
                        do {
                            height = (int) (Math.random() * 20 + 155);
                            weight = (int) (Math.random() * 10 + (103 + (50 - ages[i])));
                            waist = (int) (Math.random() * 10 + 63);
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
                            height = (int) (Math.random() * 40 + 155);
                            weight = (int) (Math.random() * 10 + (25 + 50 - ages[i]));
                            waist = (int) (Math.random() * 10 + 35);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 23);
                    } else {
                        do {
                            height = (int) (Math.random() * 40 + 155);
                            weight = (int) (Math.random() * 5 + (110 + 50 - ages[i]));
                            waist = (int) (Math.random() * 5 + 100);
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
                    if (rand % 2 == 0) {
                        do {
                            height = (int) (Math.random() * 40 + 155);
                            weight = (int) (Math.random() * 10 + 30);
                            waist = (int) (Math.random() * 10 + 30);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 0 || bfp[i] >= 24);
                    } else {
                        do {
                            height = (int) (Math.random() * 40 + 155);
                            weight = (int) (Math.random() * 5 + 100);
                            waist = (int) (Math.random() * +90);
                            bfp[i] = 10.32970
                                    + (0.155437 * ages[i])
                                    + (11.19118 * genders[i])
                                    + (-0.184118 * height)
                                    + (0.149443 * weight)
                                    + (0.149109 * waist);
                        } while (bfp[i] < 36 || bfp[i] >= 42);
                    }
                }
//</editor-fold>
            }
//</editor-fold>

            this.height[i] = height;
            this.weight[i] = weight;
            this.waist[i] = waist;
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Red BodyFat">covered += numReds;
        covered += numReds;
        for (int i = numGreens + numAmbers; i < covered; i++) {

            //<editor-fold defaultstate="collapsed" desc="Male">
            if (genders[i] == 1) {

                //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                if (ages[i] < 40) {
                    height = (int) (Math.random() * 40 + 155);
                    weight = (int) (Math.random() * 50 + 120);
                    waist = (int) (Math.random() * 50 + 110);
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * height)
                            + (0.149443 * weight)
                            + (0.149109 * waist);

                } else //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="40 -> 120">
                {
                    height = (int) (Math.random() * 40 + 155);
                    weight = (int) (Math.random() * 50 + 125);
                    waist = (int) (Math.random() * 50 + 110);
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * height)
                            + (0.149443 * weight)
                            + (0.149109 * waist);
                }
//</editor-fold>
            } //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Female">
            else {
                //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                if (ages[i] < 40) {
                    height = (int) (Math.random() * 40 + 155);
                    weight = (int) (Math.random() * 50 + 145);
                    waist = (int) (Math.random() * 50 + 125);
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * height)
                            + (0.149443 * weight)
                            + (0.149109 * waist);
                }//</editor-fold> 
                else //<editor-fold defaultstate="collapsed" desc="40 -> 120">
                {
                    height = (int) (Math.random() * 40 + 155);
                    weight = (int) (Math.random() * 50 + 135);
                    waist = (int) (Math.random() * 50 + 125);
                    bfp[i] = 10.32970
                            + (0.155437 * ages[i])
                            + (11.19118 * genders[i])
                            + (-0.184118 * height)
                            + (0.149443 * weight)
                            + (0.149109 * waist);
                }
            }
//</editor-fold>

            this.height[i] = height;
            this.weight[i] = weight;
            this.waist[i] = waist;

//</editor-fold>
        }
//</editor-fold>

        return bfp;
    }

    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    private int[] generateAges() {
        String[] array = new String[size];
        try {
            for (int i = 0; i < size; i++) {
                array[i] = "19" + this.clientIDNums[i].substring(0, 2) + "/" + this.clientIDNums[i].substring(2, 4) + "/" + this.clientIDNums[i].substring(4, 6);
            }
        } catch (Exception e) {
            System.out.println("Yo. Exception.");
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        int[] arrays = new int[size];

        for (int i = 0; i < size; i++) {
            arrays[i] = getDiffYears(new java.util.Date(array[i]), new java.util.Date());
        }
        return arrays;
    }

    private int[] generateGenders() {
        String[] array = new String[size];
        try {
            for (int i = 0; i < size; i++) {
                array[i] = this.clientIDNums[i].substring(6, 10);
            }
        } catch (Exception e) {
            System.out.println("Yo. Exception.");
        }

        int[] arrays = new int[size];

        for (int i = 0; i < size; i++) {
            arrays[i] = (Integer.parseInt(array[i]) < 5000 ? 2 : 1);
        }
        return arrays;
    }

    private int getDiffYears(java.util.Date first, java.util.Date last) {
        Calendar a = getCalendar(first);
        Calendar b = getCalendar(last);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH)
                || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    private Calendar getCalendar(java.util.Date date) {
        Calendar cal = Calendar.getInstance(Locale.US);
        cal.setTime(date);
        return cal;
    }
    //</editor-fold>
}
