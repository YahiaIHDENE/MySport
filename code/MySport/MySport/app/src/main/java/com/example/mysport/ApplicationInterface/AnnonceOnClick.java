package com.example.mysport.ApplicationInterface;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysport.ApplicationInterface.Accueil;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.TypeTerrain;
import com.example.mysport.R;

import java.util.ArrayList;


public class AnnonceOnClick extends AppCompatActivity {

    TextView text,res;
    Button reserver;
    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_on_click);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        text = findViewById(R.id.textView4);
        res = findViewById(R.id.textView5);
        reserver = findViewById(R.id.button3);

        text.setText("Voulez vous réservez ce terrain ?");

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexionBDD = new ConnexionBDDProxy();
                //Il faut décrementer le nombre de place dans l'annonce!!
                connexionBDD.reserver(MainActivity.user_id,Accueil.annonceList.get(Accueil.annonceClickedPosition));
                res.setText("Vous avez bien réservé le terrain! ");

            }

        });


    }

}
