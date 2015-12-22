package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

import java.io.IOException;
import java.util.List;


public class MapWayActivity2 extends Activity {



    private GoogleMap gMap;
    private float distance =0;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_way2);

        final Button buttonGoTraining = (Button) findViewById(R.id.buttonGoTraining);

        //On récupère les composants graphiques
        gMap = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();


        final Boolean here = getIntent().getBooleanExtra("here", true);

        if (here==true) {       //Maps3
            Bundle extras1 = getIntent().getExtras();
            String destination = getIntent().getStringExtra("destination");
            double latD = getLatFromAddress(destination) ;
            double lngD = getLngFromAddress(destination) ;
            double latA = extras1.getDouble("latitude");
            double lngA = extras1.getDouble("longitude");

            float [] dist = new float[1];
            Location.distanceBetween(latD,lngD,latA,lngA,dist);
            distance = dist[0] * 0.000621371192f;

            new ItineraryTaskHere(this, gMap, latA,lngA, destination).execute();

        }
        else {

            String destination = getIntent().getStringExtra("destination");
            String departure = getIntent().getStringExtra("departure");
            new ItineraryTask(this, gMap, departure, destination).execute();

            double latD = getLatFromAddress(destination) ;
            double lngD = getLngFromAddress(destination) ;
            double latA = getLatFromAddress(departure);
            double lngA = getLngFromAddress(departure);

            float [] dist = new float[1];
            Location.distanceBetween(latD,lngD,latA,lngA,dist);
            distance = dist[0] * 0.000621371192f;


        }


        buttonGoTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Bundle bundle = new Bundle();
                bundle.putFloat("distance", distance);
                Intent intent = new Intent(MapWayActivity2.this, TrainingExoActivity.class);
                intent.putExtra("distance", distance);
                startActivity(intent);

            }
        });

    }

    //Latitude from Adresse
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

    //Longitude from Adresse
    public double getLngFromAddress(String strAddress){
        Geocoder coder = new Geocoder(this);
        List<Address> address;
        double lng =0;
        try {
            address = coder.getFromLocationName(strAddress,5);
            Address location=address.get(0);
            lng = location.getLatitude();
            location.getLongitude();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lng;
    }
}


