package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;

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

public class AnnonceRechercherOnClick extends AppCompatActivity {
    private EditText nbPlace;
    TextView text,res;
    Button reserver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_rechercher_on_click);

        Gson gson3 = new Gson();
        String user_json = "";

        try {
            InputStream inputStream = AnnonceRechercherOnClick.this.openFileInput("user.json");

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
            Toast.makeText(AnnonceRechercherOnClick.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(AnnonceRechercherOnClick.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        final User user = gson3.fromJson(user_json, User.class);

        text = findViewById(R.id.textView4);
        res = findViewById(R.id.textView5);
        nbPlace = findViewById(R.id.nbPlace);
        reserver = findViewById(R.id.supprimer);

        text.setText("Voulez vous réserver cette annonce: "
                + RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getTerrain().getNom() + ", à  "
                + RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getTerrain().getAdresse() + ", Type: "
                + RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getTerrain().getTypeSport() + " avec " +
                RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getNombreDePlaceRestant() + " places disponible. Heure début: " +
                RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getHeureDebut()
                + " ?");

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int quantite = new Integer(nbPlace.getText().toString());
                Reservation reservation = new Reservation();

                reservation.setIdUser(user.getId());
                reservation.setNombreDePlace(quantite);

                res.setText("Vous avez bien réservé le terrain! ");
            }
        });
    }
}
