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

        final Button ProfileButton = (Button) findViewById(R.id.ProfileButton);
        final Button PredefinedRouteButton =(Button)  findViewById(R.id.PredefinedRouteButton);
        final Button FreeTrailButton =(Button)  findViewById(R.id.FreeTrailButton);
        //final Button StrengthTrainingButton = (Button) findViewById(R.id.StrengthTrainingButton);

        PredefinedRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, Maps3Activity.class);
                startActivity(intent);
            }
        } );

        FreeTrailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, FreeTrailActivity.class);
                startActivity(intent);
            }
        });



        ProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WelcomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });


    }
}
