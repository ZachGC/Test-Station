/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momentum.multiply.hhs.post;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.*;

/**
 *
 * @author zachristophers
 */
public class ClientMeasurementGeneration {

    private boolean smoker, pregnant;
    private int height, bps, bpd, waist;
    private float glu_rand, chol, weight;
    private String json = "";

    //<editor-fold> 
    public boolean isSmoker() {
        return smoker;
    }

    public void setSmoker(boolean smoker) {
        this.smoker = smoker;
    }

    public boolean isPregnant() {
        return pregnant;
    }

    public void setPregnant(boolean pregnant) {
        this.pregnant = pregnant;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    public int getWaist() {
        return waist;
    }

    public void setWaist(int waist) {
        this.waist = waist;
    }

    public float getGlu_rand() {
        return glu_rand;
    }

    public void setGlu_rand(float glu_rand) {
        this.glu_rand = glu_rand;
    }

    public float getChol() {
        return chol;
    }

    public void setChol(float chol) {
        this.chol = chol;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    //</editor-fold>//Accessors and Mutators
    
    public ClientMeasurementGeneration() {
    }

    public ClientMeasurementGeneration(boolean smoker, boolean pregnant, boolean male, int height, int bps, int bpd, int waist, float glu_rand, float chol, float weight) {
        this.smoker = smoker;
        this.pregnant = pregnant;
        this.height = height;
        this.bps = bps;
        this.bpd = bpd;
        this.waist = waist;
        this.glu_rand = glu_rand;
        this.chol = chol;
        this.weight = weight;
    }

    private String createClientJSON() {
        String smoker = "Non-";
        String pregnant = "NO";
        if (this.smoker) {
            smoker = "";
        }
        if (this.pregnant) {
            pregnant = "YES";
        }
        String json = "[  \r\n   "
                //<editor-fold defaultstate="collapsed" desc="Blood Pressure">
                + "{  \r\n      \"name\":\"Blood Pressure\",\r\n      "
                + "\"type\":\"Health Risk Assessment\",\r\n      "
                + "\"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"diastolic\":\"" + bpd + "\",\r\n         \"systolic\":\"" + bps + "\"\r\n      }\r\n   },\r\n   "
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Cholesterol">
                + "{  \r\n      "
                + "\"name\":\"Cholesterol\",\r\n      "
                + "\"type\":\"Health Risk Assessment\",\r\n      "
                + "\"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + chol + "\"\r\n      }\r\n   },"
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Glucose - Random">
                + "\r\n   {  \r\n      \"name\":\"Glucose - Random\",\r\n      "
                + "\"type\":\"Health Risk Assessment\",\r\n      "
                + "\"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + glu_rand + "\"\r\n      }\r\n   },"
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Height">
                + "\r\n   {  \r\n      \"name\":\"Height\",\r\n      "
                + "\"type\":\"Health Risk Assessment\",\r\n      "
                + "\"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + height + "\"\r\n      }\r\n   },\r\n   "
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Waist">
                + "{  \r\n      \"name\":\"Waist\",\r\n      "
                + "\"type\":\"Health Risk Assessment"
                + "\",\r\n      \"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + waist + "\"\r\n      }\r\n   },"
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Smoker Status">
                + "\r\n   {  \r\n      \"name\":\"" + smoker + "Smoking Declaration\",\r\n      "
                + "\"type\":\"Health Risk Assessment\",\r\n      "
                + "\"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + smoker + "Smoker\"\r\n      }\r\n   },"
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Weight">
                + "\r\n   {  \r\n      \"name\":\"Weight\",\r\n      "
                + "\"type\":\"Health Risk Assessment"
                + "\",\r\n      \"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + weight + "\"\r\n      }\r\n   },"
                //</editor-fold>

                //<editor-fold defaultstate="collapsed" desc="Pregnant">
                + "\r\n   {  \r\n      \"name\":\"Pregnant\",\r\n      "
                + "\"type\":\"Health Risk Assessment"
                + "\",\r\n      \"date\":\"2017-11-15T00:00:00+02:00\",\r\n      "
                + "\"results\":{  \r\n         \"result\":\"" + pregnant + "\"\r\n      }\r\n   "
                //</editor-fold>

                + "}\r\n]\r\n";
        this.json = json;
        return json;
    }

    public String getClientJson() {
        return json;
    }

    public void postClientJSON(String clientNumber) {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, createClientJSON());
        UUID uuid = UUID.randomUUID();
        Request request = new Request.Builder()
                .url("http://multiplywsdev.multiply.co.za/multiply-measurement-impl/rest/measurements/provider/Clicks/clientNumber/" + clientNumber)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("Cache-Control", "no-cache")
                .addHeader("Postman-Token", uuid.toString())
                .build();

        System.out.println(uuid + "    " + request.toString());

        try {
            Response response = client.newCall(request).execute();
            System.out.println("Successful execution");
        } catch (IOException ex) {
            System.out.println("Something went wrong in the PostmanSend Class");
        }

        System.out.println(json);
    }

    @Override
    public String toString() {
        return "PostmanSend{" + "smoker=" + smoker + ", pregnant=" + pregnant + ", height=" + height + ", bps=" + bps + ", bpd=" + bpd + ", waist=" + waist + ", glu_rand=" + glu_rand + ", chol=" + chol + ", weight=" + weight + "}\n";
    }

}
