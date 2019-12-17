package com.example.mysport;

public interface Item
{
    String getNom();
    int getCapacity();
    String getAdresse();
    String getCodePostal();
    int getId();
    String getTypeSport();
    String getVille();
    String getClassName();

    void setNom(String nom);
    void setCapacity(int capacity);
    void setAdresse(String adresse);
    void setCodePostal(String codePostal);
    void setId(int id);
    void setTypeSport(String typeSport);
    void setVille(String ville);

}