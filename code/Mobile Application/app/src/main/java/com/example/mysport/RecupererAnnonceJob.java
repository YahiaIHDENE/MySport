package com.example.mysport;

import android.content.Context;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

public class RecupererAnnonceJob implements IRecupererAnnonceJob {
    @Override
    public void onSuccess(String body, Context context, ListView listView, List<Annonce> annonceList) {
        Gson gson = new GsonBuilder().registerTypeAdapter(Annonce.class,new AnnonceDeserializer()).create();
        Type listType =new TypeToken<List<Annonce>>(){}.getType();
        annonceList = (List<Annonce>) gson.fromJson(body,listType);
        AnnonceListAdapter annonceListAdapter = new AnnonceListAdapter(context,annonceList);
        annonceListAdapter.notifyDataSetChanged();
        listView.setAdapter(annonceListAdapter);
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
