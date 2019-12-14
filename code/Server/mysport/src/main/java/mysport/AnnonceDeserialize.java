package mysport;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AnnonceDeserialize implements JsonDeserializer<Annonce> {
    // pour s'adpter au tout type de item(objet abstrait), on utilise cette classe permet de décerialisation de string de json,
    // qui convertie un objet annonce
    @Override
    public Annonce deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        JsonObject json = jsonElement.getAsJsonObject();
        FactoryItem f = new FactoryItem();
        String typeItem = json.get("typeItem").getAsString();
        Item i = f.getInstanceItem(typeItem);
        // TODO : annonce json .get Terrain  va être modifier en item

        Item terr = (Item) new Gson().fromJson(json.get("terrain"), i.getClass());
        System.out.println("tterain "+ terr.getAdresse());
        String datedebut = json.get("dateDisponible").getAsString();
        // TODO : peut etre modifier, le format de date
        SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a", Locale.US);
        java.util.Date tmpDateDebut =new Date();
        java.util.Date tmpCreneau=new Date();
        try {
            tmpDateDebut = ft.parse(datedebut);
            tmpCreneau= ft.parse(json.get("creneau").getAsString());
        }catch (Exception e) {};
        Annonce annonce = new Annonce();
        annonce.setDateDisponible(tmpDateDebut);
        annonce.setTerrain(terr);
        annonce.setTypeItem(typeItem);
        annonce.setCreneau(tmpCreneau);
        annonce.setIdUser(json.get("idUser").getAsInt());
        annonce.setNombreDePlaceRestant(json.get("nombreDePlaceRestant").getAsInt());
        return annonce;
    }
}