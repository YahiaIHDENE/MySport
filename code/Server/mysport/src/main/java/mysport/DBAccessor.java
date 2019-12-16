package mysport;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/")
public class DBAccessor{

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "hakim";
    private static final String password = "12345678";
    private PreparedStatement ps;
    private Connection conn;
    private Gson gson;

    public DBAccessor(){
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
        String INSERT = "INSERT INTO mysport.t_user (user_first_name," +
                                                     " user_last_name," +
                                                     " user_mail," +
                                                     " user_password," +
                                                     " user_tel)" +
                                         " VALUES ('"+ received_user.getFirstname() + "', '"+
                                                      received_user.getLastname() + "', '"+
                                                      received_user.getEmail() + "', '"+
                                                      received_user.getPW() + "', '"+
                                                      received_user.getNumber() + "')";
        try {
	        ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        } catch (Exception e){
            return -1;
        }
    }

    @GET
    @Path("/fetchuser/{email}/{pw}")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchUser(@PathParam("email") String email, @PathParam("pw") String pw){

        String user_email = gson.fromJson(email, String.class);
        String user_pw = gson.fromJson(pw, String.class);

        String SELECT = "SELECT * FROM mysport.t_user WHERE user_mail='"+user_email+"' AND user_password='"+user_pw+"'";
        try {
            ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                User user = new User(rs.getString("user_last_name"), rs.getString("user_first_name"), rs.getString("user_mail"), rs.getString("user_tel"), rs.getString("user_password"));
                user.setId(Integer.parseInt(rs.getString("id_user")));
                return gson.toJson(user);
            } else {
                return gson.toJson(null);
            }
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(null);
        }
    }

    @GET
    @Path("/updateuser/{user}")
    public int updateUser(@PathParam("user") String user) {
        User received_user = gson.fromJson(user, User.class);
        String INSERT = "UPDATE mysport.t_user SET user_first_name= '"    + received_user.getFirstname() +"'," +
                                                   " user_last_name = '"    + received_user.getLastname() + "'," +
                                                   " user_password = '"     + received_user.getPW() + "'," +
                                                   " user_tel = '"          + received_user.getNumber() + "'" +
                                              "WHERE id_user = "            + received_user.getId();
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        } catch (Exception e){
            return -1;
        }
    }

    //##########################################################################################################################//
    // Partie Reservation d'annonce//
    //##########################################################################################################################//

    // Tested
    @GET
    @Path("/reserve/addReservation/{addReservation}")
    public String addReservation(@PathParam("addReservation") String reservation) {
        Reservation received_reservation = gson.fromJson(reservation, Reservation.class);
        //Reservation received_reservation = new Reservation("12-12-2022", 1, 3,3,"12:00","14:00");
        //received_annonce.setId_reserve(0);received_annonce.id_annonce=1,received_annonce.id_user=5,received_annonce.nombreDePlace=6,received_annonce.date='2020-05-06',received_annonce.heure_debut='16:00:00',received_annonce.heure_fin='18:00:00'
        String INSERT = "INSERT INTO mysport.t_reserve (id_annonce," +
                                                        " id_user," +
                                                        " nombre_reserve," +
                                                        " jour_reserve," +
                                                        " heure_debut," +
                                                        " heure_fin)" +
                                          " VALUES (" + received_reservation.getIdAnnonce() + "," +
                                                        received_reservation.getIdUser() + "," +
                                                        received_reservation.getNombreDePlace() + ", '" +
                                                        received_reservation.getDate() + "', '" +
                                                        received_reservation.getHeureDebut() + "', '" +
                                                        received_reservation.getHeureFin() + "') ";
        int nombre_place = 0;
        int diff = 0;
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            String SELECT = "SELECT place_restant FROM mysport.t_annonce WHERE id_annonce= '" + received_reservation.getIdAnnonce() + "'";
            try {
                ps = conn.prepareStatement(SELECT);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nombre_place = rs.getInt("place_restant");
                    diff = nombre_place - received_reservation.getNombreDePlace();
                    if(diff<0)
                        return"Nombre de places insufisant "+nombre_place;

                }
                String Update = "UPDATE  mysport.t_annonce SET place_restant=" + diff + " WHERE id_annonce='" + received_reservation.getIdAnnonce() + "'";
                try {
                    ps = conn.prepareStatement(Update);
                    ps.execute();
                    return "Reservation confirmé";
                } catch (Exception e) {
                    return "Error !";
                }
            } catch (Exception e) {
                return "Error !";
            }
        } catch (Exception e) {
            return "Error !";
        }
    }

    // Not Tested
    @GET
    @Path("/reserve/mesReservation/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String mesReservation(@PathParam("id_user") String id_user) {
        //int id_user = 5;
        int idUserReservation = gson.fromJson(id_user, int.class);
        List<Reservation> listReservation = new ArrayList<Reservation>();
        String SELECT = "SELECT * FROM mysport.t_reserve WHERE id_user='"+idUserReservation+"'";
        ResultSet rs;
        try {
            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            // ResultSetMetaData rsmd = rs.getMetaData();
            rs.next();
            while(rs.next()) {
                Reservation rez = new Reservation();
                rez.setNombreDePlace(rs.getInt("nombre_reserve"));
                rez.setDate(rs.getString("jour_reserve"));
                rez.setHeureDebut(rs.getString("heure_debut"));
                rez.setHeureFin(rs.getString("heure_fin"));
                rez.setIdAnnonce(rs.getInt("id_annonce"));
                rez.setIdUser(rs.getInt("id_user"));
                rez.setIdReservation(rs.getInt("id_reserve"));
                listReservation.add(rez);
            }
            return (gson.toJson(listReservation));
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"));
        }
    }

    // Tested
    @GET
    @Path("/reserve/editReservation/{reservation}")
    public String editReservation(@PathParam("reservation") String reservation) {
        Reservation update_reservation = gson.fromJson(reservation, Reservation.class);
        int placeRestant=0,ancienreserve=0,nvx=0;
        String SELECT2 = "SELECT place_restant FROM mysport.t_annonce WHERE id_annonce="+update_reservation.getIdAnnonce() ;
        try {
            ps = conn.prepareStatement(SELECT2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                placeRestant = rs.getInt("place_restant");
            }
            String SELECT = "SELECT nombre_reserve FROM mysport.t_reserve WHERE id_reserve="+update_reservation.getIdReservation();
            try {
                ps = conn.prepareStatement(SELECT);
                ResultSet rs1 = ps.executeQuery();
                if (rs1.next()) {
                    ancienreserve = rs1.getInt("nombre_reserve");
                    nvx = placeRestant + ancienreserve-update_reservation.getNombreDePlace();
                    if(nvx<0)
                        return"Nombre de places insufisant le MAX="+placeRestant;
                }
                String UPDATE = "UPDATE mysport.t_reserve SET nombre_reserve='" + update_reservation.getNombreDePlace() + "'  WHERE id_reserve=" + update_reservation.getIdReservation();
                try {
                    ps = conn.prepareStatement(UPDATE);
                    ps.execute();
                    String Update = "UPDATE  mysport.t_annonce SET place_restant=" + nvx + " WHERE id_annonce=" + update_reservation.getIdAnnonce();
                    try {
                        ps = conn.prepareStatement(Update);
                        ps.execute();
                        return "modification reussi!";
                    } catch (Exception e) {
                        return "Error !";
                    }
                } catch (Exception e) {
                    return "Error !";
                }
            } catch (Exception e) {
                return "Error !";
            }
        }catch (Exception e){
            return "Error !"; }
    }

    // Tested
    @GET
    @Path("/reserve/deleteReservation/{deleteReservation}")
    public String deleteReservation( @PathParam("deleteReservation") String reservation) {
        Reservation received_reservation1 = gson.fromJson(reservation, Reservation.class);
        // Reservation received_reservation1 = new Reservation("12-12-2022", "12:00", "14:00",3,1,3,15);

        int idannonce =0;
        int placeRestant =0;
        int nvx=0;
        String SELECT = "SELECT id_annonce FROM mysport.t_reserve WHERE id_reserve="+ received_reservation1.getIdReservation();
        try {
            ps = conn.prepareStatement(SELECT);
            ResultSet rs_id = ps.executeQuery();
            if (rs_id.next()) {
                idannonce = rs_id.getInt("id_annonce");
            }
            String SELECT2 = "SELECT place_restant FROM mysport.t_annonce WHERE id_annonce=" + idannonce ;
            try {
                ps = conn.prepareStatement(SELECT2);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    placeRestant = rs.getInt("place_restant");
                    nvx = placeRestant + received_reservation1.getNombreDePlace();
                }
                String Update = "UPDATE  mysport.t_annonce SET place_restant=" + nvx + " WHERE id_annonce="+ idannonce;
                try {
                    ps = conn.prepareStatement(Update);
                    ps.execute();
                    String DELETE = "DELETE FROM mysport.t_reserve WHERE id_reserve=" + received_reservation1.getIdReservation();
                    try {
                        ps = conn.prepareStatement(DELETE);
                        ps.execute();
                    } catch (Exception e) {
                        return "Error !";
                    }
                    return "Annulation confirmé";
                } catch (Exception e) {
                    return "Error !";
                }
            } catch (Exception e) {
                return "Error !";
            }
        } catch (Exception e){
            return "Error !";
        }
    }
    //##########################################################################################################################//
    // FIN de la partie Reservation d'annonce//
    //##########################################################################################################################//


    //##########################################################################################################################//
    // Partie Ajouter d'annonce//
    //##########################################################################################################################//

    // Tested
    @GET
    @Path("/annonce/addAnnonce/{addAnnonce}")
    public String addAnnonce(@PathParam("addAnnonce")String annonce) {

        Gson gsb = new GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserializer()).create();
        Annonce received_annonce = gsb.fromJson(annonce,Annonce.class);
        int idterrain =0;
        String INSERT = "INSERT INTO mysport.t_terrain(adresse," +
                                                        "ville," +
                                                        "code_postal," +
                                                        "capacite," +
                                                        "nom_terrain," +
                                                        "type_sport)" +
                                                        "VALUES" +
                                                        "(" +
                                                        "'"+received_annonce.getTerrain().getAdresse()+"',"+
                                                        "'"+received_annonce.getTerrain().getVille()+ "',"+
                                                        "'"+received_annonce.getTerrain().getCodePostal()+"'," +
                                                        "'"+received_annonce.getTerrain().getCapacity()+"'," +
                                                        "'"+received_annonce.getTerrain().getNom()+"'," +
                                                        "'"+received_annonce.getTerrain().getTypeSport()+"') ";
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            String SELECT = "SELECT id_terrain FROM mysport.t_terrain WHERE adresse = '" +(received_annonce.getTerrain().getAdresse()) + "'AND code_postal='"+(received_annonce.getTerrain().getCodePostal())+"'AND type_sport='"+(received_annonce.getTerrain().getTypeSport())+"'";
            try {
                ps = conn.prepareStatement(SELECT);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    idterrain=rs.getInt("id_terrain");
                }
                String INSERT2 = "INSERT INTO mysport.t_annonce(id_user,jour_debut,heure_debut,heure_fin,place_restant,id_terrain) VALUES(" +
                                                                "'"+received_annonce.getId_userPublier()+"'," +
                                                                "'"+received_annonce.getDateDisponible()+"'," +
                                                                "'"+received_annonce.getHeureDebut()+"'," +
                                                                "'"+received_annonce.getHeureFin()+"'," +
                                                                "'"+received_annonce.getNombreDePlaceRestant()+"'," +
                                                                "'"+idterrain+"')";
                try {
                    ps = conn.prepareStatement(INSERT2);
                    ps.execute();
                    return "Depot d'annonce confirmé confirmé";
                } catch (Exception e) {
                    return "Error !";
                }
            } catch (Exception e) {
                return "Error !";
            }
        } catch (Exception e) {
            return "Error !";
        }
    }

    // Tested
    @GET
    @Path("/annonce/mesAnnonces/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMesAnnonces(@PathParam("id_user") String id){
        Integer id_user = gson.fromJson(id, Integer.class);
        String SELECT = "SELECT * FROM mysport.t_annonce INNER JOIN mysport.t_terrain WHERE mysport.t_terrain.id_terrain = mysport.t_annonce.id_terrain " +
                                                                                          "and mysport.t_annonce.id_user ="+ id_user;
        ArrayList<Annonce> mesAnnonces = new ArrayList<Annonce>();
        ResultSet rs;
        try {
            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            String typeItem = "Terrain";
            while (rs.next()){
                Annonce a= new Annonce();
                FactoryItem f = new FactoryItem();
                Item terr = f.getInstanceItem(typeItem);
                terr.setAdresse(rs.getString("adresse"));
                terr.setCapacity(rs.getInt("capacite"));
                terr.setCodePostal(rs.getString("code_postal"));
                terr.setTypeSport(rs.getString("type_sport"));
                terr.setId(rs.getInt("id_terrain"));
                terr.setVille(rs.getString("ville"));

                a.setIdAnnonce(rs.getInt("id_annonce"));
                a.setId_userPublier(rs.getInt("id_user"));
                a.setHeureFin(rs.getString("heure_debut"));
                a.setHeureFin(rs.getString("heure_fin"));
                a.setNombreDePlaceRestant(rs.getInt("place_restant"));
                a.setTerrain(terr);
                mesAnnonces.add(a);
            }
            String json= gson.toJson(mesAnnonces);
            return (json );
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"+e.getMessage()));
        }
    }

    // Tested
    @GET
    @Path("/annonce/toutesAnnonces")
    @Produces(MediaType.APPLICATION_JSON)
    public String getALLAnnonces(){

        String SELECT = "SELECT * FROM mysport.t_annonce INNER JOIN mysport.t_terrain WHERE mysport.t_terrain.id_terrain = mysport.t_annonce.id_terrain ";
        ArrayList<Annonce> mesAnnonces = new ArrayList<Annonce>();
        ResultSet rs =null;
        String rsaa = null;

        try {
            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            String typeItem = "Terrain";
            while (rs.next()){
                Annonce a= new Annonce();
                FactoryItem f = new FactoryItem();
                Item terr = f.getInstanceItem(typeItem);

                terr.setAdresse(rs.getString("adresse"));
                terr.setCapacity(rs.getInt("capacite"));
                terr.setCodePostal(rs.getString("code_postal"));
                terr.setTypeSport(rs.getString("type_sport"));
                terr.setId(rs.getInt("id_terrain"));
                terr.setVille(rs.getString("ville"));

                a.setTypeItem("Terrain");
                a.setIdAnnonce(rs.getInt("id_annonce"));
                a.setId_userPublier(rs.getInt("id_user"));
                a.setDateDisponible(rs.getString("jour_debut"));
                a.setHeureDebut(rs.getString("heure_debut"));
                a.setHeureFin(rs.getString("heure_fin"));
                a.setNombreDePlaceRestant(rs.getInt("place_restant"));
                a.setTerrain(terr);
                mesAnnonces.add(a);
            }
            String json= gson.toJson(mesAnnonces);
            return (json);
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"+e.getMessage())) ;
        }

    }

    //##########################################################################################################################//
    // Partie Recherche//
    //##########################################################################################################################//

    // Tested
    @GET
    @Path("/annonce/recherche/{recherche}")
    @Produces(MediaType.APPLICATION_JSON)
    public String recherche(@PathParam("recherche") String recherchejson){
        Recherche recherche = gson.fromJson(recherchejson, Recherche.class);
        ResultSet rs =null;
        ArrayList<Annonce> resultatAnnonces = new ArrayList<Annonce>();
        String SELECT = "SELECT * FROM mysport.t_annonce INNER JOIN mysport.t_terrain WHERE " +
                                                        "jour_debut='"+recherche.getDate()+" 'AND" +
                                                        " heure_debut='"+recherche.getHeureDebut()+" 'AND" +
                                                        " ville ='"+recherche.getVille()+" 'AND" +
                                                        " type_sport='" +recherche.getTypeSport()+"' AND mysport.t_annonce.id_terrain=mysport.t_terrain.id_terrain";
        try {
            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            String typeItem = "Terrain";
            while (rs.next()){
                Annonce a= new Annonce();
                FactoryItem f = new FactoryItem();
                Item terr = f.getInstanceItem(typeItem);

                terr.setAdresse(rs.getString("adresse"));
                terr.setCapacity(rs.getInt("capacite"));
                terr.setCodePostal(rs.getString("code_postal"));
                terr.setTypeSport(rs.getString("type_sport"));
                terr.setId(rs.getInt("id_terrain"));
                terr.setVille(rs.getString("ville"));

                a.setIdAnnonce(rs.getInt("id_annonce"));
                a.setId_userPublier(rs.getInt("id_user"));
                a.setHeureFin(rs.getString("heure_debut"));
                a.setHeureFin(rs.getString("heure_fin"));
                a.setNombreDePlaceRestant(rs.getInt("place_restant"));
                a.setTerrain(terr);

                resultatAnnonces.add(a);
            }
            String json= gson.toJson(resultatAnnonces);
            return (json );
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"+e.getMessage()));
        }
    }

    // Tested
    @GET
    @Path("/annonce/deleteAnnonce/{id}")
    public int deleteUser(@PathParam("id") String id) {

        int id_annonce = gson.fromJson(id, int.class);
        if (id_annonce<0) return -1;
        String  DELETE = "Delete FROM mysport.t_annonce WHERE mysport.t_annonce.id_annonce= " + id_annonce;
        try {
            ps = conn.prepareStatement(DELETE);
            ps.execute();
            return 1;
        } catch (Exception e){
            return -1;
        }
    }
}