package mysport;

public class Terrain extends Item {

    String nom,codePostal,adresse,typeSport,ville;

    int capacity;
    int id;

    public  Terrain(String nom,String adresse,String codePostal,String typeSport,int capacity,String ville){
        this.nom = nom;
        this.adresse=adresse;
        this.codePostal=codePostal;
        this.typeSport= typeSport;
        this.capacity=capacity;
        this.id = -1;
        this.ville = ville;
    }


    public Terrain(){};
    public String getTypeSport() {
        return typeSport;
    }

    public void setTypeSport(String typeSport) {
        this.typeSport = typeSport;
    }

    @Override
    public void setVille(String ville) {
        this.ville=ville;;
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
    @Override
    public String getClassName() {
        return "Terrain";
    }
    public String getVille(){return this.ville;}
}
