package com.example.mysport;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class AnnonceDeserializer implements JsonDeserializer<Annonce> {
    // pour s'adpter au tout type de item(objet abstrait), on utilise cette classe permet de décerialisation de string de json,
    // qui convertie un objet annonce
    @Override
    public Annonce deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = jsonElement.getAsJsonObject();
        FactoryItem f = new FactoryItem();
        String typeItem = json.get("typeItem").getAsString();
        Item i = f.getInstanceItem(typeItem);

        Item terr = (Item) new Gson().fromJson(json.get("terrain"), i.getClass());
        System.out.println("tterain "+ terr.getAdresse());


        Annonce annonce = new Annonce();
        annonce.setIdAnnonce(json.get("idAnnonce").getAsInt());
        annonce.setDateDisponible(json.get("dateDisponible").getAsString());
        annonce.setHeureDebut(json.get("heureDebut").getAsString());
        annonce.setHeureFin(json.get("heureFin").getAsString());
        annonce.setTerrain(terr);
        annonce.setTypeItem(typeItem);
        annonce.setId_userPublier(json.get("id_userPublier").getAsInt());
        annonce.setNombreDePlaceRestant(json.get("nombreDePlaceRestant").getAsInt());
        return annonce;
    }
}
