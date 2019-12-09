package com.example.mysport.ApplicationInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mysport.R;

import java.util.ArrayList;

public class MesAnnonce extends AppCompatActivity {

    ListView listMesAnnonce;
    public static int positionAnnonce = 0;
    public static ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonce);


        listMesAnnonce =(ListView) findViewById(R.id.listMesAnnonce);

        arrayList.add("Mes Annonces 1");
        arrayList.add("Mes Annonces 2");
        arrayList.add("Mes Annonces 3");
        arrayList.add("Mes Annonces 4");
        arrayList.add("Mes Annonces 5");
        arrayList.add("Mes Annonces 6");
        arrayList.add("Mes Annonces 7");
        arrayList.add("Mes Annonces 9");
        arrayList.add("Mes Annonces 10");
        arrayList.add("Mes Annonces 11");
        arrayList.add("Mes Annonces 12");
        arrayList.add("Mes Annonces 13");
        arrayList.add("Mes Annonces 14");
        arrayList.add("Mes Annonces 15");
        arrayList.add("Mes Annonces 16");
        arrayList.add("Mes Annonces 17");
        arrayList.add("Mes Annonces 19");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listMesAnnonce.setAdapter(arrayAdapter);

        listMesAnnonce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionAnnonce = position;

                startActivity(new Intent(MesAnnonce.this, MesAnnonceOnClick.class));
            }
        });




        Button mesReservation = (Button) findViewById(R.id.mesRes);
        mesReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesAnnonce.this, MesReservation.class));
            }
        });

        Button mesAnnonce = (Button) findViewById(R.id.mesAnn);
        mesAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesAnnonce.this, MesAnnonce.class));
            }
        });

        Button profil = (Button) findViewById(R.id.Profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesAnnonce.this, Profil.class));
            }
        });

        Button ajoutAnnonce = (Button) findViewById(R.id.ajoutAnn);
        ajoutAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesAnnonce.this, AjoutAnnonce.class));
            }
        });

        TextView mySport = (TextView) findViewById(R.id.textView);
        mySport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesAnnonce.this, Accueil.class));
            }
        });



    }
}