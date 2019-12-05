package com.example.mysport;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listAnnonce =(ListView) findViewById(R.id.listAnnonce);

        arrayList.add("nawfaz");
        arrayList.add("azdin");
        arrayList.add("souad");
        arrayList.add("wenbi");
        arrayList.add("issa");
        arrayList.add("hakim");
        arrayList.add("nawfaz");
        arrayList.add("azdin");
        arrayList.add("souad");
        arrayList.add("wenbi");
        arrayList.add("issa");
        arrayList.add("hakim");
        arrayList.add("nawfaz");
        arrayList.add("azdin");
        arrayList.add("souad");
        arrayList.add("wenbi");
        arrayList.add("issa");
        arrayList.add("hakim");

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


    }

}
