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


public class MesReservation extends AppCompatActivity {

    ListView listMesReservation;
    public static int positionAnnonce = 0;
    public static ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_reservation);

        listMesReservation =(ListView) findViewById(R.id.listMesReservation);

        arrayList.add("Mes Reservations 1");
        arrayList.add("Mes Reservations 2");
        arrayList.add("Mes Reservations 3");
        arrayList.add("Mes Reservations 4");
        arrayList.add("Mes Reservations 5");
        arrayList.add("Mes Reservations 6");
        arrayList.add("Mes Reservations 7");
        arrayList.add("Mes Reservations 9");
        arrayList.add("Mes Reservations 10");
        arrayList.add("Mes Reservations 11");
        arrayList.add("Mes Reservations 12");
        arrayList.add("Mes Reservations 13");
        arrayList.add("Mes Reservations 14");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listMesReservation.setAdapter(arrayAdapter);

        listMesReservation.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                positionAnnonce = position;

                startActivity(new Intent(MesReservation.this, MesAnnonceOnClick.class));
            }
        });




        Button mesReservation = (Button) findViewById(R.id.mesRes);
        mesReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesReservation.this, MesReservation.class));
            }
        });

        Button mesAnnonce = (Button) findViewById(R.id.mesAnn);
        mesAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesReservation.this, MesAnnonce.class));
            }
        });

        Button profil = (Button) findViewById(R.id.Profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesReservation.this, Profil.class));
            }
        });

        Button ajoutAnnonce = (Button) findViewById(R.id.ajoutAnn);
        ajoutAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesReservation.this, AjoutAnnonce.class));
            }
        });

        TextView mySport = (TextView) findViewById(R.id.textView);
        mySport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MesReservation.this, Accueil.class));
            }
        });




    }
}




