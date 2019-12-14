package mysport;

import java.util.Date;

public class Annonce {
    Date dateDisponible,creneau;
    int nombreDePlaceRestant=0;
    Item terrain;
    int idUser=0;
    String typeItem;
    public Annonce(){};

    public Annonce(Date dateDisponible,Date creneau, Item terrain){
        this.dateDisponible= dateDisponible;
        this.creneau =creneau;
        this.terrain = terrain;
        int idUser=0;
        int nombreDePlaceRestant=0;

        this.typeItem = terrain.getClassName();
    }

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

    public int getIdUser() {
        return idUser;
    }
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }

    public Item getTerrain() {
        return terrain;
    }
    public void setTerrain(Item terrain) {

        this.terrain = terrain;
        setTypeItem(terrain.getClassName());
    }

    public void setTypeItem(String typeItem){
        this.typeItem=typeItem;
    }

    public String getTypeItem(){
        return typeItem;
    }
}
