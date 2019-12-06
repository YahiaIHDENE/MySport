package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MesAnnonceOnClick extends AppCompatActivity {

    TextView text,res;
    Button reserver;
    public static ArrayList<String> listReservations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mes_annonce_on_click);

        text = (TextView) findViewById(R.id.textView4);
        res = (TextView) findViewById(R.id.textView5);
        reserver = (Button) findViewById(R.id.button3);

        text.setText("Voulez vous supprimer " + MesAnnonce.arrayList.get(MesAnnonce.positionAnnonce) + " ?");

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listReservations.add(Accueil.arrayList.get(MesAnnonce.positionAnnonce));
                res.setText("L'annonce a bien été supprimée ! ");

            }

        });


    }
}

