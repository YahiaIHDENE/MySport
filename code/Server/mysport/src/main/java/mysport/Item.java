package mysport;

public interface Item
{
    String getNom();
    int getCapacity();
    String getAdresse();
    String getCodePostal();
    int getId();
    String getTypeSport();

    void setNom(String nom);
    void setCapacity(int capacity);
    void setAdresse(String adresse);
    void setCodePostal(String codePostal);
    void setId(int id);
    void setTypeSport(String typeSport);

    String getVille();
    void setVille(String paris);
    String getClassName();
}