/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momentum.multiply.misc;

import com.momentum.multiply.misc.Determinant.COLOURS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author zachristophers
 */
public class CalculateScores {

    private int numReds, numGreens, numAmbers, size;
    private Determinant[] clients;
    private String[] clientIDNums;
    private String[] contractNumber;
    private DecimalFormat df;

    public CalculateScores(int numReds, int numGreens, int numAmbers, ResultSet rs) throws SQLException {
        this.numReds = numReds;
        this.numGreens = numGreens;
        this.numAmbers = numAmbers;
        int usedGreens = 0;
        int usedAmbers = 0;
        int usedReds = 0;
        int rand;
        boolean flag = false;
        size = (numReds + numAmbers + numGreens);
        this.clientIDNums = new String[size];
        this.clients = new Determinant[size];
        for (int i = 0; i < size; i++) {
            this.clientIDNums[i] = "" + rs.getInt("CCIDNR");
            this.contractNumber[i] = "" + rs.getInt("CRCNBR");
            do {
                rand = (int) (Math.random() * 3);
                if (rand == 0 && usedGreens < numGreens) {
                    clients[i] = new Determinant(clientIDNums[i], contractNumber[i], COLOURS.GREEN);
                    usedGreens++;
                    flag = true;
                } else if (rand == 1 && usedAmbers < numAmbers) {
                    clients[i] = new Determinant(clientIDNums[i], contractNumber[i], COLOURS.AMBER);
                    usedAmbers++;
                    flag = true;
                } else if (rand == 2 && usedReds < numReds) {
                    clients[i] = new Determinant(clientIDNums[i], contractNumber[i], COLOURS.RED);
                    usedReds++;
                    flag = true;
                } else {
                    flag = true;
                }
            } while (flag == false);
            rs.next();

        }

        generateAges();
        generateGenders();
        df = new DecimalFormat("#.##");
    }

    public void generate() {
        generatePointAllocation();
        calculateBFP();
        calculateBloodPressure();
        calculateCholestorol();
        calculateGlucose();
        calculateSmoker();
    }

    public Determinant getClient(int i) {
        return clients[i];
    }

    public void post() {

    }

    public void generatePointAllocation() {
        int[] invPts = {0, 0, 0, 0, 0, 0};
        int[] tempArr = {0, 0, 0, 0, 0, 0};
        int points = -1, temp = 0;
        for (int i = 0; i < clients.length; i++) {
            switch (clients[i].getColour()) {
                //<editor-fold defaultstate="collapsed" desc="Green Score">
                case GREEN:
                    clients[i].setPoints(0);
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Amber Score">
                case AMBER:
                    clients[i].setPoints((int) (Math.random() * 3 + 1));
                    if (points == 1) {
                        do {
                            temp = (int) (Math.random() * 6);
                        } while (invPts[temp] == 1);
                        invPts[temp] = 1;
                    } else if (points == 2) {
                        temp = (int) (Math.random() * 23847) % 7;
                        switch (temp) {
                            case 0:
                                invPts[1] = 2;
                                break;
                            case 1:
                                invPts[2] = 2;
                                break;
                            case 3:
                                invPts[3] = 2;
                                break;
                            case 4:
                                invPts[5] = 2;
                                break;
                            case 5:
                                invPts[1] = 1;
                                invPts[2] = 1;
                                break;
                            case 6:
                                for (int j = 0; j < 2; j++) {
                                    do {
                                        temp = (int) (Math.random() * 6);
                                    } while (invPts[temp] >= 1);
                                    invPts[temp] = 1;
                                }
                                break;
                        }
                    } else if (points == 3) {
                        temp = (int) (Math.random() * 23847) % 9;
                        switch (temp) {
                            case 0:
                                invPts[1] = 3;
                                break;
                            case 1:
                                invPts[2] = 3;
                                break;
                            case 3:
                                invPts[3] = 3;
                                break;
                            case 4:
                                invPts[5] = 2;
                                do {
                                    temp = (int) (Math.random() * 6);
                                } while (invPts[temp] >= 1);
                                invPts[temp] = 1;

                                break;
                            case 5:
                                invPts[1] = 1;
                                invPts[2] = 1;
                                do {
                                    temp = (int) (Math.random() * 6);
                                } while (invPts[temp] == 1);
                                invPts[temp] = 1;
                                break;
                            case 6:
                                for (int j = 0; j < 3; j++) {
                                    do {
                                        temp = (int) (Math.random() * 6);
                                    } while (invPts[temp] == 1);
                                    invPts[temp] = 1;
                                }
                                break;
                            case 7:
                                invPts[4] = 3;
                                break;
                            case 8:
                                invPts[0] = 3;
                                break;
                        }
                    }
                    break;
//</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Red Score">
                case RED:
                    clients[i].setPoints((int) (Math.random() * 11 + 4));
                    for (int j = 0; j < clients[i].getPoints();) {
                        do {
                            temp = (int) (Math.random() * 6);
                        } while (invPts[temp] == 0);
                        switch (temp) {
                            case 0:
                            case 4:
                                do {
                                    invPts[temp] = (int) (Math.random() * 4);
                                } while (invPts[temp] == 2);
                                break;
                            case 1:
                            case 2:
                            case 3:
                                invPts[temp] = (int) (Math.random() * 4);
                                break;
                            default:
                                do {
                                    invPts[temp] = (int) (Math.random() * 3);
                                } while (invPts[temp] == 1);
                                break;
                        }
                        j += invPts[temp];
                    }
//</editor-fold>
            }
            clients[i].setAllColours(invPts);
        }

    }

