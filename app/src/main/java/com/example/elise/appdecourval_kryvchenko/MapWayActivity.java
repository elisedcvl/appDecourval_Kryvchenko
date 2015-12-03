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
        final String editDeparture = getIntent().getStringExtra("departure");
        final String editArrival = getIntent().getStringExtra("arrival");
        Bundle extras = getIntent().getExtras();
        //final Boolean here = extras.getBoolean("here");

        new ItineraryTask(this, gMap, editDeparture, editArrival).execute();

       /* //TODO: Appeler une tâche asynchrone qui affiche l'itinéraire sur la carte
        if (here==true) {
            new ItineraryTask(this, gMap, editDeparture, editArrival).execute();

        }
        else {
            Bundle extras1 = getIntent().getExtras();
            double lat = extras1.getDouble("lat");
            double lng = extras1.getDouble("lng");
            new ItineraryTaskHere(this, gMap, lat,lng, editArrival).execute();
        }*/

    }
}

