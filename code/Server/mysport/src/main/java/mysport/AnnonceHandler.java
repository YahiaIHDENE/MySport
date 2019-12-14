package mysport;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Path("/")
public class AnnonceHandler{

    private static final String url = "jdbc:mysql://localhost:9002";
    private static final String user = "user";
    private static final String password = "test";
    private PreparedStatement ps;
    private Connection conn;
    private Gson gson;

    public AnnonceHandler() throws ParseException {
        gson = new Gson();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/addAnnonce/{annonce}")
    public String addAnnonce(@PathParam("annonce") String annonce){
        Gson gson = new Gson();
        System.out.println(annonce);
        // get json objet
        JsonObject json  =  gson.fromJson(annonce,JsonObject.class);
        //get id user
        int id_user = json.get("idUser").getAsInt();

        //get date debut date fin --> meme
        String datedebut = json.get("dateDisponible").getAsString();
        String datefin = json.get("dateDisponible").getAsString();
        String heure_debut = json.get("creneau").getAsString();
        String heure_fin = json.get("creneau").getAsString();


        SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a", Locale.US);
        try {
            java.util.Date tmpDateDebut= ft.parse(datedebut);
            java.util.Date tmpDateFin= ft.parse(datedebut);
            java.util.Date tmpHeureDebut= ft.parse(datedebut);
            java.util.Date tmpHeureFin= ft.parse(datedebut);

            ft.applyPattern("yyyy-MM-dd");
            datedebut = ft.format(tmpDateDebut);
            datefin = ft.format(tmpDateFin);
            ft.applyPattern("hh:mm:ss");
            heure_debut=ft.format(tmpHeureDebut);
            heure_fin=ft.format(tmpHeureFin);
        }catch (ParseException ex){
            ex.getMessage();
        }
        // get Terrain and construit terrain
        FactoryItem f= new FactoryItem();
        Item i = f.getInstanceItem(json.get("typeItem").getAsString());
        Item terr = (Item) gson.fromJson(json.get("terrain"),i.getClass());


        String INSERT = "INSERT INTO myDb.t_annonce ( id_user, jour_debut,jour_fin, heure_debut, heure_fin) VALUES " +
                "("+ id_user    +","
                +"'"+datedebut  +"',"
                +"'"+datefin    +"',"
                +"'"+heure_debut+"',"
                +"'"+heure_fin  +"')";

        System.out.println(INSERT);

        return INSERT;
    }


    /*

        String INSERT = "INSERT INTO myDb.t_annonce ( id_user, jour_debut, heure_debut, heure_fin, jour_fin) VALUES ( '1'," +
                " '2019-12-12', '10:00:00', '18:00:00', '2019-12-15')";
        System.out.println(INSERT);
        System.out.println(annonce);

        return gson.toJson(INSERT);
*/
        /*try{
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        }    catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            return -1;
        }*/



}
