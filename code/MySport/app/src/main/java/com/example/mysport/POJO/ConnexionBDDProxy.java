package com.example.mysport.POJO;

import java.util.List;

public class ConnexionBDDProxy implements ConnexionBDD {

    ConnexionBDDImpl connexionBDD;

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
        if(connexionBDD==null)connexionBDD = new ConnexionBDDImpl();
        if(annonce.creneau!=null && annonce.dateDisponible!=null && annonce.nombreDePlaceRestant!=0 && annonce.terrain!=null){

            connexionBDD.posterAnnonce(annonce);

        }
    }

    @Override
    public List<Annonce> recupererMesAnnonces(String user_id) {
        return null;
    }

    @Override
    public void supprimerMesAnnonces(String user_id, Annonce annonce) {

    }

    @Override
    public void reserver(int userId,Annonce annonce) {

    }

    @Override
    public List<Annonce> rechercherAnnonces(Recherche recherche) {
        return null;
    }
}
