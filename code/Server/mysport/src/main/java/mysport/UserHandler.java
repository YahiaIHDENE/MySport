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
}