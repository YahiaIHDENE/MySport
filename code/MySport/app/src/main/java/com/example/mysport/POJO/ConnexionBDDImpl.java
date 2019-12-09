package com.example.mysport.POJO;

import com.example.mysport.POJO.Annonce;
import com.example.mysport.POJO.ConnexionBDD;

import java.util.List;

public class ConnexionBDDImpl implements ConnexionBDD {
    @Override
    public void ajouterUtilisateur(User user) {

    }

    @Override
    public boolean checkUser(User user) {
        return false;
    }

    @Override
    public int getIdUser(User user) {
        return 0;
    }

    @Override
    public void posterAnnonce(Annonce annonce) {

        //Se connecte à l'Url pour poster l'annonce.

    }

    @Override
    public List<Annonce> recupererAnnonces() {

        //Se connecte à l'Url pour recuperer une liste d'annonce
        return null;
    }

    @Override
    public void reserver(int userId, Annonce annonce) {

    }
}
