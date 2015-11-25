package com.example.elise.appdecourval_kryvchenko;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginDisplayActivity extends AppCompatActivity  {

    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";
    private Button next_welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_display);
        next_welcome = (Button)  findViewById(R.id.next_welcome);


        Intent intent = getIntent();
        TextView loginDisplay = (TextView) findViewById(R.id.emailDisplay);
        TextView passwordDisplay = (TextView) findViewById(R.id.passwordDisplay);

        if (intent != null) {
            loginDisplay.setText(intent.getStringExtra(EXTRA_LOGIN));
            passwordDisplay.setText(intent.getStringExtra(EXTRA_PASSWORD));
        }

        next_welcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginDisplayActivity.this, WelcomeActivity.class);
                startActivity(intent);
            }
        } );
    }
}
