package com.example.mysport.ApplicationInterface;

import android.content.Intent;
import android.os.Bundle;

import com.example.mysport.POJO.Annonce;
import com.example.mysport.POJO.AnnonceListAdapter;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.Recherche;
import com.example.mysport.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Accueil extends AppCompatActivity {

    private ListView listViewAnnonce;
    public AnnonceListAdapter annonceListAdapter;
    public static List<Annonce> annonceList;
    public static int annonceClickedPosition = 0;
    public static Annonce annonceClicked;
    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewAnnonce = findViewById(R.id.listAnnonce);
        connexionBDD = new ConnexionBDDProxy();

        // Recupère la liste d'annonces à partir du BDD

        if(annonceList==null){
            annonceList = new ArrayList<>();
        }
        annonceList.clear();
       // annonceList = connexionBDD.recupererAnnonces();

        annonceListAdapter = new AnnonceListAdapter(getApplicationContext(),annonceList);
        listViewAnnonce.setAdapter(annonceListAdapter);

        //Clique sur un item pour la réserver

        listViewAnnonce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                annonceClickedPosition = position;
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
