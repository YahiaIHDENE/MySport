package mysport;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AnnonceDeserializer implements JsonDeserializer<Annonce> {
    // pour s'adpter a tout type de item(qui est un objet abstrait), on utilise cette classe
    // qui permet de décerialisation un objet de la classe Annonce.
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
