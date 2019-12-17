package com.example.mysport;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class AnnonceOnClick extends AppCompatActivity {
    TextView text,res;
    EditText nbPlace;
    Button reserver;
    DBAccessor dbAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_on_click);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = findViewById(R.id.textView4);
        res = findViewById(R.id.textView5);
        nbPlace = findViewById(R.id.nbPlace);
        reserver = findViewById(R.id.supprimer);

        Gson gson3 = new Gson();
        String user_json = "";

        try {
            InputStream inputStream = AnnonceOnClick.this.openFileInput("user.json");

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
            Toast.makeText(AnnonceOnClick.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(AnnonceOnClick.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        final User user = gson3.fromJson(user_json, User.class);

        String affiche = new StringBuilder().append("Voulez vous réserver cette annonce: ").append(Accueil.annonceList.get(Accueil.annonceClickedPosition).getTerrain().getNom()).append(", à  ").append(Accueil.annonceList.get(Accueil.annonceClickedPosition).getTerrain().getAdresse()).append(", Type: ").append(Accueil.annonceList.get(Accueil.annonceClickedPosition).getTerrain().getTypeSport()).append(" avec ").append(Accueil.annonceList.get(Accueil.annonceClickedPosition).getNombreDePlaceRestant()).append(" places disponible. Heure début: ").append(Accueil.annonceList.get(Accueil.annonceClickedPosition).getHeureDebut()).append(" ?").toString();

        text.setText(affiche);

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantite = new Integer(nbPlace.getText().toString());

                Reservation reservation = new Reservation();

                //reservation.setIdAnnonce(Accueil.annonceList.get(Accueil.annonceClickedPosition).getIdAnnonce());
                reservation.setIdAnnonce(Accueil.annonceList.get(Accueil.annonceClickedPosition).getIdAnnonce());
                reservation.setNombreDePlace(quantite);
                reservation.setIdUser(user.getId());
                reservation.setHeureDebut(Accueil.annonceList.get(Accueil.annonceClickedPosition).getHeureDebut());
                reservation.setHeureFin(Accueil.annonceList.get(Accueil.annonceClickedPosition).getHeureFin());

///////////////////////////////////////////////////////////////////////
                dbAccessor = new DBAccessor();
                AddReservationJob addReservationJob = new AddReservationJob();
                dbAccessor.addReservation(reservation, AnnonceOnClick.this,addReservationJob);
///////////////////////////////////////////////////////////////////////////////////
                res.setText("Vous avez bien réservé! ");
                startActivity(new Intent(AnnonceOnClick.this, MesReservation.class));
            }
        });


    }
}
