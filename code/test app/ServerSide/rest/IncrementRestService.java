package com.IncrementRestService.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.sql.*;

@Path("/")
public class IncrementRestService{

    private static final String url = "jdbc:mysql://localhost";
    private static final String user = "hakim";
    private static final String password = "12345678";
    private PreparedStatement ps;
    private Connection conn;

    public IncrementRestService(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);

            // the mysql insert statement
            String INSERT = "INSERT INTO increment.incrementTable" + " VALUES ('cpt', 1)" + " ON DUPLICATE KEY UPDATE value=0";

            // create and execute the mysql insert preparedstatement
            ps = conn.prepareStatement(INSERT);
            ps.execute();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    private int fetch(){
        String SELECT = "SELECT value FROM increment.incrementTable WHERE name='cpt'";
        int i = 0;
        try {
            ps = conn.prepareStatement(SELECT);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                i = rs.getInt(1);
            } else {
                i = -1;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    @GET
    @Path("/decrement")
    public int decrement() {
        String UPDATE = "UPDATE increment.incrementTable SET value=LAST_INSERT_ID(value-1) WHERE NAME='cpt'";
        try {
            ps = conn.prepareStatement(UPDATE);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fetch();
    }
    @GET
    @Path("/increment")
    public int increment() {
        String UPDATE = "UPDATE increment.incrementTable SET value=LAST_INSERT_ID(value+1) WHERE NAME='cpt'";
        try {
            ps = conn.prepareStatement(UPDATE);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fetch();
    }
}