package mysport;
public class Recherche {

    String date,heureDebut,typeItem;
    String ville,typeSport;

    public Recherche(String date, String heureDebut, String ville, String typeSport,String typeItem) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.ville = ville;
        this.typeSport = typeSport;
        this.typeItem = typeItem;
    }

    public String getDate() {
        return date;
    }

    public String getTypeItem() {
        return typeItem;
    }

    public void setTypeItem(String typeItem) {
        this.typeItem = typeItem;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(String heureDebut) {
        this.heureDebut = heureDebut;
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