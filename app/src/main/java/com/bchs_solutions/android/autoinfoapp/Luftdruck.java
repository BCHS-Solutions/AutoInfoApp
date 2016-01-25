package com.bchs_solutions.android.autoinfoapp;

/**
 * Created by wowa on 21.01.16.
 */
public class Luftdruck {
    private static Luftdruck INSTANCE;
    private static double[] reifen;

    private Luftdruck(){
        // VL, VR, HL, HR
        reifen = new double[]{2.0, 1.8, 2.3, 2.0};
    }

    public static Luftdruck getInstance(){
        if(INSTANCE == null){
            INSTANCE = new Luftdruck();
        }
        return INSTANCE;
    }

    public double[] getLufdruck(){
        return reifen;
    }
}
