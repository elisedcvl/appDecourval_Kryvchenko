package com.example.elise.appdecourval_kryvchenko;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

public class TrainingExoActivity extends AppCompatActivity {

    Chronometer focus;
    Button start, stop, restart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_exo);

        Bundle extras1 = getIntent().getExtras();
        Float distance = getIntent().getFloatExtra("distance", 0);
        String dist = Float.toString(distance);
        Float q1 = 0.083F;
        Float q2 = 0.16F;
        Float q3 = 0.25F;
        Float d1 = distance * q2 ;
        String dist1 = Float.toString(d1);
        Float d2 = distance * q2;
        String dist2 = Float.toString(d2);
        Float d3 = distance * q1;
        String dist3 = Float.toString(d3);
        Float d4 = distance * q3;
        String dist4 = Float.toString(d4);
        Float d5 = distance * q2;
        String dist5 = Float.toString(d5);
        Float d6 = distance * q2;
        String dist6 = Float.toString(d6);


        TextView textViewDistance = (TextView) findViewById(R.id.textViewDistance);
        textViewDistance.setText(dist);
        TextView textViewD1 = (TextView) findViewById(R.id.d1);
        textViewD1.setText(dist1);
        TextView textViewD2 = (TextView) findViewById(R.id.d2);
        textViewD2.setText(dist2);
        TextView textViewD3 = (TextView) findViewById(R.id.d3);
        textViewD3.setText(dist3);
        TextView textViewD4 = (TextView) findViewById(R.id.d4);
        textViewD4.setText(dist4);
        TextView textViewD5 = (TextView) findViewById(R.id.d5);
        textViewD5.setText(dist5);
        TextView textViewD6 = (TextView) findViewById(R.id.d6);
        textViewD6.setText(dist6);


        //Chronometre
        start = (Button) findViewById(R.id.startC);
        stop = (Button) findViewById(R.id.stopC);
        restart = (Button) findViewById(R.id.restartC);

        focus = (Chronometer) findViewById(R.id.chronometer1);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                focus.start();
            }
        });


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                focus.stop();
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                focus.setBase(SystemClock.elapsedRealtime());
            }
        });

    }
}
