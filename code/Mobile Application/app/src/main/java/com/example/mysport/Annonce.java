package com.example.mysport;

public class Annonce {
    String dateDisponible, heureDebut,heureFin;
    int nombreDePlaceRestant=0,id_userPublier=0,idAnnonce;
    Item terrain; String typeItem;

    public int getIdAnnonce() {
        return idAnnonce;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }

    public void setIdAnnonce(int idAnnonce) {
        this.idAnnonce = idAnnonce;
    }


    public int getId_userPublier() {
        return id_userPublier;
    }

    public void setId_userPublier(int id_userPublier) {
        this.id_userPublier = id_userPublier;
    }

    public String getDateDisponible() {
        return dateDisponible;
    }

    public void setDateDisponible(String dateDisponible) {
        this.dateDisponible = dateDisponible;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
    }

    public String getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(String heureFin) {
        this.heureFin = heureFin;
    }

    public int getNombreDePlaceRestant() {
        return nombreDePlaceRestant;
    }

    public void setNombreDePlaceRestant(int nombreDePlaceRestant) {
        this.nombreDePlaceRestant = nombreDePlaceRestant;
    }

    public Item getTerrain() {
        return terrain;
    }

    public void setTerrain(Item terrain) {
        this.terrain = terrain;
    }


}
