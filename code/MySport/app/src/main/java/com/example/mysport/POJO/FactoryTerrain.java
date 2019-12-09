package com.example.mysport.POJO;

public class FactoryTerrain {

    public TypeTerrain getInstanceTerrain(String str){

        if (str.equals("Football"))
            return new Terrain();
        return null;
    }
}
