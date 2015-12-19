package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.io.IOException;
import java.util.List;

public class MapWayActivity extends Activity {

    private GoogleMap gMap;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_way);

        //On récupère les composants graphiques
        gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

        //On récupère le départ et l'arrivée
        //final String editDeparture = getIntent().getStringExtra("departure");
        //final String editArrival = getIntent().getStringExtra("arrival");
        final Boolean here = getIntent().getBooleanExtra("here", true);

        //new ItineraryTaskHere(this, gMap, lat,lng, editArrival).execute();

        if (here==true) {       //Maps3
            Bundle extras1 = getIntent().getExtras();
            String destination = getIntent().getStringExtra("destination");
            double latD = getLatFromAddress(destination) ;
            double latA = extras1.getDouble("latitude");
            double lngA = extras1.getDouble("longitude");


            Toast toast = Toast.makeText(getApplicationContext(),"Lat !"+latD, Toast.LENGTH_LONG);
            new ItineraryTaskHere(this, gMap, latA,lngA, destination).execute();

        }
        else {                  //AddDepart
            String destination = getIntent().getStringExtra("destination");
            String departure = getIntent().getStringExtra("departure");
            new ItineraryTask(this, gMap, departure, destination).execute();
            gMap.addMarker(null);
        }


    }

    public double getLatFromAddress(String strAddress){

        Geocoder coder = new Geocoder(this);
        List<Address> address;
        double lat =0;

        try {
            address = coder.getFromLocationName(strAddress,5);
            Address location=address.get(0);
            lat = location.getLatitude();
            location.getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lat;
    }
}

