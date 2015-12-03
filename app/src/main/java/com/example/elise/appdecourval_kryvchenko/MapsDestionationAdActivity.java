package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MapsDestionationAdActivity extends Activity {

    private EditText editDeparture;
    private EditText editArrival;
    private Button buttonGoResearch;
    private int here;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_arrival_destionation);

        Bundle extras = getIntent().getExtras();
        //Boolean here = extras.getBoolean("here");

       // here = herep;

        //On récupère les composants graphiques
        editDeparture = (EditText) findViewById(R.id.editDepart);
        editArrival = (EditText) findViewById(R.id.editArrivee);
        buttonGoResearch = (Button) findViewById(R.id.buttonGoResearch);

        buttonGoResearch.setOnClickListener(new View.OnClickListener() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onClick(final View v) {
                if("".equals(editDeparture.getText().toString().trim())) {
                    Toast.makeText(MapsDestionationAdActivity.this, "Please enter a starting point", Toast.LENGTH_SHORT).show();
                }
                else if("".equals(editArrival.getText().toString().trim())) {
                    Toast.makeText(MapsDestionationAdActivity.this, "Please enter a end point", Toast.LENGTH_SHORT).show();
                }
                else {
                    //On transmet les données à l'activité suivante
                    final Intent intent = new Intent(MapsDestionationAdActivity.this, MapWayActivity.class);
                    intent.putExtra("departure", editDeparture.getText().toString().trim());
                    intent.putExtra("arrival", editArrival.getText().toString().trim());
                   // intent.putExtra("here", here);

                    MapsDestionationAdActivity.this.startActivity(intent);
                }
            }
        });
    }
}
