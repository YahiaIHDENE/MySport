package com.example.mysport.ApplicationInterface;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.mysport.POJO.ConnexionBDD;
import com.example.mysport.POJO.ConnexionBDDProxy;
import com.example.mysport.POJO.User;
import com.example.mysport.R;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.Retrofit;
import retrofit2.http.Path;



public class Inscription extends AppCompatActivity {

    EditText nom,prenom,email,motDePasse,confirmationMotDePasse,tel;
    Button btn;
    User user;
    UserHandler userHandler =new UserHandler();
    AddUserJob addUserJob =new AddUserJob();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        email = findViewById(R.id.Email);
        tel = findViewById(R.id.tel);
        motDePasse = findViewById(R.id.Mdp);
        confirmationMotDePasse = findViewById(R.id.confirmerMdp);
        btn = findViewById(R.id.btnInscription);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    User user = new User(nom.getText().toString(),
                            prenom.getText().toString(),
                            email.getText().toString(),
                            tel.getText().toString(),
                            motDePasse.getText().toString());


                    userHandler.addUser(user, addUserJob );

                    //startActivity(new Intent(Inscription.this, MainActivity.class));
            }
        });
    }
}
