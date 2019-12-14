package mysport;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Path("/")
public class AnnonceHandler {

    private static final String url = "jdbc:mysql://localhost:9002";
    private static final String user = "user";
    private static final String password = "test";
    private PreparedStatement ps;
    private Connection conn;
    private Gson gson;

    public AnnonceHandler(){
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
}
