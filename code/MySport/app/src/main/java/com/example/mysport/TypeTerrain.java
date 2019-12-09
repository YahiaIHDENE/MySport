package com.example.mysport;

public abstract class TypeTerrain
{
    abstract String getNom();
    abstract String getCapacity();
    abstract String getAdresse();
    abstract String getCodePostal();
    abstract int getId();

    abstract void setNom(String nom);
    abstract void setCapacity(String capacity);
    abstract void setAdresse(String adresse);
    abstract void setCodePostal(String codePostal);
    abstract void setId(int id);

}
