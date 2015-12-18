package com.example.elise.appdecourval_kryvchenko;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;


import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import android.database.*;

import org.w3c.dom.Comment;

public class ProfileActivity extends Activity {

    //private UserDataSource datasource;

   DatabaseHelperUser userBdd;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /*datasource = new UserDataSource(this);
        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //Création d'une instance de ma classe UserBDD
        //UserBDD userBdd = new UserBDD(this);
        userBdd = new DatabaseHelperUser(this);


        //On ouvre la base de données pour écrire dedans
        //userBdd.open();


        final Button buttonSave = (Button) findViewById(R.id.buttonSave);
        final EditText editTextAge = (EditText) findViewById(R.id.editTextAge);
        final RadioButton radioButtonWooman = (RadioButton) findViewById(R.id.radioButtonWooman);
        final RadioButton radioButtonMan = (RadioButton) findViewById(R.id.radioButtonMan);
        final RadioButton radioButtonSportYes = (RadioButton) findViewById(R.id.radioButtonSportYes);
        final RadioButton radioButtonSportNo = (RadioButton) findViewById(R.id.radioButtonSportNo);

                buttonSave.setOnClickListener(new View.OnClickListener() {
            int id = 1;
            int sexe = 0;
            int age = 20;
            //int age = Integer.parseInt(editTextAge.getText().toString());
            int sport = 0;
            boolean bool =false;

            public void onClick(View v) {

                if (radioButtonWooman.isChecked()) {
                    sexe = 1; // fille
                }
                if (age < 18 || age > 100) {
                    Toast.makeText(ProfileActivity.this, "Error age", Toast.LENGTH_SHORT).show();
                }
                if (radioButtonSportYes.isChecked()) {
                    sport = 1;
                }

                //datasource.createUser(sexe, age, sport);

                Intent intent = new Intent(ProfileActivity.this, TestBDUser.class);
                startActivity(intent);
                bool = userBdd.insertData(id,sexe,age,sport);
                if (bool = true) {Toast toast = Toast.makeText(getApplicationContext(),
                        "Insert Data !", Toast.LENGTH_LONG);
                    toast.show();}
                int age = userBdd.getAge(1);
                //Toast toast = Toast.makeText(getApplicationContext(),age, Toast.LENGTH_LONG);
            }


        });


    }
}
