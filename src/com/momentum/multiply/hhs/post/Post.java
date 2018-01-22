/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.momentum.multiply.hhs.post;

import java.io.*;
import java.util.*;

/**
 *
 * @author zachristophers
 */
public class Post {

    public static void createFile() throws FileNotFoundException, IOException {
        File results = new File("C:\\Users\\zachristophers\\Documents\\Results\\Empty Numbers\\Results\\My Empty Nums.csv");
        File resultsFile = new File("C:\\Users\\zachristophers\\Documents\\Results\\Empty Numbers\\Results\\SpartaDuds.csv");
        Scanner scanFile = new Scanner(results);
        ClientMeasurementGeneration pms;
        Random random = new Random();
        PrintWriter fw = new PrintWriter(new FileWriter(resultsFile));
        while(scanFile.hasNextLine()){
            pms = new ClientMeasurementGeneration();            
            pms.setBpd((int)(Math.random()*40) + 60);
            pms.setBps((int) (Math.random()*80) + 60);
            pms.setChol(0);
            pms.setGlu_rand((Math.round((4.0*Math.random() + 2.0f)*10))/10);
            pms.setHeight((int)(Math.random()*110)+ 110);
            pms.setPregnant(false);
            pms.setSmoker(random.nextBoolean());
            pms.setWaist((int)(Math.random()*90) + 65);
            pms.setWeight((float) (Math.random()* 80f + 45f));
            String tmp = scanFile.nextLine().substring(1,14);
            pms.postClientJSON(tmp);
            System.out.println(pms.toString() + tmp);
            fw.write(pms.toString() + "," + tmp + "\n");
            System.out.println("");            
            System.out.println("");
        }
        fw.close();
        scanFile.close();
    }
}
