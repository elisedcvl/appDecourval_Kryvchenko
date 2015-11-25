package com.example.elise.appdecourval_kryvchenko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        final Button FreeTrailButton = (Button) findViewById(R.id.FreeTrailButton);
        final Button PredefinedRouteButton =(Button)  findViewById(R.id.PredefinedRouteButton);
        final Button StrengthTrainingButton = (Button) findViewById(R.id.StrengthTrainingButton);

        FreeTrailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, FreeTrailActivity.class);
                startActivity(intent);
            }
        } );

        PredefinedRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, PredefinedRouteActivity.class);
                startActivity(intent);
            }
        } );

        StrengthTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, StrenghtTrainingActivity.class);
                startActivity(intent);
            }
        });




    }
}
