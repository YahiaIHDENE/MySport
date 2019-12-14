package mysport;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Path("/")
public class UserHandler{

    private static final String url = "jdbc:mysql://localhost:9002";
    private static final String user = "user";
    private static final String password = "test";
    private PreparedStatement ps;
    private Connection conn;
    private Gson gson;

    public UserHandler(){
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
    @Path("/adduser/{user}")
    public int addUser(@PathParam("user") String user) {
        User received_user = gson.fromJson(user, User.class);
	//String test = "SELECT COUNT(*) FROM mysportdb.t_user WHERE user_mail ='"+received_user.getEmail()+"'";

        String INSERT = "INSERT INTO myDb.t_user (user_first_name," +
                                                     " user_last_name," +
                                                     " user_mail," +
                                                     " user_password," +
                                                     " user_tel)" +
                                         " VALUES ('"+ received_user.getFirstname() + "', '"+
                                                      received_user.getLastname() + "', '"+
                                                      received_user.getEmail() + "', '"+
                                                      received_user.getPW() + "', '"+
                                                      received_user.getNumber() + "')";

        System.out.println(INSERT);
        try {
            /* ps.conn.prepareStatement(test);
             ResultSet rs= ps.executeQuery();
	     while(rs.next()){
        		count=rs.getInt(1);
			if (count>0){
				return 0;
			}
			else{....
		*/
	ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
            return -1;
        }
    }

    @GET
    @Path("/fetchuser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchUser(@PathParam("id") String id){
        Integer id_user = gson.fromJson(id, Integer.class);
        String SELECT = "SELECT * FROM myDb.t_user WHERE id_user="+id_user.intValue();
        try {
            ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                User user = new User(rs.getString("user_last_name"), rs.getString("user_last_name"), rs.getString("user_mail"), rs.getString("user_tel"), rs.getString("user_password"));
                user.setId(Integer.parseInt(rs.getString("id_user")));
                return gson.toJson(user);
            } else {
                return gson.toJson(new String("Can't construct object user"));
            }
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"));
        }
    }


    @GET
    @Path("/addAnnonce/{annonce}")
    public String addAnnonce(@PathParam("annonce") String annonce) {
        Gson gson = new Gson();
        System.out.println(annonce);
        // get json objet
        JsonObject json = gson.fromJson(annonce, JsonObject.class);
        //get id user
        int id_user = json.get("idUser").getAsInt();

        //get date debut date fin --> meme
        String datedebut = json.get("dateDisponible").getAsString();
        String datefin = json.get("dateDisponible").getAsString();
        String heure_debut = json.get("creneau").getAsString();
        String heure_fin = json.get("creneau").getAsString();


        SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy, hh:mm:ss a", Locale.US);
        try {
            java.util.Date tmpDateDebut = ft.parse(datedebut);
            java.util.Date tmpDateFin = ft.parse(datedebut);
            java.util.Date tmpHeureDebut = ft.parse(datedebut);
            java.util.Date tmpHeureFin = ft.parse(datedebut);

            ft.applyPattern("yyyy-MM-dd");
            datedebut = ft.format(tmpDateDebut);
            datefin = ft.format(tmpDateFin);
            ft.applyPattern("hh:mm:ss");
            heure_debut = ft.format(tmpHeureDebut);
            heure_fin = ft.format(tmpHeureFin);
        } catch (ParseException ex) {
            ex.getMessage();
        }
        // get Terrain and construit terrain
        FactoryItem f = new FactoryItem();
        String typeItem = json.get("typeItem").getAsString();
        Item i = f.getInstanceItem(typeItem);
        Item terr = (Item) gson.fromJson(json.get("terrain"), i.getClass());


        String INSERT = "INSERT INTO myDb.t_annonce ( id_user, jour_debut,jour_fin, heure_debut, heure_fin) VALUES " +
                "(" + id_user + ","
                + "'" + datedebut + "',"
                + "'" + datefin + "',"
                + "'" + heure_debut + "',"
                + "'" + heure_fin + "')";

        System.out.println(INSERT);
        String Insert2="";



        // String INSERT = "INSERT INTO myDb.t_annonce ( id_user, jour_debut, heure_debut, heure_fin, jour_fin) VALUES ( '1'," +
        //       " '2019-12-12', '10:00:00', '18:00:00', '2019-12-15')";
        // System.out.println(INSERT);
        System.out.println(annonce);
        int id_annonce = 0;

        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();

            String SELECT = "Select max(id_annonce) from myDb.t_annonce";
            ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id_annonce = rs.getInt(1);
                System.out.println(id_annonce);
            }
            if (typeItem.equals("Terrain")) {
                Insert2 = "INSERT INTO myDb.t_terrain (id_annonce, adresse, ville, code_postal, capacite, type_sport) " +
                        "VALUES ("+
                        id_annonce +
                        ",'" + terr.getAdresse()     + "'," +
                        "'" + terr.getVille()       + "'," +
                        "'" + terr.getCodePostal()  + "'," +
                         + terr.getCapacity()    + "," +
                        "'" + terr.getTypeSport()   + "')";

                ps = conn.prepareStatement(Insert2);
                ps.execute();

            }

            return "success";
        } catch (Exception e) {

            return ( e.getMessage() + INSERT +"<br>"+ id_annonce+Insert2);
        }
    }

    @GET
    @Path("/deletAnnonce/{id}")
    public int deleteUser(@PathParam("id") String id) {

        Integer id_annonce = gson.fromJson(id, Integer.class);
        if (id_annonce<0) return -1;
        String  DELETE = "Delete FROM myDb.t_annonce WHERE myDb.t_annonce.id_annonce= " + id_annonce;
        try {
            ps = conn.prepareStatement(DELETE);
            ps.execute();
            return 1;
        } catch (Exception e){
            return -1;
        }
    }



}
