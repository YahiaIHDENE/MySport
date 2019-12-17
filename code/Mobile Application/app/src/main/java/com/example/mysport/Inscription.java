package com.example.mysport;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Inscription extends AppCompatActivity {

    EditText nom, prenom, email, motDePasse, confirmationMotDePasse, tel;
    Button btn;
    User user;
    DBAccessor dbAccessor = new DBAccessor();
    AddUserJob addUserJob = new AddUserJob(Inscription.this, MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.Email);
        tel = findViewById(R.id.tel);
        motDePasse = findViewById(R.id.Mdp);
        confirmationMotDePasse = findViewById(R.id.confirmerMdp);
        btn = findViewById(R.id.btnInscription);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (motDePasse.getText().toString().equals(confirmationMotDePasse.getText().toString())){
                    user = new User(nom.getText().toString(),
                            prenom.getText().toString(),
                            email.getText().toString(),
                            tel.getText().toString(),
                            motDePasse.getText().toString());

                    dbAccessor.addUser(user, addUserJob);
                } else {
                    Toast.makeText(Inscription.this, "Please check your passwords", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
