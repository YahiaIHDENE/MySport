package mysport;


import com.google.gson.*;

import java.lang.reflect.Type;

public class AnnonceDeserialize implements JsonDeserializer<Annonce> {
    // pour s'adpter au tout type de item(objet abstrait), on utilise cette classe permet de décerialisation de string de json,
    // qui convertie un objet annonce
    @Override
    public Annonce deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = jsonElement.getAsJsonObject();
        FactoryItem f = new FactoryItem();
        String typeItem = json.get("typeItem").getAsString();

        Gson gson=new Gson();


        Item i = f.getInstanceItem(typeItem);
        Item terr =  new Gson().fromJson(json.get("item"), i.getClass());

        String receivedannonces = gson.toJson(terr);
        System.out.println(receivedannonces);
        Annonce annonce = new Annonce();
        annonce.setIdAnnonce(json.get("idAnnonce").getAsInt());
        annonce.setDateDisponible(json.get("dateDisponible").getAsString());
        annonce.setHeureDebut(json.get("heureDebut").getAsString());
        annonce.setHeureFin(json.get("heureFin").getAsString());
        annonce.setItem(terr);
        annonce.setTypeItem(typeItem);
        annonce.setId_userPublier(json.get("id_userPublier").getAsInt());
        annonce.setNombreDePlaceRestant(json.get("nombreDePlaceRestant").getAsInt());
        return annonce;
    }

}