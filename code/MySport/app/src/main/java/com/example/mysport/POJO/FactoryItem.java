package com.example.mysport.POJO;

public class FactoryItem {

    public Item getInstanceItem(String str){

        if (str.equals("Terrain"))
            return new Terrain();
        return null;
    }
}
