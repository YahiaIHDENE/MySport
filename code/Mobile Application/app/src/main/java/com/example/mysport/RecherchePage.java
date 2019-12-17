package com.example.mysport;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class RecherchePage extends AppCompatActivity {
    private Spinner spinner2, spinner1, spinner3;
    private Button btnSubmit;

    TextView textView;
    EditText date,ville,typeSport;
    Button rechercher;
    public static List<Annonce> listRecherche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_page);

        addItemsOnSpinner2();
        addListenerOnButton();


        textView = findViewById(R.id.text);
        ville = findViewById(R.id.ville);
        typeSport = findViewById(R.id.typeSport);
        rechercher = findViewById(R.id.btnRechercher);

        textView.setText("Faite votre recherche! ");



        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Recherche recherche = new Recherche();

                recherche.setVille(ville.getText().toString());
                recherche.setDate(date.getText().toString());

                recherche.setTypeSport(typeSport.getText().toString());

                listRecherche = new ArrayList<>();
                listRecherche.clear();
                //listRecherche = connexionBDD.rechercherAnnonces(recherche);

                startActivity(new Intent(RecherchePage.this, AnnoncesRecherchEs.class));
            }
        });
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {
        spinner1 = findViewById(R.id.spinner1);
        List<String> list = new ArrayList<String>();
        for(int i=1; i<32; i++)
            list.add(String.valueOf(i));

        spinner2 = findViewById(R.id.spinner2);
        List<String> list1 = new ArrayList<String>();
        for(int i=1; i<13; i++)
            list1.add(String.valueOf(i));

        spinner3 = findViewById(R.id.spinner3);
        List<String> list2 = new ArrayList<String>();
        for(int i=2019; i<2090; i++)
            list2.add(String.valueOf(i));

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {
        spinner1  =  findViewById(R.id.spinner1);
        spinner2  =  findViewById(R.id.spinner2);
        spinner3  =  findViewById(R.id.spinner3);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(RecherchePage.this," vous avez choisit la date:"+ String.valueOf(spinner1.getSelectedItem()) +
                                " vous avez choisit la date:"+ String.valueOf(spinner2.getSelectedItem())+
                                " vous avez choisit la date:"+ String.valueOf(spinner3.getSelectedItem())
                        , Toast.LENGTH_SHORT).show();
            }

        });
    }
}
