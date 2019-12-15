package mysport;

public class Annonce {
    String dateDisponible, heureDebut,heureFin;
    int nombreDePlaceRestant=0,id_userPublier=0,idAnnonce;
    Item item;
    private String typeItem;

    public Annonce(String dateDisponible, String heureDebut, String heureFin, int nombreDePlaceRestant, int id_userPublier, int idAnnonce, Item item, String typeItem) {
        this.dateDisponible = dateDisponible;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.nombreDePlaceRestant = nombreDePlaceRestant;
        this.id_userPublier = id_userPublier;
        this.idAnnonce = idAnnonce;
        this.item = item;
        this.typeItem = typeItem;
    }
    public Annonce(){};
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;

    }

    public void setTypeItem(String typeItem){
        this.typeItem=typeItem;
    }

    public String getTypeItem(){
        return typeItem;
    }

}