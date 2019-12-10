package mysport;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.*;

@Path("/")
public class UserHandler{

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "hakim";
    private static final String password = "12345678";
    private PreparedStatement ps;
    private Connection conn;

    public UserHandler(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GET
    @Path("/adduser")

    public void addUser() {
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

        /*String INSERT = "INSERT INTO mysportdb.t_user (user_first_name, user_last_name, user_mail, user_password, user_tel) VALUES ('A', 'A', 'A', 'A', 'A')";
        try {
            ps = conn.prepareStatement(INSERT);
            ps.execute();
            return 0;
        } catch (Exception e){
            return -1;
        }*/
    }
}
