package com.example.mysport;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText    email, motDePasse;
    Button      buttonConnexion, buttonInscription;
    DBAccessor dbAccessor = new DBAccessor();
    FetchUserJob fetchUserJob   = new FetchUserJob(MainActivity.this, Accueil.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email       = findViewById(R.id.editText3);
        motDePasse  = findViewById(R.id.editText4);
        buttonConnexion     = findViewById(R.id.button);
        buttonInscription   = findViewById(R.id.button2);

        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user_email       = email.getText().toString();
                String user_password    = motDePasse.getText().toString();
                dbAccessor.fetchUser(user_email, user_password, fetchUserJob);
            }
        });

        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Inscription.class));
            }
        });
    }
}