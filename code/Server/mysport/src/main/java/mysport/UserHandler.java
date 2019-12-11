package mysport;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;

@Path("/")
public class UserHandler{

    private static final String url = "jdbc:mysql://localhost";
    private static final String user_db = "root";
    private static final String password = "01072018";
    private PreparedStatement ps;
    private Connection conn;
    User user;
    public UserHandler(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user_db, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    @GET
    @Path("/adduser/{message}")
   // @Produces(MediaType.APPLICATION_JSON)
    public String addUser(@PathParam("message") String json) {
        Gson gson = new Gson();

        System.out.println("j'arrive a rentrer");
        /*String INSERT = "INSERT INTO mysportdb.t_user (user_first_name," +
                                                     " user_last_name," +
                                                     " user_mail," +
                                                     " user_password," +
                                                     " user_tel)" +
                                         " VALUES ('"+ user.getFirstname() + "', '"+
                                                      user.getLastname() + "', '"+
                                                      user.getEmail() + "', '"+
                                                      user.getPW() + "', '"+
                                                      user.getNumber() + "')";
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        } catch (Exception e){
            return -1;
        }*/

       user = gson.fromJson(json, User.class);
        System.out.println("==============");
        System.out.println(user.getEmail());
        System.out.println("==============");
       // 'test','test','test','test','00000'
        String INSERT = "INSERT INTO mysportdb.t_user (user_first_name," +
                " user_last_name," +
                " user_mail," +
                " user_password," +
                " user_tel)" +
                " VALUES ('"+ user.getFirstname() + "', '"+
                user.getLastname() + "', '"+
                user.getEmail() + "', '"+
                user.getPW() + "', '"+
                user.getNumber() + "')" ;
        System.out.println("\nuser_first_name = " + user.getFirstname()+" \nuser_last_name = " +  user.getLastname() +"\nuser_mail =" +user.getEmail() + "\nuser_password," + user.getPW() + "\nuser_tel)" +user.getNumber());

;
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
           // return 0;
        } catch (Exception e){
           // return -1;
        }
        System.out.println("ookkk");
        return "toto";
    }
}
