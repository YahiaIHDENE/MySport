package com.example.mysport;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class AnnonceOnClick extends AppCompatActivity {

    TextView text,res;
    Button reserver;
    public static ArrayList<String> listReservations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_on_click);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = (TextView) findViewById(R.id.textView4);
        res = (TextView) findViewById(R.id.textView5);
        reserver = (Button) findViewById(R.id.button3);

        text.setText("Voulez vous réservez ce terrain: " + Accueil.arrayList.get(Accueil.positionAnnonce) + " ?");

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listReservations.add(Accueil.arrayList.get(Accueil.positionAnnonce));
                res.setText("Vous avez bien réservé le terrain! ");

            }

        });





    }

}
