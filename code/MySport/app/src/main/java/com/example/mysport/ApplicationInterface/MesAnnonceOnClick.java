package com.example.mysport.ApplicationInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysport.ApplicationInterface.MesAnnonce;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.R;

import java.util.ArrayList;

public class MesAnnonceOnClick extends AppCompatActivity {

    TextView text,res;
    Button supprimer;
    public static ArrayList<String> listReservations = new ArrayList<>();
    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonce_on_click);

        text = (TextView) findViewById(R.id.textView4);
        res = (TextView) findViewById(R.id.textView5);
        supprimer = (Button) findViewById(R.id.button3);

        text.setText("Voulez vous supprimer cette annonce: "
                + MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getTerrain().getNom() + ", à  "
                + MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getTerrain().getAdresse() + ", Type: "
                + MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce).getTerrain().getTypeSport()
                + " ?");

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                connexionBDD = new ConnexionBDDProxy();
                connexionBDD.supprimerMesAnnonces( String.valueOf(MainActivity.user_id), MesAnnonce.listMesAnnonces.get(MesAnnonce.positionAnnonce));

                res.setText("L'annonce a bien été supprimée ! ");

            }

        });


    }
}

