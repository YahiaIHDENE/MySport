package com.example.mysport.POJO;

import com.example.mysport.POJO.Annonce;

import java.util.List;

public interface ConnexionBDD {

    void ajouterUtilisateur(User user);
    boolean checkUser(User user);
    int getIdUser(User user);
    void posterAnnonce(Annonce annonce);
    List<Annonce> recupererMesAnnonces(String user_id);
    void supprimerMesAnnonces(String user_id,Annonce annonce);
    void reserver(int userId,Annonce annonce);
    List<Annonce> rechercherAnnonces(Recherche recherche);
}
