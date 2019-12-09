package com.example.mysport.POJO;

import com.example.mysport.POJO.Annonce;

import java.util.List;

public interface ConnexionBDD {

    void ajouterUtilisateur(User user);
    boolean checkUser(User user);
    int getIdUser(User user);
    void posterAnnonce(Annonce annonce);
    List<Annonce> recupererAnnonces();
    void reserver(int userId,Annonce annonce);
}
