package mysport;


public class Equipement extends Item {

    String nom,codePostal,adresse,typeSport,ville,description;
    int capacity;
    int id;

    public Equipement(String nom, String codePostal, String adresse, String typeSport, String ville, String description,int capacity, int id) {
        this.nom = nom;
        this.codePostal = codePostal;
        this.adresse = adresse;
        this.typeSport = typeSport;
        this.ville = ville;
        this.capacity = capacity;
        this.id = id;
        this.description=description;
    }

    public Equipement() {
    }



    public String getVille(){
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDescription(String description) {
        this.description=description;

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

    public String getDescription(){
            return description;
    }



}