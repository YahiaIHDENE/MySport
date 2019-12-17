package com.example.mysport;

import android.content.Context;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class GetMesAnnoncesJob implements IGetMesAnnoncesJob {

    private Context context;

    public GetMesAnnoncesJob(Context context){
        this.context = context;
    }

    @Override
    public void onSuccess(String retour, ListView listViewMesAnnonce) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserializer()).create();
        Type listType =new TypeToken<List<Annonce>>(){}.getType();
        List<Annonce> listMesAnnonces = (List<Annonce>) gson.fromJson(retour,listType);
        AnnonceListAdapter annonceListAdapter = new AnnonceListAdapter(context,listMesAnnonces);
        annonceListAdapter.notifyDataSetChanged();
        listViewMesAnnonce.setAdapter(annonceListAdapter);
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
