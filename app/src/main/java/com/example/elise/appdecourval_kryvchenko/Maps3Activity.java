package com.example.elise.appdecourval_kryvchenko;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;

import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v4.app.FragmentActivity;


public class Maps3Activity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private TextView latituteField;
    private TextView longitudeField;
    private LocationManager locationManager;
    private String provider;
    private double latg ;
    private double lngg ;
    private boolean here = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps3);

        final Button buttonGoDestination = (Button) findViewById(R.id.buttonGoTraining);
        final Button buttonGoDestinationArrival = (Button) findViewById(R.id.buttonGoTraining);
        final EditText editDestination = (EditText) findViewById(R.id.editDestination);
        final CheckBox checkBoxHere = (CheckBox) findViewById(R.id.checkBoxHere);

        // Get the location manager
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Define the criteria how to select the locatioin provider -> use
        // default
        Criteria criteria = new Criteria();
        provider = locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        // Initialize the location fields
        if (location != null) {
            System.out.println("Provider " + provider + " has been selected.");
            onLocationChanged(location);
        } /*else {
            latituteField.setText("Location not available");
            longitudeField.setText("Location not available");
        }*/

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buttonGoDestination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxHere.isChecked()) {
                    if("".equals(editDestination.getText().toString().trim())) {
                        Toast.makeText(Maps3Activity.this, "Please enter a destination", Toast.LENGTH_SHORT).show();
                    }
                    here = true;
                    final Bundle bundle = new Bundle();
                    bundle.putString("destination", editDestination.getText().toString());
                    final Bundle bundleLat = new Bundle();
                    bundleLat.putDouble("latitude", latg);
                    final Bundle bundleLng = new Bundle();
                    bundleLng.putDouble("longitude", lngg);
                    final Bundle bundleHere = new Bundle();
                    bundleHere.putBoolean("here", here);
                    final Intent intent = new Intent(getApplicationContext(), MapWayActivity2.class);
                    intent.putExtras(bundle);
                    intent.putExtras(bundleLat);
                    intent.putExtras(bundleLng);
                    intent.putExtras(bundleHere);
                    startActivity(intent);
                } else {
                    if("".equals(editDestination.getText().toString().trim())) {
                        Toast.makeText(Maps3Activity.this, "Please enter a destination", Toast.LENGTH_SHORT).show();
                    }
                    final Bundle bundle = new Bundle();
                    bundle.putString("destination", editDestination.getText().toString());
                    final Intent intent = new Intent(getApplicationContext(), AddDepartActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                 }
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng geo = new LatLng(latg, lngg);
        map.addMarker(new MarkerOptions()
                .position(geo).title("Marker in your position"));
        map.moveCamera(CameraUpdateFactory.newLatLng(geo));
    }

    /* Request updates at startup */
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(provider, 400, 1, this);
    }

    /* Remove the locationlistener updates when Activity is paused */
    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        double lat = (location.getLatitude());
        double lng = (location.getLongitude());
        //latituteField.setText(String.valueOf(lat));
        //longitudeField.setText(String.valueOf(lng));
        latg = lat;
        lngg = lng ;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(this, "Enabled new provider " + provider,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(this, "Disabled provider " + provider,
                Toast.LENGTH_SHORT).show();
    }


}

