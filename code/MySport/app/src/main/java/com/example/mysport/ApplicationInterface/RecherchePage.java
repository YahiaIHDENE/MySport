package com.example.mysport.ApplicationInterface;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mysport.POJO.Annonce;
import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.Recherche;
import com.example.mysport.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RecherchePage extends AppCompatActivity {

    TextView textView;
    EditText date,ville,typeSport;
    Button rechercher;
    ConnexionBDD connexionBDD;
    public static List<Annonce> listRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_page);

        textView = findViewById(R.id.text);
        date = findViewById(R.id.date);
        ville = findViewById(R.id.ville);
        typeSport = findViewById(R.id.typeSport);
        rechercher = findViewById(R.id.btnRechercher);

        textView.setText("Faite votre recherche! ");

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recherche recherche = new Recherche();

                recherche.setVille(ville.getText().toString());
                try {
                    recherche.setDate(new SimpleDateFormat( "yyyy-mm-dd" ).parse(date.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                recherche.setTypeSport(typeSport.getText().toString());

                connexionBDD = new ConnexionBDDProxy();

                listRecherche = new ArrayList<>();
                listRecherche.clear();
                //listRecherche = connexionBDD.rechercherAnnonces(recherche);

                startActivity(new Intent(RecherchePage.this, AnnoncesRecherchEs.class));
            }
        });
    }
}
