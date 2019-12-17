package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MesAnnonceOnClick extends AppCompatActivity {

    TextView text,res;
    Button supprimer;
    DBAccessor dbAccessor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonce_on_click);

        Gson gson2 = new Gson();
        String user_json = "";

        try {
            InputStream inputStream = MesAnnonceOnClick.this.openFileInput("user.json");

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
            Toast.makeText(MesAnnonceOnClick.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(MesAnnonceOnClick.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        final User user = gson2.fromJson(user_json, User.class);

        text = findViewById(R.id.textView4);
        res = findViewById(R.id.textView5);
        supprimer = findViewById(R.id.supprimer);

        text.setText("Voulez vous supprimer cette annonce: "
                + MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getTerrain().getNom() + ", à  "
                + MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getTerrain().getAdresse() + ", Type: "
                + MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getTerrain().getTypeSport() +
                MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getHeureDebut()
                + " ?");

        dbAccessor = new DBAccessor();
        final DeleteAnnonceJob deleteAnnonceJob = new DeleteAnnonceJob();

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAccessor.deleteAnnonce(user.getId(), deleteAnnonceJob);
            }
        });


    }
}

