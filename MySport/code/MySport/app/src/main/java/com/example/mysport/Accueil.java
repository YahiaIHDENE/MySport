package com.example.mysport;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    ListView listAnnonce;
    public static int positionAnnonce = 0;
    public static ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        listAnnonce =(ListView) findViewById(R.id.listAnnonce);

        arrayList.add("Annonce 1");
        arrayList.add("Annonce 2");
        arrayList.add("Annonce 3");
        arrayList.add("Annonce 4");
        arrayList.add("Annonce 5");
        arrayList.add("Annonce 6");
        arrayList.add("Annonce 7");
        arrayList.add("Annonce 9");
        arrayList.add("Annonce 10");
        arrayList.add("Annonce 11");
        arrayList.add("Annonce 12");
        arrayList.add("Annonce 13");
        arrayList.add("Annonce 14");
        arrayList.add("Annonce 15");
        arrayList.add("Annonce 16");
        arrayList.add("Annonce 17");
        arrayList.add("Annonce 19");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listAnnonce.setAdapter(arrayAdapter);

        listAnnonce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionAnnonce = position;

                startActivity(new Intent(Accueil.this, AnnonceOnClick.class));
            }
        });

        Button recherche = (Button) findViewById(R.id.rch);
        recherche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, Recherche.class));
            }
        });


        Button mesReservation = (Button) findViewById(R.id.mesRes);
        mesReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, MesReservation.class));
            }
        });

        Button mesAnnonce = (Button) findViewById(R.id.mesAnn);
        mesAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, MesAnnonce.class));
            }
        });

        Button profil = (Button) findViewById(R.id.Profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, Profil.class));
            }
        });

        Button ajoutAnnonce = (Button) findViewById(R.id.ajoutAnn);
        ajoutAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Accueil.this, AjoutAnnonce.class));
            }
        });



    }

}
