package com.bchs_solutions.android.autoinfoapp;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TimePicker;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class TankenActivity extends FragmentActivity {
    private Button btnTankenReset;
    private Button btnTankenNeuerEintrag;
    private ListView listViewTanken;

    private static ArrayList<Date> mockDate = new ArrayList<>();
    private static ArrayList<Double> mockLiter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tanken);

        // Mock-Daten generieren
        mockDate.add(new Date(2015, 10, 15));
        mockLiter.add(30.9);
        mockDate.add(new Date(2015,10,30));
        mockLiter.add(23.4);
        mockDate.add(new Date(2015,11,15));
        mockLiter.add(33.8);
        mockDate.add(new Date(2015,11,30));
        mockLiter.add(30.5);
        mockDate.add(new Date(2015,12,15));
        mockLiter.add(27.9);
        mockDate.add(new Date(2015,12,30));
        mockLiter.add(42.9);
        mockDate.add(new Date(2016,01,15));
        mockLiter.add(33.2);

        // Buttons
        btnTankenReset = (Button) findViewById(R.id.btnTankenReset);
        btnTankenNeuerEintrag = (Button) findViewById(R.id.btnTankenNeuerEintrag);
        btnTankenReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mockDate.clear();
            }
        });
        btnTankenNeuerEintrag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });

        // ListView
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,mockDate);
        listViewTanken = (ListView) findViewById(R.id.listViewTanken);
        listViewTanken.removeAllViews();
        listViewTanken.setAdapter(arrayAdapter);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {

            //TODO
            mockDate.add(new Date(year,month,day));
        }
    }

    public static class LiterangabeDialogFragment extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the Builder class for convenient dialog construction
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setMessage("MESSAGE!!!!")
                    .setPositiveButton("Speichern", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // FIRE ZE MISSILES!
                        }
                    })
                    .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                        }
                    });
            // Create the AlertDialog object and return it
            return builder.create();
        }
    }
}

