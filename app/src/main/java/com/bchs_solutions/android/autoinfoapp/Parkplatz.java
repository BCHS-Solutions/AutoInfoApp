package com.bchs_solutions.android.autoinfoapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.util.Date;

public class Parkplatz {
    private static LatLng POSITION;
    private static Date DATETIME;

    private Parkplatz(){
        POSITION = null;
        DATETIME = null;
    };

    protected LatLng getPosition(){
        return POSITION;
    }

    protected Date getTime() {
        return DATETIME;
    }

    protected void setPositionAndTime(LatLng position, Date time){
        POSITION = position;
        DATETIME = time;
    }
}
