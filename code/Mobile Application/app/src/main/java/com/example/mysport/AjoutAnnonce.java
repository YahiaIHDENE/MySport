package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class AjoutAnnonce extends AppCompatActivity {
    private TextView textView,res,text;
    private Button poster;
    private EditText nom,adresse,typeTerrain,capacity,date,heureDebut,heureFin,codePostale,ville,typeSport;
    private String dateDisponible, startTime,finishTime;
    IDBAccessor idbAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajout_annonce);

        Gson gson3 = new Gson();
        String user_json = "";

        try {
            InputStream inputStream = AjoutAnnonce.this.openFileInput("user.json");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                user_json = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Toast.makeText(AjoutAnnonce.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(AjoutAnnonce.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        final User user = gson3.fromJson(user_json, User.class);

        nom = findViewById(R.id.nomTerrain);
        adresse = findViewById(R.id.Adresse);
        typeTerrain = findViewById(R.id.TypeSport);
        capacity = findViewById(R.id.Capacite);
        date = findViewById(R.id.Date);
        codePostale = findViewById(R.id.cp);
        ville = findViewById(R.id.ville);
        typeSport = findViewById(R.id.typeSport);
        heureDebut = findViewById(R.id.heureDebut);
        heureFin = findViewById(R.id.heureFin);
        poster = findViewById(R.id.publierAnn);
        res = findViewById(R.id.textView5);

        idbAccessor = new Retrofit.Builder()
                .baseUrl(IDBAccessor.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IDBAccessor.class);

        poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Nom = nom.getText().toString();
                String Adresse = adresse.getText().toString();
                String Type = typeTerrain.getText().toString();
                String Capa = capacity.getText().toString();
                int Capacity = Integer.valueOf(Capa);

                dateDisponible = date.getText().toString();
                startTime = heureDebut.getText().toString();
                finishTime = heureFin.getText().toString();

                FactoryItem factoryItem = new FactoryItem();
                Item item = factoryItem.getInstanceItem(Type);
                item.setNom(Nom);
                item.setAdresse(Adresse);
                item.setCapacity(Capacity);
                item.setVille(ville.getText().toString());
                item.setCodePostal(codePostale.getText().toString());
                item.setTypeSport(typeSport.getText().toString());

                Annonce annonce = new Annonce();
                annonce.setTerrain(item);
                annonce.setDateDisponible(dateDisponible);
                annonce.setNombreDePlaceRestant(Capacity);
                annonce.setHeureDebut(startTime);
                annonce.setHeureFin(finishTime);
                //annonce.setId_userPublier(MainActivity.user_id);

                annonce.setId_userPublier(user.getId());

                annonce.setTypeItem(Type);
                ////////////////////////////////////////////////////////////////////////////////////////////

                Gson gson = new Gson();
                String json = gson.toJson(annonce);

                idbAccessor.addAnnonce(json).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(AjoutAnnonce.this,"Annonce bien depose", Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        //Toast.makeText(AjoutAnnonce.this,"marche pas",Toast.LENGTH_LONG).show();
                    }
                });
                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                Toast.makeText(AjoutAnnonce.this,"Annonce bien depose" + dateDisponible + " à " + startTime,Toast.LENGTH_LONG).show();
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

