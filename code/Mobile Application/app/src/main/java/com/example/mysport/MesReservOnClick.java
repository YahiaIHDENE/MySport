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
import java.util.ArrayList;

public class MesReservOnClick extends AppCompatActivity {
    TextView text,res;
    EditText nbPlace;
    Button supprimer,modifier;
    public static ArrayList<String> listReservations = new ArrayList<>();
    DBAccessor dbAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_reserv_on_click);

        Gson gson2 = new Gson();
        String user_json = "";

        try {
            InputStream inputStream = MesReservOnClick.this.openFileInput("user.json");

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
            Toast.makeText(MesReservOnClick.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(MesReservOnClick.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        final User user = gson2.fromJson(user_json, User.class);

        dbAccessor = new DBAccessor();
        final DeleteReservation deleteReservation = new DeleteReservation();
        final EditReservationJob editReservationJob = new EditReservationJob();

        text = findViewById(R.id.textView4);
        res = findViewById(R.id.textView5);
        supprimer = findViewById(R.id.supprimer);
        modifier = findViewById(R.id.modifier);
        nbPlace = findViewById(R.id.edit);

        text.setText("Voulez vous supprimer cette réservation: "
                + MesReservation.listReservation.get(MesReservation.positionAnnonce).getTerrain().getNom() + ", à  "
                + MesReservation.listReservation.get(MesReservation.positionAnnonce).getTerrain().getAdresse() + ", Type: "
                + MesReservation.listReservation.get(MesReservation.positionAnnonce).getTerrain().getTypeSport() + " avec " +
                MesReservation.listReservation.get(MesReservation.positionAnnonce).getNombreDePlaceRestant() + " places disponible. Heure début: " +
                MesReservation.listReservation.get(MesReservation.positionAnnonce).getHeureDebut()
                + " ?");

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ///////////////////////////////////////////////////////////////////////
                Reservation reservation = new Reservation();
                reservation.setIdAnnonce(MesReservation.listReservation.get(MesReservation.positionAnnonce).getIdAnnonce());
                reservation.setIdReservation(MesReservation.listReservation.get(MesReservation.positionAnnonce).getIdAnnonce());
                reservation.setIdUser(user.getId());
                reservation.setNombreDePlace(MesReservation.listReservation.get(MesReservation.positionAnnonce).getNombreDePlaceRestant());
                reservation.setHeureDebut("12:00");
                reservation.setHeureFin("15:00");

                dbAccessor.deleteReservation(reservation, deleteReservation);
///////////////////////////////////////////////////////////////////////////////////
                res.setText("La réservation a bien été supprimée ! ");
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Reservation reservation = new Reservation();
                reservation.setIdAnnonce(MesReservation.listReservation.get(MesReservation.positionAnnonce).getIdAnnonce());
                reservation.setIdReservation(MesReservation.listReservation.get(MesReservation.positionAnnonce).getIdAnnonce());
                reservation.setIdUser(user.getId());
                reservation.setNombreDePlace(new Integer(nbPlace.getText().toString()));
                reservation.setHeureDebut("12:00");
                reservation.setHeureFin("15:00");

                dbAccessor.EditReservation(reservation, editReservationJob);
            }
        });
    }
}

