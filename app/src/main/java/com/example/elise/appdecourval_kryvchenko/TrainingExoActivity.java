package com.example.elise.appdecourval_kryvchenko;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TrainingExoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_exo);

        Bundle extras1 = getIntent().getExtras();
        Float distance = getIntent().getFloatExtra("distance", 3);
        String dist = Float.toString(distance);

        TextView textViewDistance = (TextView) findViewById(R.id.textViewDistance);
        textViewDistance.setText(dist);
    }
}
