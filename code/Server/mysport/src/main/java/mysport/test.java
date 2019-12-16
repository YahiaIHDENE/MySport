package mysport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, ParseException {
        /*espace de test de serialisation et deserilisation */
        Gson gson = new Gson();
        // creer 2 item
        Terrain terrain = new Terrain("sport1 ", "dd", "ddd", "ddd", 5, "aaa");
        Equipement eqq = new Equipement("d","cd","dd","ddd","dd","description",7,1);
        // creer 2 item avec 2 type
        Annonce ad =  new Annonce("12-15-2019","155","4545",5,1,1,terrain,"Terrain");
        Annonce ad2 = new Annonce("12-15-2019","155","4545",5,1,1,eqq,"Equipement");
        // transformée
        String receivedannonce = gson.toJson(ad2);
        System.out.println("annonce seurl "+receivedannonce +"......");

        Equipement equipement=(Equipement)ad2.getItem();
        System.out.println("Ser "+equipement.getDescription());

        Gson gsb = new GsonBuilder().registerTypeAdapter(Annonce.class, new AnnonceDeserialize()).create();
        String jsonannonce = gsb.toJson(ad2);
        String jsa2= gson.toJson(ad2);
        if ( jsa2.equals(jsonannonce)){ System.out.println("json de gson et gsb custome est la meme : true");}

        Annonce maAn = gsb.fromJson(receivedannonce, Annonce.class);
        ArrayList<Annonce> annonces = new ArrayList<Annonce>();
        annonces.add(ad);
        annonces.add(ad2);
        String receivedannonces = gson.toJson(annonces);
        System.out.println(receivedannonces);

        Type listType = new TypeToken<List<Annonce>>(){}.getType();
        ArrayList<Annonce> mesAnnoncesApresJson = gsb.fromJson(receivedannonces, listType);
        Equipement aaa= (Equipement) annonces.get(1).getItem();
        if (annonces.get(1).getItem() instanceof Equipement){System.out.println("true "+gson.toJson(aaa.getDescription()));}
        Recherche recherche = new Recherche("2019-12-13","08:03:28","valeur 2","foot","Terrain");
        String rechercheString = gsb.toJson(recherche ,Recherche.class);
        System.out.println("recherche"+rechercheString);
        RechercheBuilder rechercheBuilder = new RechercheBuilder();
        if (   recherche.getTypeItem() != null ||recherche.getTypeItem()!=""){
            rechercheBuilder.setTypeItem(recherche.getTypeItem());
        }

        if (   recherche.getDate() != null ||recherche.getDate()!=""){
            rechercheBuilder.whereDate(recherche.getDate());
        }

        if (   recherche.getHeureDebut() != null ||recherche.getHeureDebut()!=""){
            rechercheBuilder.whereHeure_debut(recherche.getHeureDebut());
        }

        if (   recherche.getTypeSport() != null ||recherche.getTypeSport()!=""){
            rechercheBuilder.whereTypeSport(recherche.getTypeSport());
        }

        if (   recherche.getVille() != null ||recherche.getVille()!=""){
            rechercheBuilder.whereVille(recherche.getVille());
        }

        String querry = rechercheBuilder.getQuerry();

        /*

        String c= gson.toJson(aaa.getDescription());
        String d = gsb.toJson(annonces.get(1));
        System.out.println("annonce perso :"+d);
        System.out.println("json equipement :\n"+c);
        System.out.println(gson.toJson(annonces2));

        System.out.println(annonces2.size());
        String date = "2018-02-08";
        String time = "08:01:02";

/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(date);
        SimpleDateFormat sdf2 = new SimpleDateFormat("hh:mm:ss");

        Date d2 = sdf2.parse(time);

        System.out.println(d1.toString());
        System.out.println(d2.toString());

                Gson gson = new Gson();
        User a = new User("a", "b", "c", "d", "5");
        System.out.println(gson.toJson(a));
        User b = new User("a", "b", "c", "d", "5");
        List<User> users = new ArrayList<User>();
        users.add(a);
        users.add(b);
        String received_users = gson.toJson(users);
        // Specifier la listType pour une collector de type user
        Type listType = new TypeToken<ArrayList<User>>() {
        }.getType();
        List<User> my = new Gson().fromJson(received_users, listType);
        System.out.println(my.get(1).getEmail());
                Date aujourdhui = new Date();
        SimpleDateFormat formater = null;
        formater = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println(formater.format(aujourdhui));
*/

        RechercheBuilder querryB = new RechercheBuilder();
       // querry = querryB.setTypeItem("Terrain").whereNombreDePlaceRestant(5).
         //       whereDate("2019-12-13").whereTypeSport("foot").whereVille("valeur 2").whereCreneau("08:03:28").getQuerry();

        System.out.println(querry);



    }

    }

