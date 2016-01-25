package com.bchs_solutions.android.autoinfoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class KlimatisierungActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private static int int_setTempPos = 6;
    private static String String_setTemp = "20 °C";
    private static int int_actualTemp = 20;
    private static boolean active = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klimatisierung);

        // Temperatur-Spinner wird mit Werten befüllt
        Spinner spinner = (Spinner) findViewById(R.id.spinner_temperature);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.temperature_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setSelection(int_setTempPos, true);
        spinner.setOnItemSelectedListener(this);

        TextView temperature = (TextView) findViewById(R.id.textView_temperature);
        temperature.setText(int_actualTemp + " °C");

        // Heizung de-/aktivieren
        ToggleButton toggleButton_heizen = (ToggleButton) findViewById(R.id.toggleButton_heizen);
        toggleButton_heizen.setChecked(active);
        toggleButton_heizen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                active = isChecked;
                if(isChecked) {
                    makeToast("Klimatisierung aktiviert:\n" + getSelectedTemp());
                } else {
                    makeToast("Klimatisierung deaktiviert");
                }
            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        int_setTempPos = pos;
        String_setTemp = parent.getItemAtPosition(pos).toString();
        if (active) {
            makeToast(getSelectedTemp());
        } else {
            ToggleButton toggleButton_heizen = (ToggleButton) findViewById(R.id.toggleButton_heizen);
            toggleButton_heizen.setChecked(true);
        }
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public static String getSelectedTemp(){
        if(active){
            if((int_setTempPos+14) > int_actualTemp){
                return "heizen, " + (int_setTempPos+14) + " °C";
            }
            else if ((int_setTempPos+14) < int_actualTemp){
                return "kühlen, " + (int_setTempPos+14) + " °C";
            }
            else {
                return "gewünschte Temperatur erreicht";
            }
        }
        else {
            return "AUS";
        }
    }

    private void makeToast(String message){
        Toast toast = Toast.makeText(this,  message, Toast.LENGTH_SHORT);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if(v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }
}
