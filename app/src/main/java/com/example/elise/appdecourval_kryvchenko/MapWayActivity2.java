package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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



        //On récupère le départ et l'arrivée
        //final String editDeparture = getIntent().getStringExtra("departure");
        //final String editArrival = getIntent().getStringExtra("arrival");
        final Boolean here = getIntent().getBooleanExtra("here", true);

        //new ItineraryTaskHere(this, gMap, lat,lng, editArrival).execute();

        if (here==true) {       //Maps3
            Bundle extras1 = getIntent().getExtras();
            String destination = getIntent().getStringExtra("destination");
            double latD = getLatFromAddress(destination) ;
            double lngD = getLngFromAddress(destination) ;
            double latA = extras1.getDouble("latitude");
            double lngA = extras1.getDouble("longitude");

           /* final Bundle bundleLatA = new Bundle();
            bundleLatA.putDouble("latitude", latA);
            final Bundle bundleLngA = new Bundle();
            bundleLngA.putDouble("longitude", lngA);
            final Bundle bundleLatD = new Bundle();
            bundleLatD.putDouble("latitude", latD);
            final Bundle bundleLngD = new Bundle();
            bundleLngD.putDouble("longitudeD", lngD);*/

            Location locationD = new Location("point D");
            locationD.setLatitude(latD);
            locationD.setLongitude(lngD);
            Location locationA = new Location("point A");
            locationA.setLatitude(latA);
            locationA.setLongitude(lngA);
            distance = locationD.distanceTo(locationA);


            Toast toast = Toast.makeText(getApplicationContext(),"Lat !"+latD, Toast.LENGTH_LONG);
            new ItineraryTaskHere(this, gMap, latA,lngA, destination).execute();

        }
        else {                  //AddDepart
            String destination = getIntent().getStringExtra("destination");
            String departure = getIntent().getStringExtra("departure");
            new ItineraryTask(this, gMap, departure, destination).execute();
            gMap.addMarker(null);
        }


        buttonGoTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*final Bundle bundle = new Bundle();
                bundle.putString("destination", editDestination.getText().toString());
                final Intent intent = new Intent(getApplicationContext(), AddDepartActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);*/

                final Bundle bundle = new Bundle();
                bundle.putFloat("distance", distance);
                Intent intent = new Intent(MapWayActivity2.this, TrainingExoActivity.class);
                intent.putExtra("distance",distance);
                startActivity(intent);
                //final Bundle bundleDistance = new Bundle();
                //bundleDistance.putFloat("distance", distance);
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


