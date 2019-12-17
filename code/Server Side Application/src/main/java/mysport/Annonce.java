package mysport;

import java.util.Date;
import java.util.GregorianCalendar;

public class Annonce {
    private String dateDisponible, heureDebut,heureFin;
    private int nombreDePlaceRestant=0,id_userPublier=0,idAnnonce;
    private Item terrain;
    private String typeItem;

    public int getIdAnnonce() {
        return idAnnonce;
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
        //setTypeItem(terrain.getClassName());
    }

    public void setTypeItem(String typeItem){
        this.typeItem=typeItem;
    }

    public String getTypeItem(){
        return typeItem;
    }

}

