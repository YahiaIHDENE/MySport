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

            //Vérifie si les données sont disponible pour se connecter
            connexionBDD.posterAnnonce(annonce);

        }
    }

    @Override
    public List<Annonce> recupererAnnonces() {
        //Fait un test ensuite se connecte pour récupere l'Url

        return null;
    }

    @Override
    public void reserver(int userId,Annonce annonce) {

    }
}
