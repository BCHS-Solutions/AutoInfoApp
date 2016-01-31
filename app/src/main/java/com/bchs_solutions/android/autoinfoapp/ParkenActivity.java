package com.bchs_solutions.android.autoinfoapp;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class ParkenActivity extends FragmentActivity implements OnMapReadyCallback {

    /**
     * HS-Fulda
     * Longitude: 9,6859732
     * Latitude: 50,5650086
     *
     * Aldi-Parkplatz
     * Longitude: 9,6887569
     * Latitude: 50,5649176
     */
    private GoogleMap mMap;
    private final static int LOCATION_REQUEST = 1001;
    private static LatLng parkedLocation;
    protected static Marker markerParked;
    private static Date parkedDateTime;
    private static LocationManager locationManager;
    private Button btnReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parken);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        btnReset = (Button) findViewById(R.id.btnReset);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setZoomControlsEnabled(true);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            String permissions[] = {"android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_COARSE_LOCATION"};
            ActivityCompat.requestPermissions(this, permissions, LOCATION_REQUEST);
        } else {
            mMap.setMyLocationEnabled(true);
        }

        // Add a marker in Sydney and move the camera
        if (parkedLocation != null) {
            btnReset.setVisibility(View.VISIBLE);
            DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());
            DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(getApplicationContext());
            markerParked = mMap.addMarker(new MarkerOptions().position(parkedLocation).title("geparkt am: " + dateFormat.format(parkedDateTime) + " um " + timeFormat.format(parkedDateTime)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(parkedLocation, 18));
        } else {
            //TODO show my actual position
            btnReset.setVisibility(View.GONE);
            LatLng latLngAldi = new LatLng(50.5649176, 9.6887569);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLngAldi, 18));
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode){
            case LOCATION_REQUEST:
                for (int result : grantResults){
                    if (result == RESULT_OK){
                        mMap.setMyLocationEnabled(true);
                        return;
                    }
                }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_parken:
                parken();
                break;
            case R.id.btnReset:
                removeMarker();
                break;
        }
    }

    private void removeMarker() {
        parkedLocation = null;
        markerParked.remove();
        btnReset.setVisibility(View.GONE);
        Toast.makeText(this, "letzte Position wurde entfernt", Toast.LENGTH_LONG).show();
    }

    private void parken(){
        //TODO position auswählen und speichern, textfeld für Eingabe einer Parkplatznummer oder anderer Merkmale. Eventuell mit Bild?
        parkedDateTime = Calendar.getInstance().getTime();
        mMap.addMarker(new MarkerOptions().position(mMap.getCameraPosition().target));
        parkedLocation = mMap.getCameraPosition().target;

        Toast toast = Toast.makeText(this,"Position erfolgreich gespeichert.\nBis später!", Toast.LENGTH_LONG);
        TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
        if(v != null) v.setGravity(Gravity.CENTER);
        toast.show();
    }

}
