package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;

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
            double lat = extras1.getDouble("latitude");
            double lng = extras1.getDouble("longitude");
            new ItineraryTaskHere(this, gMap, lat,lng, destination).execute();
        }
        else {                  //AddDepart
            String destination = getIntent().getStringExtra("destination");
            String departure = getIntent().getStringExtra("departure");
            new ItineraryTask(this, gMap, departure, destination).execute();
        }

    }
}

