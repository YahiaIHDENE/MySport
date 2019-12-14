package mysport;


public class Terrain extends Item {

    String nom,codePostal,adresse,typeSport,ville;
    int capacity;
    int id;

    public Terrain(String nom, String codePostal, String adresse, String typeSport, String ville, int capacity, int id) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.adresse = adresse;
        this.typeSport = typeSport;
        this.ville = ville;
        this.capacity = capacity;
        this.id = id;
    }

    public Terrain() {
    }

    public String getVille(){
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getClassName() {
        return "Terrain";
    }
}
