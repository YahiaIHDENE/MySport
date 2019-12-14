package mysport;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class test {

    static class AnnonceD implements  JsonDeserializer<Annonce>{
        // pour s'adpter au tout type de item(objet abstrait), on utilise cette classe décerialisation de json, qui converti les string json en un annonce
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
            java.util.Date tmpDateDebut =new  Date();
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

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException {
        /*espace de test de serialisation et deserilisation */
        Gson gson =new Gson();
        User a = new User("a","b","c","d","5");
        System.out.println(gson.toJson(a));
        User b= new User("a","b","c","d","5");
        List<User> users = new ArrayList<User>();
        users.add(a);
        users.add(b);
        String received_users= gson.toJson(users);
        // Specifier la listType pour une collector de type user
        Type listType = new TypeToken<ArrayList<User>>(){}.getType();
        List<User> my= new Gson().fromJson(received_users,listType);
        System.out.println(my.get(1).getEmail());
        Item terrain = new Terrain("sport1 ","dd","ddd","ddd",5, "aaa");

        Date aujourdhui = new Date();
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formater.format(aujourdhui));
        Annonce ad = new Annonce(aujourdhui,aujourdhui,terrain);
        Annonce ad2 = new Annonce(aujourdhui,aujourdhui,terrain);

        String receivedannonce= gson.toJson(ad2);
        Gson gsb = new  GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceD()).create();

        Annonce maAn = gsb.fromJson(receivedannonce,Annonce.class);
        System.out.println(maAn.getTerrain().getVille());


        ArrayList<Annonce> annonces = new ArrayList<Annonce>();
        annonces.add(ad);
        annonces.add(ad2);

        String receivedannonces= gson.toJson(annonces);
        Type listType2 = new TypeToken<ArrayList<AnnonceD>>(){}.getType();
        ArrayList<Annonce> annonces2 = gsb.fromJson(receivedannonces, listType2);
        System.out.println(annonces2.size());

        }

    }

