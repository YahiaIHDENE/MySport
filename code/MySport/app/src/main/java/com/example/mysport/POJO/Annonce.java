package com.example.mysport.POJO;

import java.util.Date;
import java.util.GregorianCalendar;

public class Annonce {
    Date dateDisponible,creneau;
    int nombreDePlaceRestant=0;
    Item terrain;

    public Date getDateDisponible() {
        return dateDisponible;
    }

    public void setDateDisponible(Date dateDisponible) {
        this.dateDisponible = dateDisponible;
    }

    public Date getCreneau() {
        return creneau;
    }

    public void setCreneau(Date creneau) {
        this.creneau = creneau;
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
