package com.example.mysport.ApplicationInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysport.POJO.Annonce;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.FactoryItem;
import com.example.mysport.POJO.Item;
import com.example.mysport.R;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AjoutAnnonce extends AppCompatActivity {

    private TextView textView,res,text;
    private Button poster;
    private EditText nom,adresse,typeTerrain,capacity,date;
    private Date dateDisponible;


    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_annonce);

        nom = findViewById(R.id.nomTerrain);
        adresse = findViewById(R.id.Adresse);
        typeTerrain = findViewById(R.id.TypeSport);
        capacity = findViewById(R.id.Capacite);
        date = findViewById(R.id.Date);
        poster = findViewById(R.id.publierAnn);
        res = findViewById(R.id.textView5);

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nom = nom.getText().toString();
                String Adresse = adresse.getText().toString();
                String Type = typeTerrain.getText().toString();
                String Capa = capacity.getText().toString();
                int Capacity = new Integer(Capa);

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                try {
                    dateDisponible = formatter.parse(date.getText().toString());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                FactoryItem factoryItem = new FactoryItem();
                Item item = factoryItem.getInstanceItem(Type);
                item.setNom(Nom);
                item.setAdresse(Adresse);
                item.setCapacity(Capacity);

                Annonce annonce = new Annonce();
                annonce.setTerrain(item);
                annonce.setDateDisponible(dateDisponible);
                annonce.setNombreDePlaceRestant(Capacity);

                Accueil.annonceList.add(annonce);
                Accueil.annonceListAdapter.notifyDataSetChanged();

                connexionBDD = new ConnexionBDDProxy();
                connexionBDD.posterAnnonce(annonce);

                Toast.makeText(AjoutAnnonce.this,"Ajout de l'annonce..." + dateDisponible,Toast.LENGTH_LONG).show();

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

    }
}

