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
public class Determinant {

    //<editor-fold defaultstate="collapsed" desc="Variables">
    private String id;
    private COLOURS colour, bfpCol, bpsCol, bpdCol, cholCol, gluCol, smokerCol;
    private int height, weight, waist, age, gender, points, bps, bpd, smoker;
    private double cholestorol, glucose, bfp;
    private int[] pointPortfolio;

//</editor-fold>
    public Determinant(String id, COLOURS col) {
        this.id = id;
        colour = col;
        int[] temp = {0, 0, 0, 0, 0, 0};
        pointPortfolio = temp;
    }

    //<editor-fold defaultstate="collapsed" desc="COLOURS">
    public enum COLOURS {
        GREEN,
        AMBER,
        RED
    };
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters And Setters">
    public void setAllColours(COLOURS bfpCol, COLOURS bpsCol, COLOURS bpdCol, COLOURS cholCol, COLOURS gluCol, COLOURS smoker) {
        this.bfpCol = bfpCol;
        this.bpsCol = bpsCol;
        this.bpdCol = bpdCol;
        this.cholCol = cholCol;
        this.gluCol = gluCol;
        this.smokerCol = smoker;
    }

    public void setAllColours(int bfpCol, int bpsCol, int bpdCol, int cholCol, int gluCol, int smoker) {
        setAllColours(COLOURS.values()[bfpCol], COLOURS.values()[bpsCol], COLOURS.values()[bpdCol], COLOURS.values()[cholCol], COLOURS.values()[gluCol], COLOURS.values()[smoker]);
    }

    public COLOURS getColours(int pos) {
        switch (pos) {
            case 0:
                return bfpCol;
            case 1:
                return bpsCol;
            case 2:
                return bpdCol;
            case 3:
                return cholCol;
            case 4:
                return gluCol;
            default:
                return smokerCol;
        }
    }

    public COLOURS getSmokerCol() {
        return smokerCol;
    }

    public void setSmokerCol(COLOURS smokerCol) {
        this.smokerCol = smokerCol;
    }

    public int getSmoker() {
        return smoker;
    }

    public void setSmoker(int smoker) {
        this.smoker = smoker;
    }

    public void setAllColours(int[] vals) {
        int[] values = new int[6];
        for (int i = 0; i < 6; i++) {
            if (vals[i] == 0) {
                values[i] = 0;
            } else if (vals[i] > 2) {
                values[i] = 2;
            } else {
                values[i] = 1;
            }
        }
        pointPortfolio = vals;
        setAllColours(values[0], values[1], values[2], values[3], values[4], values[5]);
    }

    public int getPoints() {
        return points;
    }

    public int getPoints(int i) {
        return pointPortfolio[i];
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public COLOURS getBfpCol() {
        return bfpCol;
    }

    public void setBfpCol(COLOURS bfpCol) {
        this.bfpCol = bfpCol;
    }

    public COLOURS getBpsCol() {
        return bpsCol;
    }

    public void setBpsCol(COLOURS bpsCol) {
        this.bpsCol = bpsCol;
    }

    public COLOURS getBpdCol() {
        return bpdCol;
    }

    public void setBpdCol(COLOURS bpdCol) {
        this.bpdCol = bpdCol;
    }

    public COLOURS getCholCol() {
        return cholCol;
    }

    public void setCholCol(COLOURS cholCol) {
        this.cholCol = cholCol;
    }

    public COLOURS getGluCol() {
        return gluCol;
    }

    public void setGluCol(COLOURS gluCol) {
        this.gluCol = gluCol;
    }

    public double getGlucose() {
        return glucose;
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public double getCholestorol() {
        return cholestorol;
    }

    public void setCholestorol(double cholestorol) {
        this.cholestorol = cholestorol;
    }

    public int getBps() {
        return bps;
    }

    public void setBps(int bps) {
        this.bps = bps;
    }

    public int getBpd() {
        return bpd;
    }

    public void setBpd(int bpd) {
        this.bpd = bpd;
    }

    public double getBfp() {
        return bfp;
    }

    public void setBfp(double bfp) {
        this.bfp = bfp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public COLOURS getColour() {
        return colour;
    }

    public void setColour(COLOURS colour) {
        this.colour = colour;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
//</editor-fold>

}