    public void calculateBFP() {
        int height = 0, weight = 0, waist = 0;
        double[] bfp = new double[clients.length];

        for (int i = 0; i < size; i++) {
            //<editor-fold defaultstate="collapsed" desc="Green BodyFat">
            if (clients[i].getBfpCol() == COLOURS.GREEN) {
                //<editor-fold defaultstate="collapsed" desc="Males">
                if (clients[i].getGender() == 1) {
                    do {
                        height = (int) (Math.random() * 35 + 155);
                        weight = (int) (Math.random() * 30 + (55 + (50 - (clients[i].getAge() < 61 ? clients[i].getAge() : 60))));
                        waist = (int) (Math.random() * 35 + 60);
                        bfp[i] = 10.32970
                                + (0.155437 * clients[i].getAge())
                                + (11.19118 * clients[i].getGender())
                                + (-0.184118 * height)
                                + (0.149443 * weight)
                                + (0.149109 * waist);
                    } while (bfp[i] < 13 || bfp[i] >= 22);
                } //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Females">
                else {
                    do {
                        height = (int) (Math.random() * 35 + 150);
                        weight = (int) (Math.random() * 35 + (45 + (50 - (clients[i].getAge() < 61 ? clients[i].getAge() : 60))));
                        waist = (int) (Math.random() * 40 + 40);
                        bfp[i] = 10.32970
                                + (0.155437 * clients[i].getAge())
                                + (11.19118 * clients[i].getGender())
                                + (-0.184118 * height)
                                + (0.149443 * weight)
                                + (0.149109 * waist);
                    } while (bfp[i] < 24 || bfp[i] >= 33);
                }
//</editor-fold>
            } //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Amber BodyFat">
            else if (clients[i].getBfpCol() == COLOURS.AMBER) {
                int rand = (int) ((Math.random() * 500) * (Math.random() * 501));

                //<editor-fold defaultstate="collapsed" desc="Male">
                if (clients[i].getGender() == 1) {

                    //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                    if (clients[i].getAge() < 40) {
                        if (rand % 3 == 0) {
                            do {
                                height = (int) (Math.random() * 15 + 190);
                                weight = (int) (Math.random() * 6 + (29 + (50 - clients[i].getAge())));
                                waist = (int) (Math.random() * 10 + 50);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 0 || bfp[i] >= 8);
                        } else if (rand % 3 == 1) {
                            do {
                                height = (int) (Math.random() * 10 + 190);
                                weight = (int) (Math.random() * 5 + (114 + (50 - clients[i].getAge())));
                                waist = (int) (Math.random() * 5 + 84);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 22 || bfp[i] >= 25);
                        } else {
                            do {
                                height = (int) (Math.random() * 6 + 148);
                                weight = (int) (Math.random() * 6 + (60 + (50 - clients[i].getAge() < 61 ? clients[i].getAge() : 60)));
                                waist = (int) (Math.random() * 15 + 25);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 22 || bfp[i] >= 25);
                        }
                    } else //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="40 -> 59">
                    if (clients[i].getAge() < 60 && clients[i].getAge() > 39) {
                        if (rand % 3 == 0) {
                            do {
                                height = (int) (Math.random() * 15 + 190);
                                weight = (int) (Math.random() * 6 + (43 + (50 - (clients[i].getAge() < 61 ? clients[i].getAge() : 60))));
                                waist = (int) (Math.random() * 9 + 55);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 0 || bfp[i] >= 11);
                        } else if (rand % 3 == 1) {
                            do {
                                height = (int) (Math.random() * 10 + 195);
                                weight = (int) (Math.random() * 4 + (127 + (50 - (clients[i].getAge() < 61 ? clients[i].getAge() : 60))));
                                waist = (int) (Math.random() * 4 + 97);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 25 || bfp[i] >= 28);
                        } else {
                            do {
                                height = (int) (Math.random() * 10 + 156);
                                weight = (int) (Math.random() * 4 + (101 + (50 - (clients[i].getAge() < 61 ? clients[i].getAge() : 60))));
                                waist = (int) (Math.random() * 4 + 78);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 25 || bfp[i] >= 28);
                        }
                    } else //</editor-fold>
                    //<editor-fold defaultstate="collapsed" desc="60 -> 120">
                    if (clients[i].getAge() > 60) {
                        if (rand % 3 == 0) {
                            do {
                                height = (int) (Math.random() * 15 + 190);
                                weight = (int) (Math.random() * 4 + (30 + (50 - clients[i].getAge() / 2)));
                                waist = (int) (Math.random() * 4 + 40);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 0 || bfp[i] >= 11);
                        } else if (rand % 3 == 1) {
                            do {
                                height = (int) (Math.random() * 10 + 185);
                                weight = (int) (Math.random() * 3 + (111 + (50 - clients[i].getAge() / 2)));
                                waist = (int) (Math.random() * 3 + 88);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 28 || bfp[i] >= 30);
                        } else {
                            do {
                                height = (int) (Math.random() * 10 + 156);
                                weight = (int) (Math.random() * 3 + (88 + (50 - clients[i].getAge() / 2)));
                                waist = (int) (Math.random() * 3 + 75);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
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
                    if (clients[i].getAge() < 40) {
                        if (rand % 2 == 0) {
                            do {
                                height = (int) (Math.random() * 40 + 155);
                                weight = (int) (Math.random() * 25 + 25);
                                waist = (int) (Math.random() * 10 + 45);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 0 || bfp[i] >= 21);
                        } else {
                            do {
                                height = (int) (Math.random() * 20 + 155);
                                weight = (int) (Math.random() * 10 + (103 + (50 - clients[i].getAge())));
                                waist = (int) (Math.random() * 10 + 63);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 33 || bfp[i] >= 39);
                        }
                    }//</editor-fold> 
                    else //<editor-fold defaultstate="collapsed" desc="40 -> 59">
                    if (clients[i].getAge() < 60 && clients[i].getAge() > 39) {
                        if (rand % 2 == 0) {
                            do {
                                height = (int) (Math.random() * 40 + 155);
                                weight = (int) (Math.random() * 10 + (25 + 50 - clients[i].getAge()));
                                waist = (int) (Math.random() * 10 + 35);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 0 || bfp[i] >= 23);
                        } else {
                            do {
                                height = (int) (Math.random() * 40 + 155);
                                weight = (int) (Math.random() * 5 + (110 + 50 - clients[i].getAge()));
                                waist = (int) (Math.random() * 5 + 100);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 35 || bfp[i] >= 40);
                        }
                    } //</editor-fold>
                    else //<editor-fold defaultstate="collapsed" desc="60 -> 120">
                    if (clients[i].getAge() > 60) {
                        if (rand % 2 == 0) {
                            do {
                                height = (int) (Math.random() * 40 + 155);
                                weight = (int) (Math.random() * 10 + 30);
                                waist = (int) (Math.random() * 10 + 30);
                                bfp[i] = 10.32970
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
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
                                        + (0.155437 * clients[i].getAge())
                                        + (11.19118 * clients[i].getGender())
                                        + (-0.184118 * height)
                                        + (0.149443 * weight)
                                        + (0.149109 * waist);
                            } while (bfp[i] < 36 || bfp[i] >= 42);
                        }
                    }
//</editor-fold>
                }
//</editor-fold>
            } //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Red BodyFat">
            else {
                //<editor-fold defaultstate="collapsed" desc="Male">
                if (clients[i].getGender() == 1) {

                    //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                    if (clients[i].getAge() < 40) {
                        height = (int) (Math.random() * 40 + 155);
                        weight = (int) (Math.random() * 50 + 120);
                        waist = (int) (Math.random() * 50 + 110);
                        bfp[i] = 10.32970
                                + (0.155437 * clients[i].getAge())
                                + (11.19118 * clients[i].getGender())
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
                                + (0.155437 * clients[i].getAge())
                                + (11.19118 * clients[i].getGender())
                                + (-0.184118 * height)
                                + (0.149443 * weight)
                                + (0.149109 * waist);
                    }
//</editor-fold>
                } //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="Female">
                else {
                    //<editor-fold defaultstate="collapsed" desc="18 -> 39">
                    if (clients[i].getAge() < 40) {
                        height = (int) (Math.random() * 40 + 155);
                        weight = (int) (Math.random() * 50 + 145);
                        waist = (int) (Math.random() * 50 + 125);
                        bfp[i] = 10.32970
                                + (0.155437 * clients[i].getAge())
                                + (11.19118 * clients[i].getGender())
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
                                + (0.155437 * clients[i].getAge())
                                + (11.19118 * clients[i].getGender())
                                + (-0.184118 * height)
                                + (0.149443 * weight)
                                + (0.149109 * waist);
                    }
                }
//</editor-fold>
//</editor-fold>
            }
//</editor-fold>

            clients[i].setWeight(weight);
            clients[i].setHeight(height);
            clients[i].setWaist(waist);
            clients[i].setBfp(bfp[i]);
        }
    }

    public void calculateCholestorol() {
        for (int i = 0; i < size; i++) {
            if (clients[i].getCholCol() == COLOURS.GREEN) {
                clients[i].setCholestorol(Double.parseDouble(df.format(Math.random() * (5.19 - 4.11) + 4.11)));
            }
            if (clients[i].getCholCol() == COLOURS.AMBER) {
                if (clients[i].getPoints(3) == 1) {
                    int rand = (int) (Math.random() * 451665 % 2);
                    if (rand == 0) {
                        clients[i].setCholestorol(Double.parseDouble(df.format(Math.random() * (4.1 - 0) + 0)));
                    } else {
                        clients[i].setCholestorol(Double.parseDouble(df.format(Math.random() * (6.21 - 5.2) + 5.2)));
                    }
                } else {
                    clients[i].setCholestorol(Double.parseDouble(df.format(Math.random() * (7.24 - 6.22) + 6.22)));
                }
            }
            if (clients[i].getCholCol() == COLOURS.RED) {
                clients[i].setCholestorol(Double.parseDouble(df.format(Math.random() * (40 - 7.25) + 7.25)));
            }
        }
    }

    public void calculateGlucose() {
        for (int i = 0; i < size; i++) {
            if (clients[i].getGluCol() == COLOURS.GREEN) {
                clients[i].setGlucose(Double.parseDouble(df.format(Math.random() * (7.8 - 3.91) + 3.91)));
            }
            if (clients[i].getGluCol() == COLOURS.AMBER) {
                int rand = (int) (Math.random() * 451665 % 2);
                if (rand == 0) {
                    clients[i].setGlucose(Double.parseDouble(df.format(Math.random() * (3.9 - 0) + 0)));
                } else {
                    clients[i].setGlucose(Double.parseDouble(df.format(Math.random() * (11 - 7.81) + 7.81)));
                }
            }
            if (clients[i].getGluCol() == COLOURS.RED) {
                clients[i].setGlucose(Double.parseDouble(df.format(Math.random() * (70 - 11.01) + 11.01)));
            }
        }
    }

    public void calculateBloodPressure() {
        int bpScore, rand;
        for (int i = 0; i < size; i++) {
            bpScore = clients[i].getPoints(1) + clients[i].getPoints(2);
            if (bpScore == 0) {
                clients[i].setBps((int) (Math.random() * (129 - 91) + 91));
                clients[i].setBpd((int) (Math.random() * (89 - 61) + 61));
            } else if (bpScore == 1) {
                rand = (int) (Math.random() * 21265 % 3);
                if (rand == 0) {
                    clients[i].setBps((int) (Math.random() * (90 - 0) + 0));
                    clients[i].setBpd((int) (Math.random() * (80 - 61) + 61));
                } else if (rand == 1) {
                    clients[i].setBps((int) (Math.random() * (129 - 91) + 91));
                    clients[i].setBpd((int) (Math.random() * (60 - 0) + 0));
                } else if (rand == 2) {
                    clients[i].setBps((int) (Math.random() * (139 - 130) + 130));
                    clients[i].setBpd((int) (Math.random() * (89 - 61) + 61));
                }
            } else if (bpScore == 2) {
                rand = (int) (Math.random() * 21265 % 5);
                if (rand == 0) {
                    clients[i].setBps((int) (Math.random() * (90 - 0) + 0));
                    clients[i].setBpd((int) (Math.random() * (89 - 81) + 81));
                } else if (rand == 1) {
                    clients[i].setBps((int) (Math.random() * (129 - 91) + 91));
                    clients[i].setBpd((int) (Math.random() * (99 - 90) + 90));
                } else if (rand == 2) {
                    clients[i].setBps((int) (Math.random() * (139 - 130) + 130));
                    clients[i].setBpd((int) (Math.random() * (60 - 0) + 0));
                } else if (rand == 3) {
                    clients[i].setBps((int) (Math.random() * (159 - 140) + 140));
                    clients[i].setBpd((int) (Math.random() * (89 - 61) + 61));
                } else if (rand == 4) {
                    clients[i].setBps((int) (Math.random() * (159 - 140) + 140));
                    clients[i].setBpd((int) (Math.random() * (89 - 61) + 61));
                }
            } else if (bpScore == 3) {
                rand = (int) (Math.random() * 21265 % 6);
                if (rand == 0) {
                    clients[i].setBps((int) (Math.random() * (90 - 0) + 0));
                    clients[i].setBpd((int) (Math.random() * (99 - 90) + 90));
                } else if (rand == 1) {
                    clients[i].setBps((int) (Math.random() * (129 - 91) + 91));
                    clients[i].setBpd((int) (Math.random() * (300 - 100) + 100));
                } else if (rand == 2) {
                    clients[i].setBps((int) (Math.random() * (139 - 130) + 130));
                    clients[i].setBpd((int) (Math.random() * (99 - 90) + 90));
                } else if (rand == 3) {
                    clients[i].setBps((int) (Math.random() * (159 - 140) + 140));
                    clients[i].setBpd((int) (Math.random() * (60 - 0) + 0));
                } else if (rand == 4) {
                    clients[i].setBps((int) (Math.random() * (400 - 160) + 160));
                    clients[i].setBpd((int) (Math.random() * (89 - 61) + 61));
                }
            } else if (bpScore == 4) {
                rand = (int) (Math.random() * 21265 % 4);
                if (rand == 0) {
                    clients[i].setBps((int) (Math.random() * (90 - 0) + 0));
                    clients[i].setBpd((int) (Math.random() * (300 - 100) + 100));
                } else if (rand == 1) {
                    clients[i].setBps((int) (Math.random() * (139 - 130) + 130));
                    clients[i].setBpd((int) (Math.random() * (300 - 100) + 100));
                } else if (rand == 2) {
                    clients[i].setBps((int) (Math.random() * (159 - 140) + 140));
                    clients[i].setBpd((int) (Math.random() * (99 - 90) + 90));
                } else if (rand == 3) {
                    clients[i].setBps((int) (Math.random() * (400 - 160) + 160));
                    clients[i].setBpd((int) (Math.random() * (60 - 0) + 0));
                }
            } else if (bpScore == 5) {
                rand = (int) (Math.random() * 21265 % 2);
                if (rand == 0) {
                    clients[i].setBps((int) (Math.random() * (159 - 140) + 140));
                    clients[i].setBpd((int) (Math.random() * (300 - 100) + 100));
                } else if (rand == 1) {
                    clients[i].setBps((int) (Math.random() * (400 - 160) + 160));
                    clients[i].setBpd((int) (Math.random() * (99 - 90) + 90));
                }
            } else if (bpScore == 6) {
                clients[i].setBps((int) (Math.random() * (400 - 160) + 160));
                clients[i].setBpd((int) (Math.random() * (300 - 100) + 100));
            }
        }
    }

    public void calculateSmoker() {
        for (int i = 0; i < size; i++) {
            if (clients[i].getSmokerCol() == COLOURS.GREEN) {
                clients[i].setSmoker(0);
            } else {
                clients[i].setSmoker(2);
            }
        }
    }

    private int calculateNumbers(COLOURS col, int pos) {
        int count = 0;
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getColours(pos) == col) {
                count++;
            }
        }
        return count;
    }

    //<editor-fold defaultstate="collapsed" desc="Private Methods">
    private void generateAges() {
        String[] array = new String[size];
        try {
            for (int i = 0; i < size; i++) {
                array[i] = "19" + this.clientIDNums[i].substring(0, 2) + "/" + this.clientIDNums[i].substring(2, 4) + "/" + this.clientIDNums[i].substring(4, 6);
            }
        } catch (Exception e) {
            System.out.println("Yo. Exception.");
        }

        for (int i = 0; i < size; i++) {
            clients[i].setAge(getDiffYears(new java.util.Date(array[i]), new java.util.Date()));
        }
    }

    private void generateGenders() {
        String[] array = new String[size];
        try {
            for (int i = 0; i < size; i++) {
                array[i] = this.clientIDNums[i].substring(6, 10);
            }
        } catch (Exception e) {
            System.out.println("Yo. Exception.");
        }

        for (int i = 0; i < size; i++) {
            clients[i].setGender((Integer.parseInt(array[i]) < 5000 ? 2 : 1));
        }
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
