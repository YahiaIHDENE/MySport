package com.example.mysport;

public class FactoryTerrain {

    public TypeTerrain getInstanceTerrain(String str){

        if (str.equals("Football"))
            return new Terrain();
        return null;
    }
}
