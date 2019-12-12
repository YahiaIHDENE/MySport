package com.example.mysport.ApplicationInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.R;

public class AnnonceRechercherOnClick extends AppCompatActivity {


    TextView text,res;
    Button reserver;
    ConnexionBDD connexionBDD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annonce_rechercher_on_click);


        text = findViewById(R.id.textView4);
        res = findViewById(R.id.textView5);
        reserver = findViewById(R.id.button3);

        text.setText("Voulez vous réserver cette annonce: "
                + RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getTerrain().getNom() + ", à  "
                + RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getTerrain().getAdresse() + ", Type: "
                + RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition).getTerrain().getTypeSport()
                + " ?");

        reserver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connexionBDD = new ConnexionBDDProxy();
                connexionBDD.reserver(MainActivity.user_id, RecherchePage.listRecherche.get(AnnoncesRecherchEs.annonceClickedPosition));
                res.setText("Vous avez bien réservé le terrain! ");
            }
        });
    }
}
