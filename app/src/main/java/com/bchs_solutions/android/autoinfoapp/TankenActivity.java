package com.bchs_solutions.android.autoinfoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.Date;
import java.util.Random;

public class TankenActivity extends AppCompatActivity {
    private GraphView graph;
    private Button btnTankenReset;
    private Button btnTankenNeuerEintrag;

    private BarGraphSeries<DataPoint> series;
    private final static Date[] mockDate = {
            new Date(2015,10,15),
            new Date(2015,10,30),
            new Date(2015,11,15),
            new Date(2015,11,30),
            new Date(2015,12,15),
            new Date(2015,12,30)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanken);

        graph = (GraphView) findViewById(R.id.graph);
        btnTankenReset = (Button) findViewById(R.id.btnTankenReset);
        btnTankenNeuerEintrag = (Button) findViewById(R.id.btnTankenNeuerEintrag);

        series = new BarGraphSeries<>();
            Random random = new Random();
        //TODO händisch eintragen!
        for(Date date : mockDate) {
            series.appendData(new DataPoint(date, random.nextDouble()*30+20),true,5);
        }
        graph.addSeries(series);

        btnTankenReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                graph.removeAllSeries();
            }
        });

        btnTankenNeuerEintrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                series.appendData(new DataPoint(new Date(2016,01,15),35),false,5);
                graph.addSeries(series);
            }
        });
    }
}

