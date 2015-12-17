package com.example.elise.appdecourval_kryvchenko;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.sql.SQLException;

public class TestBDUser2 extends AppCompatActivity {

     private UserDbAdaptater dbHelper;
    private SimpleCursorAdapter dataAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_bduser2);
        /*dbHelper = new UserDbAdaptater(this);
        try {
            dbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        //Clean all data
        //dbHelper.deleteAllUsers();
        //Add some data
        //dbHelper.insertSomeUSer();

        //Generate ListView from SQLite Database
        //displayListView();

    }

    private void displayListView() {


        Cursor cursor = dbHelper.fetchAllCountries();

        // The desired columns to be bound
        String[] columns = new String[] {
                UserDbAdaptater.KEY_ROWID,
                UserDbAdaptater.KEY_SEXE,
                UserDbAdaptater.KEY_AGE,
                UserDbAdaptater.KEY_SPORT
        };

        // the XML defined views which the data will be bound to
        int[] to = new int[] {
                R.id.id,
                R.id.sexe,
                R.id.age,
                R.id.sport,
        };

        // create the adapter using the cursor pointing to the desired data
        //as well as the layout information
        dataAdapter = new SimpleCursorAdapter(
                this, R.layout.layout_user_info,
                cursor,
                columns,
                to,
                0);

        ListView listView = (ListView) findViewById(R.id.listView1);
        // Assign adapter to ListView
        listView.setAdapter(dataAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView listView, View view,
                                    int position, long id) {
                // Get the cursor, positioned to the corresponding row in the result set
                Cursor cursor = (Cursor) listView.getItemAtPosition(position);

                // Get the state's capital from this row in the database.
                String countryCode =
                        cursor.getString(cursor.getColumnIndexOrThrow("code"));
                Toast.makeText(getApplicationContext(),
                        countryCode, Toast.LENGTH_SHORT).show();

            }
        });

        /*EditText myFilter = (EditText) findViewById(R.id.myFilter);
        myFilter.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                dataAdapter.getFilter().filter(s.toString());
            }
        });

        dataAdapter.setFilterQueryProvider(new FilterQueryProvider() {
            public Cursor runQuery(CharSequence constraint) {
                try {
                    return dbHelper.fetchUserByName(constraint.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });*/

    }
    }
