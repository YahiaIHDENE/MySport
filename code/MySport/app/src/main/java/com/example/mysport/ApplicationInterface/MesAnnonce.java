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

import com.example.mysport.POJO.Annonce;
import com.example.mysport.POJO.AnnonceListAdapter;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.FactoryItem;
import com.example.mysport.POJO.Item;
import com.example.mysport.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MesAnnonce extends AppCompatActivity {

    ListView listViewMesAnnonce;
    public static int positionAnnonce = 0;
    public static List<Annonce> listMesAnnonces;
    public static AnnonceListAdapter annonceListAdapter;
    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonce);

        listViewMesAnnonce = findViewById(R.id.listMesAnnonce);

        connexionBDD = new ConnexionBDDProxy();

        if(listMesAnnonces==null){
            listMesAnnonces = new ArrayList<>();
        }

        listMesAnnonces.clear();

      FactoryItem factoryItem = new FactoryItem();
        Item item = factoryItem.getInstanceItem("Terrain");
        item.setAdresse("neuiily sur marne");
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
            listMesAnnonces.add(annonce);

        //listMesAnnonces = connexionBDD.recupererMesAnnonces(String.valueOf(MainActivity.user_id));

        annonceListAdapter = new AnnonceListAdapter(getApplicationContext(),listMesAnnonces);
        annonceListAdapter.notifyDataSetChanged();
        listViewMesAnnonce.setAdapter(annonceListAdapter);


        listViewMesAnnonce.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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