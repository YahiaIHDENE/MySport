package mysport;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/")
public class UserHandler {

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "mysportuser";
    private static final String password = "";
    private PreparedStatement ps;
    private Connection conn;
    private Gson gson;

    public UserHandler() {
        gson = new Gson();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/adduser/{user}")
    public int addUser(@PathParam("user") String user) {
        User received_user = gson.fromJson(user, User.class);
        String INSERT = "INSERT INTO mysportdb.t_user (user_first_name," +
                " user_last_name," +
                " user_mail," +
                " user_password," +
                " user_tel)" +
                " VALUES ('" + received_user.getFirstname() + "', '" +
                received_user.getLastname() + "', '" +
                received_user.getEmail() + "', '" +
                received_user.getPW() + "', '" +
                received_user.getNumber() + "')";
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        } catch (Exception e) {
            return -1;
        }
    }

    @GET
    @Path("/fetchuser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchUser(@PathParam("id") String id) {
        Integer id_user = gson.fromJson(id, Integer.class);
        String SELECT = "SELECT * FROM mysportdb.t_user WHERE id_user=" + id_user.intValue();
        try {
            ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("user_last_name"), rs.getString("user_last_name"), rs.getString("user_mail"), rs.getString("user_tel"), rs.getString("user_password"));
                user.setId(Integer.parseInt(rs.getString("id_user")));
                return gson.toJson(user);
            } else {
                return gson.toJson(new String("Can't construct object user"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return gson.toJson(new String("Failure"));
        }
    }

    //##########################################################################################################################//
                                                 // Partie Reservation d'annonce//
    //##########################################################################################################################//
    @GET
    @Path("/reserve/addReservation/{addReservation}")
    public String addReservation(@PathParam("addReservation") String reservation) {
        Reservation received_reservation = gson.fromJson(reservation, Reservation.class);
        System.out.println("reservation : web ok !!!!!");
        System.out.println(received_reservation.getIdAnnonce()+"\n"+ received_reservation.getIdReservation()+"\n"+received_reservation.getNombreDePlace());
        //Reservation received_reservation = new Reservation("12-12-2022", 1, 3,3,"12:00","14:00");
        //received_annonce.setId_reserve(0);received_annonce.id_annonce=1,received_annonce.id_user=5,received_annonce.nombreDePlace=6,received_annonce.date='2020-05-06',received_annonce.heure_debut='16:00:00',received_annonce.heure_fin='18:00:00'

        String INSERT = "INSERT INTO mysportdb.t_reserve (id_annonce," +
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
/*
        String INSERT= "INSERT INTO mysportdb.t_reserve (id_annonce," +
                " id_user," +
                " nombre_reserve," +
                " jour_reserve," +
                " heure_debut," +
                " heure_fin)" +
                " VALUES ('"+ 1+ "', '"+ 5 + "', '"+6+ "', '"+"2019-12-22"+ "', '"+ "18:00:00" + "', '"+"22:00:00" + "')";
       */
        int nombre_place = 0;
        int diff = 0;
        try {
            System.out.println("reservation : 1er !!!!!");

            ps = conn.prepareStatement(INSERT);
            ps.execute();
            System.out.println("reservation : 1er try ok  ps!!!!!");
            String SELECT = "SELECT place_restant FROM mysportdb.t_annonce WHERE id_annonce= '" + received_reservation.getIdAnnonce() + "'";
            System.out.println("reservation : 1er try ok !!!!!");

            try {
                System.out.println("reservation : 2eme  !!!!!");

                ps = conn.prepareStatement(SELECT);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    nombre_place = rs.getInt("place_restant");
                    diff = nombre_place - received_reservation.getNombreDePlace();
                    if(diff<0)
                        return"Nombre de places insufisant "+nombre_place;

                }
                System.out.println("reservation : 2eme try ok ps !!!!!");

                String Update = "UPDATE  mysportdb.t_annonce SET place_restant=" + diff + " WHERE id_annonce='" + received_reservation.getIdAnnonce() + "'";
                System.out.println("reservation : 2eme try ok !!!!!");

                try {
                    System.out.println("reservation : 3eme  !!!!!");

                    ps = conn.prepareStatement(Update);
                    ps.execute();
                    System.out.println("reservation : 3eme try ok ps !!!!!");

                    System.out.println("reserver =>  = " + nombre_place);
                    System.out.println("place restant  = " + diff);
                    System.out.println("reservation : 3eme try ok !!!!!");

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


    @GET
    @Path("/reserve/mesReservation/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String mesReservation(@PathParam("id_user") String id_user) {
        //int id_user = 5;
        int idUserReservation = gson.fromJson(id_user, int.class);

        System.out.println("reservation : web ok !!!!!");
        List<Reservation> listReservation = new ArrayList<Reservation>();
        String SELECT = "SELECT * FROM mysportdb.t_reserve WHERE id_user='"+idUserReservation+"'";
        System.out.println(SELECT);
        ResultSet rs = null;
        try {

            ps = conn.prepareStatement(SELECT);
             rs = ps.executeQuery();
           // ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("je suis rentré RQT ok!!");
            rs.next();
            System.out.println(rs.getString("jour_reserve"));

            while(rs.next()) {
                System.out.println("je suis rentré  2222!!");

                    Reservation rez = new Reservation();
                    rez.setNombreDePlace(rs.getInt("nombre_reserve"));
                    rez.setDate(rs.getString("jour_reserve"));
                    rez.setHeureDebut(rs.getString("heure_debut"));
                    rez.setHeureFin(rs.getString("heure_fin"));
                    rez.setIdAnnonce(rs.getInt("id_annonce"));
                    rez.setIdUser(rs.getInt("id_user"));
                    rez.setIdReservation(rs.getInt("id_reserve"));
                    listReservation.add(rez);

                System.out.println(gson.toJson(listReservation));
            }
                return (gson.toJson(listReservation));
        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"));
        }

    }





    @GET
    @Path("/reserve/editReservation/{reservation}")
    public String editReservation(@PathParam("reservation") String reservation) {
        System.out.println("reservation : web ok !!!!!");
        Reservation update_reservation = gson.fromJson(reservation, Reservation.class);
        System.out.println("reservation : web ok !!!!!");
        int placeRestant=0,ancienreserve=0,nvx=0;
        String SELECT2 = "SELECT place_restant FROM mysportdb.t_annonce WHERE id_annonce="+update_reservation.getIdAnnonce() ;
        try {
            System.out.println("reservation : 2er !!!!!");
            ps = conn.prepareStatement(SELECT2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                placeRestant = rs.getInt("place_restant");
                System.out.println("rest" + placeRestant);
            }
                String SELECT = "SELECT nombre_reserve FROM mysportdb.t_reserve WHERE id_reserve="+update_reservation.getIdReservation();
                try {
                    System.out.println("reservation : 2er !!!!!");
                    ps = conn.prepareStatement(SELECT);
                    ResultSet rs1 = ps.executeQuery();
                    System.out.println("reservation :QR  2er !!!!!");
                    if (rs1.next()) {
                        ancienreserve = rs1.getInt("nombre_reserve");
                        System.out.println("rest" + placeRestant);
                        nvx = placeRestant + ancienreserve-update_reservation.getNombreDePlace();
                        if(nvx<0)
                            return"Nombre de places insufisant le MAX="+placeRestant;
                        System.out.println("nouveau" + nvx);
                    }
                    String UPDATE = "UPDATE mysportdb.t_reserve SET nombre_reserve='" + update_reservation.getNombreDePlace() + "'  WHERE id_reserve=" + update_reservation.getIdReservation();
                    System.out.println(UPDATE);
                    try {
                        ps = conn.prepareStatement(UPDATE);
                        ps.execute();
                        String Update = "UPDATE  mysportdb.t_annonce SET place_restant=" + nvx + " WHERE id_annonce=" + update_reservation.getIdAnnonce();
                        try {
                            ps = conn.prepareStatement(Update);
                            ps.execute();
                            System.out.println("reservation : mise a jour de place rest!!!!!");
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



@GET
@Path("/reserve/deleteReservation/{deleteReservation}")
public String deleteReservation( @PathParam("deleteReservation") String reservation) {
    Reservation received_reservation1 = gson.fromJson(reservation, Reservation.class);
    System.out.println("reservation : web ok !!!!!");
   // Reservation received_reservation1 = new Reservation("12-12-2022", "12:00", "14:00",3,1,3,15);
    System.out.println(received_reservation1.getIdAnnonce()+"\n"+ received_reservation1.getIdReservation()+"\n"+received_reservation1.getNombreDePlace());

    int idannonce =0;
    int placeRestant =0;
    int nvx=0;
    String SELECT = "SELECT id_annonce FROM mysportdb.t_reserve WHERE id_reserve="+ received_reservation1.getIdReservation();
    try {
        System.out.println("reservation : 1er !!!!!");
        ps = conn.prepareStatement(SELECT);
        ResultSet rs_id = ps.executeQuery();
        System.out.println("reservation :RQT ok 1er !!!!!");
        if (rs_id.next()) {
            idannonce = rs_id.getInt("id_annonce");
            System.out.println(idannonce);
        }
        String SELECT2 = "SELECT place_restant FROM mysportdb.t_annonce WHERE id_annonce=" + idannonce ;
        try {
            System.out.println("reservation : 2er !!!!!");
            ps = conn.prepareStatement(SELECT2);
            ResultSet rs = ps.executeQuery();
            System.out.println("reservation :QR  2er !!!!!");
            if (rs.next()) {
                placeRestant = rs.getInt("place_restant");
                System.out.println("rest"+placeRestant);

                nvx = placeRestant + received_reservation1.getNombreDePlace();
                System.out.println("nouveau"+nvx);

            }
            System.out.println("reservation : 1er try ok !!!!!");
            String Update = "UPDATE  mysportdb.t_annonce SET place_restant=" + nvx + " WHERE id_annonce="+ idannonce;
            try {
                ps = conn.prepareStatement(Update);
                ps.execute();
                System.out.println("reservation : mise a jour de place rest!!!!!");
                String DELETE = "DELETE FROM mysportdb.t_reserve WHERE id_reserve=" + received_reservation1.getIdReservation();
                try {
                    ps = conn.prepareStatement(DELETE);
                    ps.execute();
                    System.out.println("reservation : supression ok !!!!!");
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

    @GET
    @Path("/annonce/addAnnonce/{addAnnonce}")
    public String addAnnonce(@PathParam("addAnnonce")String annonce) {

        Gson gsb = new GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserialize()).create();

        Annonce received_annonce = gsb.fromJson(annonce,Annonce.class);
        System.out.println("Annonce : web ok !!!!!");
        int idterrain =0;
        //FactoryItem factoryItem = new FactoryItem();
        //Item terrain = FactoryItem.getInstanceItem("Terrain");
       /*
        Terrain terrain = new Terrain();
        terrain.setNom("FIVE");
        terrain.setAdresse("5 place jussieu");
        terrain.setCapacity(10);
        terrain.setCodePostal("75005");
        terrain.setTypeSport("Football");
        terrain.setVille("Paris");

        Annonce received_annonce = new Annonce();
        received_annonce.setDateDisponible("2020-01-10");
        received_annonce.setHeureDebut("10:00:00");
        received_annonce.setHeureFin("12:00:00");
        received_annonce.setId_userPublier(3);
        received_annonce.setNombreDePlaceRestant(terrain.getCapacity());
        received_annonce.setTerrain(terrain);
System.out.println(terrain.getNom()+"\n"+terrain.getAdresse()+"\n"+terrain.getCapacity()+"\n"+terrain.getCodePostal()+"\n"+terrain.getTypeSport()+"\n"+terrain.getVille());
System.out.println(received_annonce.getDateDisponible()+"\n"+received_annonce.getHeureDebut()+"\n"+received_annonce.getHeureFin()+"\n"+received_annonce.getId_userPublier()+"\n"+received_annonce.getNombreDePlaceRestant());
*/
        String INSERT = "INSERT INTO mysportdb.t_terrain(id_annonce," +
                "adresse," +
                "ville," +
                "code_postal," +
                "capacite," +
                "type_sport)" +
                "VALUES" +
                "('"+received_annonce.getTerrain().getAdresse() + "',"+
                "'"+received_annonce.getTerrain().getVille()+ "',"+
                "'"+received_annonce.getTerrain().getCodePostal()+"'," +
                "'"+received_annonce.getTerrain().getCapacity()+"'," +
                "'"+received_annonce.getTerrain().getTypeSport()+"') ";

        try {
            System.out.println("ajout terrain  !!!!!");
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            System.out.println("Ajout terrain!!!!!");
            String SELECT = "SELECT id_terrain FROM mysportdb.t_terrain WHERE adresse = '" +(received_annonce.getTerrain().getAdresse()) + "'AND code_postal='"+(received_annonce.getTerrain().getCodePostal())+"'AND type_sport='"+(received_annonce.getTerrain().getTypeSport())+"'";
            try {
                System.out.println("id terrain !!!!!");
                ps = conn.prepareStatement(SELECT);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    idterrain=rs.getInt("id_terrain");
                    System.out.println("id_terrain ="+idterrain);
                }
                System.out.println("Ajout terrain ok !!!!!");
                String INSERT2 = "INSERT INTO mysportdb.t_annonce(id_user,jour_debut,heure_debut,heure_fin,place_restant,id_terrain) VALUES(" +
                        "'"+received_annonce.getId_userPublier()+"'," +
                        "'"+received_annonce.getDateDisponible()+"'," +
                        "'"+received_annonce.getHeureDebut()+"'," +
                        "'"+received_annonce.getHeureFin()+"'," +
                        "'"+received_annonce.getNombreDePlaceRestant()+"'," +
                        "'"+idterrain+"')";

                System.out.println("add annonce ??? !!!!!");

                try {
                    System.out.println("add annonce ?11111 !!!!!");

                    ps = conn.prepareStatement(INSERT2);
                    ps.execute();
                    System.out.println("add annonce ooook !!!!!");

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
    
    @GET
    @Path("/annonce/mesAnnonces/{id_user}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getMesAnnonces(@PathParam("id_user") String id){
        Integer id_user = gson.fromJson(id, Integer.class);
        String SELECT = "SELECT * FROM mysportdb.t_annonce INNER JOIN mysportdb.t_terrain WHERE mysportdb.t_terrain.id_terrain = mysportdb.t_annonce.id_terrain " +
                "and mysportdb.t_annonce.id_user ="+ id_user;
        System.out.println(SELECT);
        ArrayList<Annonce> mesAnnonces = new ArrayList<Annonce>();
        ResultSet rs =null;
        String rsaa = null;

        try {
            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            String typeItem = "Terrain";
            while (rs.next()){
                System.out.println(rs.toString());
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


    @GET
    @Path("/annonce/toutesAnnonces/")
    @Produces(MediaType.APPLICATION_JSON)
    public String getALLAnnonces(){
        String SELECT = "SELECT * FROM mysportdb.t_annonce INNER JOIN mysportdb.t_terrain WHERE mysportdb.t_terrain.id_terrain = mysportdb.t_annonce.id_terrain ";
        System.out.println(SELECT);
        ArrayList<Annonce> mesAnnonces = new ArrayList<Annonce>();
        ResultSet rs =null;
        String rsaa = null;

        try {
            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            String typeItem = "Terrain";
            while (rs.next()){
                System.out.println(rs.toString());
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

    //##########################################################################################################################//
                                                // Partie Recherche//
    //##########################################################################################################################//


    @GET
    @Path("/annonce/recherche/{recherche}")
    @Produces(MediaType.APPLICATION_JSON)
    public String recherche(@PathParam("recherche") String recherchejson){
        Recherche recherche = gson.fromJson(recherchejson, Recherche.class);
        ResultSet rs =null;
        ArrayList<Annonce> resultatAnnonces = new ArrayList<Annonce>();
       String SELECT = "SELECT * FROM mysportdb.t_annonce INNER JOIN mysportdb.t_terrain WHERE " +
               "jour_debut='"+recherche.getDate()+" 'AND" +
               " heure_debut='"+recherche.getHeureDebut()+" 'AND" +
               " ville ='"+recherche.getVille()+" 'AND" +
               " type_sport='" +recherche.getTypeSport()+"'";
        System.out.println(SELECT);


        try {
            System.out.println("ajout rech  !!!!!");

            ps = conn.prepareStatement(SELECT);
            rs = ps.executeQuery();
            System.out.println("ajout rech ok  !!!!!");

            String typeItem = "Terrain";
            while (rs.next()){
                System.out.println("ajout while  !!!!!");

                System.out.println(rs.toString());
                Annonce a= new Annonce();
                FactoryItem f = new FactoryItem();
                Item terr = f.getInstanceItem(typeItem);

                terr.setAdresse(rs.getString("adresse"));
                terr.setCapacity(rs.getInt("capacite"));
                terr.setCodePostal(rs.getString("code_postal"));
                terr.setTypeSport(rs.getString("type_sport"));
                terr.setId(rs.getInt("id_terrain"));
                terr.setVille(rs.getString("ville"));
                System.out.println("ajout terrain  ok!!!!!");

                a.setIdAnnonce(rs.getInt("id_annonce"));
                a.setId_userPublier(rs.getInt("id_user"));
                a.setHeureFin(rs.getString("heure_debut"));
                a.setHeureFin(rs.getString("heure_fin"));
                a.setNombreDePlaceRestant(rs.getInt("place_restant"));
                a.setTerrain(terr);
                System.out.println("ajout annonce ok  !!!!!");

                resultatAnnonces.add(a);

            }
            String json= gson.toJson(resultatAnnonces);

            return (json );


        } catch (Exception e){
            e.printStackTrace();
            return gson.toJson(new String("Failure"+e.getMessage()));
        }

    }

    @GET
    @Path("/annonce/deleteAnnonce/{id}")
    public int deleteUser(@PathParam("id") String id) {

        int id_annonce = gson.fromJson(id, int.class);
        if (id_annonce<0) return -1;
        String  DELETE = "Delete FROM mysportdb.t_annonce WHERE mysportdb.t_annonce.id_annonce= " + id_annonce;
        System.out.println(DELETE);
        try {
            ps = conn.prepareStatement(DELETE);
            ps.execute();
            System.out.println("Delete success");
            return 1;
        } catch (Exception e){
            return -1;
        }

    }

}