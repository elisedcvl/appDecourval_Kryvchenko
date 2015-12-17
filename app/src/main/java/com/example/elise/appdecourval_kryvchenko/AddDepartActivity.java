package com.example.elise.appdecourval_kryvchenko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddDepartActivity extends AppCompatActivity {

    private boolean here = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_depart);


        final String destination = getIntent().getStringExtra("destination");

        TextView destinationView = (TextView) findViewById(R.id.ArrivalView);
        destinationView.setText(destination);



        final EditText editDepart = (EditText) findViewById(R.id.editDepart);
        final String depart = editDepart.getText().toString();


        final Button buttonGo = (Button) findViewById(R.id.buttonGoResearch);


        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if("".equals(editDepart.getText().toString().trim())) {
                    Toast.makeText(AddDepartActivity.this, "Please enter a destination", Toast.LENGTH_SHORT).show();
                }

                final Bundle bundleDep = new Bundle();
                bundleDep.putString("departure", editDepart.getText().toString());
                final Bundle bundleDest = new Bundle();
                bundleDest.putString("destination", destination);
                final Bundle bundleHere = new Bundle();
                bundleHere.putBoolean("here", here);
                final Intent intent = new Intent(getApplicationContext(), MapWayActivity.class);
                intent.putExtras(bundleDep);
                intent.putExtras(bundleDest);
                intent.putExtras(bundleHere);
                startActivity(intent);
            }
        });

    }


}
