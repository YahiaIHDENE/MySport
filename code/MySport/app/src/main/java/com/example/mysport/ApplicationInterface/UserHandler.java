package com.example.mysport.ApplicationInterface;
import com.example.mysport.POJO.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import com.google.gson.Gson;

interface IUserHandler {
    String ENDPOINT = "http://397a9660.ngrok.io";

    @GET("/mysport/adduser/{message}")
    Call<Integer> addUser(@Path("message") String message);

    @GET("/mysport/fetchuser/{id}")
    Call<User> fetchUser(@Path("id") String  id);
}

public class UserHandler {
    private IUserHandler userHandler;
    private Gson gson;

    public UserHandler () {
        userHandler = new Retrofit.Builder()
                .baseUrl(IUserHandler.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IUserHandler.class);
        gson = new Gson();

    }

    public void addUser(User user, final IAddUserJob job){
        String user_json =  gson.toJson(user);
        Callback<Integer> cb = new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                job.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        userHandler.addUser(user_json).enqueue(cb);
    }

    public void fetchUser(int id, final IFetchUserJob fetchUserJob){
        String id_user = gson.toJson(id);
        Callback<User> cb = new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                fetchUserJob.onSuccess(response.body());
            }
            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                fetchUserJob.onFailure(throwable);
            }
        };
        userHandler.fetchUser(id_user).enqueue(cb);
    }


}
