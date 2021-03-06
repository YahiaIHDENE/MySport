package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MesAnnonce extends AppCompatActivity {
    ListView listViewMesAnnonce;
    public static int positionAnnonce = 0;
    public static List<Annonce> listMesAnnonces = new ArrayList<>();
    public static AnnonceListAdapter annonceListAdapter;
    IDBAccessor idbAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonce);

        listViewMesAnnonce = findViewById(R.id.listMesAnnonce);

        ///////////////////////////////////////////////////////////////////////////////

        idbAccessor = new Retrofit.Builder()
                .baseUrl(IDBAccessor.ENDPOINT)
                .build()
                .create(IDBAccessor.class);

        Gson gson2 = new Gson();
        String user_json = "";

        try {
            InputStream inputStream = MesAnnonce.this.openFileInput("user.json");

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
            Toast.makeText(MesAnnonce.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(MesAnnonce.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        final User user = gson2.fromJson(user_json, User.class);


        idbAccessor.getMesAnnonces(gson2.toJson(user.getId())).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String bodyString= null;
                try{
                    bodyString = new String(response.body().bytes());}
                catch (Exception e ){

                }
                Gson gson = new GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserializer()).create();
                Type listType =new TypeToken<List<Annonce>>(){}.getType();
                listMesAnnonces = (List<Annonce>) gson.fromJson(bodyString,listType);
                annonceListAdapter = new AnnonceListAdapter(getApplicationContext(),listMesAnnonces);
                annonceListAdapter.notifyDataSetChanged();
                listViewMesAnnonce.setAdapter(annonceListAdapter);
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Toast.makeText(MesAnnonce.this,t.toString(),Toast.LENGTH_LONG).show();
            }
        });


        ///////////////////////////////////////////////////////////////////////////////////////

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