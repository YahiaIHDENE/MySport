package com.example.mysport.ApplicationInterface;

import android.content.Intent;
import android.os.Bundle;

import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.User;
import com.example.mysport.R;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button buttonConnexion,buttonInscription;
    EditText email,motDePasse;
    User user;
    public static int user_id;
    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonConnexion = findViewById(R.id.btnConnexion);
        buttonConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Accueil.class));
            /*    email = findViewById(R.id.mail);
                motDePasse = findViewById(R.id.Mdp);

                user.setMail(email.getText().toString());
                user.setMotDePasse(motDePasse.getText().toString());
                connexionBDD = new ConnexionBDDProxy();

                boolean userExist = connexionBDD.checkUser(user);

                if(userExist){
                    user_id = connexionBDD.getIdUser(user);
                    startActivity(new Intent(MainActivity.this, Accueil.class));
                }

                else{
                    Toast.makeText(MainActivity.this,"Veuillez d'abord vous inscrire! ",Toast.LENGTH_LONG).show();
                }*/
            }
        });

        buttonInscription = findViewById(R.id.btnInscription);
        buttonInscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Inscription.class));
            }
        });


    }
}