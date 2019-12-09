package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class AjoutAnnonce extends AppCompatActivity {

    private TextView textView;

    TextView text,res;
    Button reserver, poster;
    EditText nom,adresse,typeTerrain,capacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_annonce);

        nom = (EditText) findViewById(R.id.nomTerrain);
        adresse = (EditText) findViewById(R.id.Adresse);
        typeTerrain = (EditText) findViewById(R.id.TypeSport);
        capacity = (EditText) findViewById(R.id.Capacite);
        poster = (Button) findViewById(R.id.publierAnn);

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nom = nom.getText().toString();
                String Adresse = adresse.getText().toString();
                String Type = typeTerrain.getText().toString();
                String Capacity = capacity.getText().toString();


                FactoryTerrain factoryTerrain = new FactoryTerrain();
                TypeTerrain typeTerrain = factoryTerrain.getInstanceTerrain(Type);
                typeTerrain.setNom(Nom);
                typeTerrain.setAdresse(Adresse);
                typeTerrain.setCapacity(Capacity);

                Accueil.terrainList.add(typeTerrain);

                Toast.makeText(AjoutAnnonce.this,"Ajout de l'annonce...",Toast.LENGTH_LONG).show();

                Accueil.terrainListAdapter.notifyDataSetChanged();

                startActivity(new Intent(AjoutAnnonce.this, Accueil.class));

            }
        });





        Button mesReservation = (Button) findViewById(R.id.mesRes);
        mesReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AjoutAnnonce.this, MesReservation.class));
            }
        });

        Button mesAnnonce = (Button) findViewById(R.id.mesAnn);
        mesAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AjoutAnnonce.this, MesAnnonce.class));
            }
        });

        Button profil = (Button) findViewById(R.id.Profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AjoutAnnonce.this, Profil.class));
            }
        });

        Button ajoutAnnonce = (Button) findViewById(R.id.ajoutAnn);
        ajoutAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AjoutAnnonce.this, AjoutAnnonce.class));
            }
        });

        TextView mySport = (TextView) findViewById(R.id.textView);
        mySport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AjoutAnnonce.this, Accueil.class));
            }
        });



        res = (TextView) findViewById(R.id.textView5);
        reserver = (Button) findViewById(R.id.publierAnn);


        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                res.setText("Votre annonce a bien été enregistrée ");

            }

        });


    }
}

