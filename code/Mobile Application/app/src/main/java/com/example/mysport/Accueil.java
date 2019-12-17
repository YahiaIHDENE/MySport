package com.example.mysport;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Accueil extends AppCompatActivity {
    private ListView listViewAnnonce;
    public static AnnonceListAdapter annonceListAdapter;
    public static List<Annonce> annonceList;
    public static int annonceClickedPosition = 0;
    IDBAccessor idbAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listViewAnnonce = findViewById(R.id.listAnnonce);

        // Recupère la liste d'annonces à partir du BDD

        if(annonceList==null){
            annonceList = new ArrayList<Annonce>();
        }
        annonceList.clear();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////


        idbAccessor = new Retrofit.Builder()
                .baseUrl(IDBAccessor.ENDPOINT)
                // .addConverterFactory(ScalarsConverterFactory.create())
                .build()
                .create(IDBAccessor.class);



        idbAccessor.recupererAnnonces().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String bodyString= null;
                try{
                    bodyString = new String(response.body().bytes());}
                catch (Exception e ){

                }
                Gson gson = new GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserializer()).create();
                Type listType =new TypeToken<List<Annonce>>(){}.getType();
                annonceList = (List<Annonce>) gson.fromJson(bodyString,listType);
                //Toast.makeText(Accueil.this,annonceList.get(1).getTerrain().getNom(),Toast.LENGTH_LONG).show();
                annonceListAdapter = new AnnonceListAdapter(getApplicationContext(),annonceList);
                annonceListAdapter.notifyDataSetChanged();
                listViewAnnonce.setAdapter(annonceListAdapter);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(Accueil.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
                startActivity(new Intent(Accueil.this, RecherchePage.class));
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
