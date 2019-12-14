package mysport;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import com.google.gson.Gson;
import java.util.Date;
import java.text.SimpleDateFormat;


@Path("/reserve/")
public class ReservationHandler {

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "mysportuser";
    private static final String password = "";
    public PreparedStatement ps;
    public Connection conn;

    private Gson gson;


    public ReservationHandler(){
        gson = new Gson();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
/*
    @GET
    @Path("/reserve/addReservation/{reservation}")
    public int addReservation(@PathParam("reservation") String reservation) {
        Reservation received_reservation = gson.fromJson(reservation, Reservation.class);
        System.out.println("reservation : web ok !!!!!");

        String INSERT= "INSERT INTO mysportdb.t_reserve (id_annonce," +
                " id_user," +
                " nombre_reserve," +
                " jour_reserve," +
                " heure_debut," +
                " heure_fin)" +
                " VALUES ('"+ received_reservation.getId_annonce()+ "', '"+
                received_reservation.getId_user() + "', '"+
                received_reservation.getNombreDePlace() + "', '"+
                received_reservation.getDate() + "', '"+
                received_reservation.getHeure_debut() + "', '"+
                received_reservation.getHeure_fin() + "')";

        try {
             ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 1;
        } catch (Exception e){
            return -1;
        }
    }



 */



    @GET
    @Path("/fetchreserve/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchUser(@PathParam("id") String id){
        Integer id_user = gson.fromJson(id, Integer.class);
       /*
        String SELECT = "SELECT * FROM mysportdb.t_reserve WHERE id_user="+id_user.intValue();
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

        */
       return "toto";
    }

}
