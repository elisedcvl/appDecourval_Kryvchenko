package com.example.elise.appdecourval_kryvchenko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PredefinedRouteActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predefined_route);

        final Button maps1Button =(Button)  findViewById(R.id.maps1Button);
        final Button maps2Button =(Button)  findViewById(R.id.maps2Button);

        maps1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PredefinedRouteActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });

        maps2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PredefinedRouteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } );


    }
}
