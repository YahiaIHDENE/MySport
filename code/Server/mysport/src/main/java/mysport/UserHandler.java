package mysport;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import com.google.gson.Gson;

@Path("/")
public class UserHandler{

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "hakim";
    private static final String password = "12345678";
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
        String INSERT = "INSERT INTO mysportdb.t_user (user_first_name," +
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
    @Path("/fetchuser/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String fetchUser(@PathParam("id") String id){
        Integer id_user = gson.fromJson(id, Integer.class);
        String SELECT = "SELECT * FROM t_user WHERE id_user="+id_user.intValue();
        try {
            ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            //rs.next();
            User user = new User(rs.getString(3), rs.getString(2), rs.getString(4), rs.getString(6), rs.getString(5));
            user.setId(Integer.parseInt(rs.getString(1)));
            return gson.toJson(user);
        } catch (Exception e){
            return gson.toJson(new String("Failure"));
        }
    }
}