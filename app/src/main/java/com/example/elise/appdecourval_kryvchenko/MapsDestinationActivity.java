package com.example.elise.appdecourval_kryvchenko;

import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsDestinationActivity extends FragmentActivity implements OnMapReadyCallback {

    private double latg;
    private double lngg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Double lat = extras.getDouble("latitude");
            Double lng = extras.getDouble("longitude");
            String destination = extras.getString("destination");
            latg = lat;
            lngg = lng;
        } else {
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "Error localisation !", Toast.LENGTH_LONG);
            toast.show();
        }
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(latg, lngg);
        map.addMarker(new MarkerOptions()
                .position(sydney).title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


}
