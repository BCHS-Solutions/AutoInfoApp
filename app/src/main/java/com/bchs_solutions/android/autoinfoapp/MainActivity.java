package com.bchs_solutions.android.autoinfoapp;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Klimatisierung-Status anzeigen
        TextView textView_klimatisierungStatus = (TextView) findViewById(R.id.textView_klimatisierungStatus);
        textView_klimatisierungStatus.setText(KlimatisierungActivity.getSelectedTemp());

        // Tankanzeige
        changeFuelGaugeColor();

        TextView tvMainLuftdruckMessage = (TextView) findViewById(R.id.tvMainLuftdruckMessage);
        for(double druck : Luftdruck.getInstance().getLufdruck()){
            if (druck != 2.0){
                tvMainLuftdruckMessage.setText("Lufdruck überprüfen!");
                tvMainLuftdruckMessage.setTextColor(Color.RED);
                return;
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();    // Zeichnet die GUI mit neuen Werten.
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.row_klimatisierung:
                startActivity(new Intent(this, KlimatisierungActivity.class));
                break;
            case R.id.row_tanken:
                startActivity(new Intent(this, TankenActivity.class));
                break;
            case R.id.row_luftdruck:
                startActivity(new Intent(this, LuftdruckActivity.class));
                break;
            case R.id.btn_maps:
                startActivity(new Intent(this, ParkenActivity.class));
                break;
        }
    }

    // Tankanzeige abhängig vom Füllstand färben. Unter 10%: rot, unter 30% gelb, sonst default.
    private void changeFuelGaugeColor() {
        ProgressBar progressBar_tankanzeige = (ProgressBar) findViewById(R.id.progressBar_tankanzeige);

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_enabled}, // enabled
                new int[] {-android.R.attr.state_enabled}, // disabled
                new int[] {-android.R.attr.state_checked}, // unchecked
                new int[] { android.R.attr.state_pressed}  // pressed
        };
        int[] colorsRED = new int[] {
                Color.RED, Color.RED, Color.RED, Color.RED
        };
        int[] colorsYELLOW = new int[] {
                Color.YELLOW, Color.YELLOW, Color.YELLOW, Color.YELLOW
        };

        if (progressBar_tankanzeige.getProgress() < 10) {
            ColorStateList myList = new ColorStateList(states, colorsRED);
            progressBar_tankanzeige.setProgressTintList(myList);
        } else if (progressBar_tankanzeige.getProgress() < 30) {
            ColorStateList myList = new ColorStateList(states, colorsYELLOW);
            progressBar_tankanzeige.setProgressTintList(myList);
        }
    }
}
