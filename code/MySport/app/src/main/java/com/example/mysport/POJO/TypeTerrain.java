package com.example.mysport.POJO;

public abstract class TypeTerrain
{
    public abstract String getNom();
    public abstract int getCapacity();
    public abstract String getAdresse();
    public abstract String getCodePostal();
    public abstract int getId();

    public abstract void setNom(String nom);
    public abstract void setCapacity(int capacity);
    public abstract void setAdresse(String adresse);
    public abstract void setCodePostal(String codePostal);
    public abstract void setId(int id);

}
