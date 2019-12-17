package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class AnnoncesRecherchEs extends AppCompatActivity {
    ListView listViewRecherche;
    public static AnnonceListAdapter annonceListAdapter;
    public static int annonceClickedPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_recherch_es);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewRecherche = findViewById(R.id.listRech);

        FactoryItem factoryItem = new FactoryItem();
        Item item = factoryItem.getInstanceItem("Terrain");
        item.setAdresse("Paris");
        item.setCodePostal("93330");
        item.setNom("Footfive");
        item.setTypeSport("Football");

        Annonce annonce = new Annonce();

        annonce.setDateDisponible("2019-12-12 12:30");


        annonce.setTerrain(item);
        for(int i=0;i<10;i++)
            RecherchePage.listRecherche.add(annonce);

        annonceListAdapter = new AnnonceListAdapter(getApplicationContext(),RecherchePage.listRecherche);
        annonceListAdapter.notifyDataSetChanged();
        listViewRecherche.setAdapter(annonceListAdapter);

        listViewRecherche.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                annonceClickedPosition = position;
                startActivity(new Intent(AnnoncesRecherchEs.this, AnnonceRechercherOnClick.class));
            }
        });

    }
}
