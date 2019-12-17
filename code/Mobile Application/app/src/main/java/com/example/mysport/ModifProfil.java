package com.example.mysport;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

public class ModifProfil extends AppCompatActivity {
    EditText    nom, prenom, tel, Mdp, ConfirmMdp;
    Button      reser,btn;
    User        user;
    UpdateUserJob  updateUserJob = new UpdateUserJob();
    DBAccessor     dbAccessor = new DBAccessor();
    Gson        gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modif_profil);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        tel = findViewById(R.id.tel);
        Mdp = findViewById(R.id.Mdp);
        ConfirmMdp = findViewById(R.id.ConfirmMdp);
        btn = findViewById(R.id.buttonModifier);

        String user_json = "";

        try {
            InputStream inputStream = ModifProfil.this.openFileInput("user.json");

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
            Toast.makeText(ModifProfil.this, "File  not found", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            Toast.makeText(ModifProfil.this, "IO Exception", Toast.LENGTH_LONG).show();
        }

        user = gson.fromJson(user_json, User.class);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String  lastName    = nom.getText().toString(),
                        firstName   = prenom.getText().toString(),
                        number      = tel.getText().toString(),
                        password    = Mdp.getText().toString(),
                        conf_pw     = ConfirmMdp.getText().toString();
                if (password.equals(conf_pw)){
                    if (!lastName.equals(""))
                        user.setLastname(lastName);

                    if (!firstName.equals(""))
                        user.setFirstname(firstName);

                    if (!number.equals(""))
                        user.setNumber(number);

                    if (!password.equals(""))
                        user.setPW(password);

                    dbAccessor.updateUser(user, updateUserJob);
                    Toast.makeText(ModifProfil.this, "Your profile is updated", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(ModifProfil.this, "incompatible passwords", Toast.LENGTH_LONG).show();
                }
            }
        });

        Button mesReservation = findViewById(R.id.mesRes);
        mesReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifProfil.this, MesReservation.class));
            }
        });

        Button mesAnnonce = findViewById(R.id.mesAnn);
        mesAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifProfil.this, MesAnnonce.class));
            }
        });

        Button profil = findViewById(R.id.Profil);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifProfil.this, Profil.class));
            }
        });

        Button ajoutAnnonce = findViewById(R.id.ajoutAnn);
        ajoutAnnonce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifProfil.this, AjoutAnnonce.class));
            }
        });

        TextView mySport = findViewById(R.id.textView);
        mySport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ModifProfil.this, Accueil.class));
            }
        });
    }
}