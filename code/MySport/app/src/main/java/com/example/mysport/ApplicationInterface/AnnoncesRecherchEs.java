package com.example.mysport.ApplicationInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.mysport.POJO.Annonce;
import com.example.mysport.POJO.AnnonceListAdapter;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.FactoryItem;
import com.example.mysport.POJO.Item;
import com.example.mysport.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AnnoncesRecherchEs extends AppCompatActivity {

    ListView listViewRecherche;
    public static AnnonceListAdapter annonceListAdapter;
    public static int annonceClickedPosition = 0;
    ConnexionBDD connexionBDD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonces_recherch_es);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewRecherche = findViewById(R.id.listRech);
        connexionBDD = new ConnexionBDDProxy();

        FactoryItem factoryItem = new FactoryItem();
        Item item = factoryItem.getInstanceItem("Terrain");
        item.setAdresse("Paris");
        item.setCodePostal("93330");
        item.setNom("Footfive");
        item.setTypeSport("Football");

        Annonce annonce = new Annonce();
        try {
            annonce.setDateDisponible(new SimpleDateFormat( "yyyy-mm-dd HH:mm" ).parse( "2019-12-12 12:30" ));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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
