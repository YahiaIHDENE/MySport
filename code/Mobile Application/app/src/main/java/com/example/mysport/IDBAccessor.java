package com.example.mysport;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IDBAccessor {
    String ENDPOINT = "http://32778cf1.ngrok.io/";

    @GET("/mysport/adduser/{user}")
    Call<Integer> addUser(@Path("user") String user);

    @GET("/mysport/fetchuser/{email}/{pw}")
    Call<User> fetchUser(@Path("email") String  email, @Path("pw") String pw);

    @GET("/mysport/updateuser/{user}")
    Call<Integer> updateUser(@Path("user") String user);

    @GET("/mysport/annonce/toutesAnnonces/")
    Call<ResponseBody> recupererAnnonces();

    @GET("/mysport/annonce/deleteAnnonce/{id}")
    Call<String> deleteAnnonce(@Path("id") String id_Annonce);

    @GET("/mysport/annonce/mesAnnonces/{message}")
    Call<ResponseBody> getMesAnnonces(@Path("message") String message);

    @GET("/mysport/reserve/addReservation/{addReservation}")
    Call<String> addReservation(@Path("addReservation") String addReservation);

    @GET("/mysport/reserve/deleteReservation/{deleteReservation}")
    Call<String> deleteReservation(@Path("deleteReservation") String deleteReservation);

    @GET("/mysport/reserve/editReservation/{editReservation}")
    Call<String> editReservation(@Path("editReservation") String editReservation);

    @GET("/mysport/reserve/mesReservation/{message}")
    Call<ResponseBody> getMesReservations(@Path("message") String message);

    @GET("/mysport/annonce/addAnnonce/{addAnnonce}")
    Call<String> addAnnonce(@Path("addAnnonce") String addAnnonce);

}