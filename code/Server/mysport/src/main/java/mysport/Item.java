package mysport;


public abstract class Item
{
    public abstract String getNom();
    public abstract int getCapacity();
    public abstract String getAdresse();
    public abstract String getCodePostal();
    public abstract int getId();
    public abstract String getTypeSport();

    public abstract void setNom(String nom);
    public abstract void setCapacity(int capacity);
    public abstract void setAdresse(String adresse);
    public abstract void setCodePostal(String codePostal);
    public abstract void setId(int id);
    public abstract void setTypeSport(String typeSport);

    public abstract String getVille();

    public abstract void setVille(String paris);

    public abstract String getClassName();
}
