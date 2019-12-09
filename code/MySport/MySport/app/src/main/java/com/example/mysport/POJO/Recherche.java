package com.example.mysport.POJO;

import java.util.Date;

public class Recherche {

    Date date,creneau;
    String ville,typeSport;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getCreneau() {
        return creneau;
    }

    public void setCreneau(Date creneau) {
        this.creneau = creneau;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTypeSport() {
        return typeSport;
    }

    public void setTypeSport(String typeSport) {
        this.typeSport = typeSport;
    }
}
