package com.example.elise.appdecourval_kryvchenko;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, View.OnClickListener{

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;
    private GoogleApiClient client;
    final String EXTRA_LOGIN = "user_login";
    final String EXTRA_PASSWORD = "user_password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final EditText login = (EditText) findViewById(R.id.userEmail);
        final EditText pass = (EditText) findViewById(R.id.userPassword);
        final Button loginButton = (Button) findViewById(R.id.connect);


        findViewById(R.id.button_sign_in).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .build();
        client = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String loginTxt = login.getText().toString();
                final String passTxt = pass.getText().toString();
                // On déclare le pattern que l’on doit vérifier
                Pattern p = Pattern.compile(".+@.+\\.[a-z]+");
                // On déclare un matcher, qui comparera le pattern avec la string passée en argument
                Matcher m = p.matcher(loginTxt);
                // Si l’adresse mail saisie ne correspond au format d’une adresse mail on un affiche un message à l'utilisateur
                if (!m.matches()) {
                    // Toast est une classe fournie par le SDK Androi pour afficher les messages (indications) à l'intention de
                    // l'utilisateur. Ces messages ne possédent pas d'interaction avec l'utilisateur Le premier argument représente le contexte, puis
                    // le message et à la fin la durée d'affichage du Toast (constante LENGTH_SHORT ou LENGTH_LONG). Sans oublier d'appeler la méthode
                    //show pour afficher le Toast
                    Toast.makeText(MainActivity.this, R.string.email_format_error, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (loginTxt.equals("") || passTxt.equals("")) {
                    Toast.makeText(MainActivity.this,
                            R.string.email_or_password_empty, Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(MainActivity.this, LoginDisplayActivity.class);
                intent.putExtra(EXTRA_LOGIN, login.getText().toString());
                intent.putExtra(EXTRA_PASSWORD, pass.getText().toString());
                startActivity(intent);
            }
        } );

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_sign_in:
                signIn();
                break;
        }
    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi
                .getSignInIntent(client);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi
                    .getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.i(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
// display a message
            Toast toast = Toast.makeText(
                    getApplicationContext(),
                    "Sign-in OK !", Toast.LENGTH_LONG);
            toast.show();

            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            startActivity(intent);


        }
    }

    @Override
    public void onConnectionFailed(
            ConnectionResult connectionResult) {
    }



}