package com.bchs_solutions.android.autoinfoapp;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LuftdruckActivity extends AppCompatActivity {
    private ImageView mImageView;
    private int drawableCarTopView = R.drawable.buick;
    private TextView tvLuftdruckMessage;
    private TextView[] druckViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luftdruck);

        mImageView = (ImageView) findViewById(R.id.ivCarTopView);
        mImageView.setImageResource(drawableCarTopView);

        tvLuftdruckMessage = (TextView) findViewById(R.id.tvLuftdruckMessage);
        druckViews = new TextView[]{
                (TextView) findViewById(R.id.tvDruckVorneLinks),
                (TextView) findViewById(R.id.tvDruckVorneRechts),
                (TextView) findViewById(R.id.tvDruckHintenLinks),
                (TextView) findViewById(R.id.tvDruckHintenRechts)
        };

        setLuftdruckText();
        }

    private void setLuftdruckText() {
        double[] luftDruck = Luftdruck.getInstance().getLufdruck();

        for(int i=0; i<druckViews.length; i++){
            druckViews[i].setText(String.valueOf(luftDruck[i]));
            if(luftDruck[i]!=2.0){
                druckViews[i].setTextColor(Color.RED);
                tvLuftdruckMessage.setText("Luftdruck überprüfen!");
                tvLuftdruckMessage.setTextColor(Color.RED);
            }
        }
    }
}
