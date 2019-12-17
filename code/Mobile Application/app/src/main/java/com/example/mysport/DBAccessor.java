package com.example.mysport;


import android.content.Context;
import android.widget.ListView;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class DBAccessor {
    private IDBAccessor dbAccessor;
    private Gson gson;

    public DBAccessor () {
        dbAccessor = new Retrofit.Builder()
                .baseUrl(IDBAccessor.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IDBAccessor.class);
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
        dbAccessor.addUser(user_json).enqueue(cb);
    }

    public void fetchUser(String email, String pw, final IFetchUserJob fetchUserJob){
        String user_email = gson.toJson(email);
        String user_pw = gson.toJson(pw);

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
        dbAccessor.fetchUser(user_email, user_pw).enqueue(cb);
    }

    public void updateUser(User user, final IUpdateUserJob job){
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
        dbAccessor.updateUser(user_json).enqueue(cb);
    }

    public void recupererAnnonces(final ListView listView, final Context context, final List<Annonce> annonceList, final IRecupererAnnonceJob job){

        Callback<ResponseBody> cb = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    job.onSuccess(new String(response.body().bytes()), context, listView, annonceList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.recupererAnnonces().enqueue(cb);
    }

    public void addReservation(Reservation reservation, final Context context, final IAddReservationJob job){
        String reservation_json = gson.toJson(reservation);
        Callback<String> cb = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                job.onSuccess(response.body(), context);
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.addReservation(reservation_json).enqueue(cb);
    }

    public void deleteAnnonce(int id, final IDeleteAnnonceJob job){
        String id_json = gson.toJson(id);
        Callback<String> cb = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                job.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.deleteAnnonce(id_json).enqueue(cb);
    }

    public void getMesAnnonces(int id, final ListView listViewMesAnnonce, final IGetMesAnnoncesJob job){
        String id_json = gson.toJson(id);
        Callback<ResponseBody> cb = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    job.onSuccess(new String(response.body().bytes()), listViewMesAnnonce);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.getMesAnnonces(id_json).enqueue(cb);
    }

    public void deleteReservation(Reservation reservation, final IDeleteReservation job){
        String reservation_json = gson.toJson(reservation);
        Callback<String> cb = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                job.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.deleteReservation(reservation_json).enqueue(cb);
    }

    public void EditReservation(Reservation reservation, final IEditReservationJob job){
        String reservation_json = gson.toJson(reservation);
        Callback<String> cb = new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                job.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.editReservation(reservation_json).enqueue(cb);
    }

    public void getMesReservations(int id, final ListView listViewMesReservation, final IGetMesReservationJob job){
        String id_json = gson.toJson(id);
        Callback<ResponseBody> cb = new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    job.onSuccess(new String(response.body().bytes()), listViewMesReservation);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {
                job.onFailure(throwable);
            }
        };
        dbAccessor.getMesReservations(id_json).enqueue(cb);
    }
}
