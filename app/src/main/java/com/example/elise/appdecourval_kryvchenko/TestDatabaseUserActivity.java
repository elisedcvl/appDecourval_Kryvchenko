package com.example.elise.appdecourval_kryvchenko;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.List;

public class TestDatabaseUserActivity extends ListActivity {

    private UserDataSource datasource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database_user);


        datasource = new UserDataSource(this);
        try {
            datasource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<User> values = datasource.getAllUsers();

        ArrayAdapter<User> adapter = new ArrayAdapter<User>(this,android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
    }
}
