package com.example.mysport.ApplicationInterface;

import android.content.Intent;
import android.os.Bundle;

import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.User;
import com.example.mysport.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Inscription extends AppCompatActivity {

    EditText nom,prenom,email,motDePasse,confirmationMotDePasse,tel;
    Button btn;
    User user;
    ConnexionBDD connexionBDD;

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

                if(motDePasse.toString().equals(confirmationMotDePasse.toString())) {
                    user = new User();
                    user.setNom(nom.getText().toString());
                    user.setPrenom(prenom.getText().toString());
                    user.setMail(email.getText().toString());
                    user.setTel(tel.getText().toString());
                    user.setMotDePasse(motDePasse.getText().toString());

                    connexionBDD = new ConnexionBDDProxy();
                    connexionBDD.ajouterUtilisateur(user);

                    startActivity(new Intent(Inscription.this, MainActivity.class));

                }

                else{

                    Toast.makeText(Inscription.this,"Veuillez remplir tous les champs!",Toast.LENGTH_LONG).show();

                }

            }
        });


    }

}
