package mysport;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {

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
        Gson gsb = new  GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserialize()).create();

        Annonce maAn = gsb.fromJson(receivedannonce,Annonce.class);
        System.out.println(maAn.getTerrain().getVille());


        ArrayList<Annonce> annonces = new ArrayList<Annonce>();
        annonces.add(ad);
        annonces.add(ad2);

        String receivedannonces= gson.toJson(annonces);
        Type listType2 = new TypeToken<ArrayList<AnnonceDeserialize>>(){}.getType();
        ArrayList<Annonce> annonces2 = gsb.fromJson(receivedannonces, listType2);
        System.out.println(annonces2.size());

        }

    }

