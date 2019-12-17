package com.example.mysport;

import android.content.Context;
import android.widget.ListView;

import java.util.List;

public interface IRecupererAnnonceJob {
    public void onSuccess(String body, Context context, ListView listView, List<Annonce> annonceList);
    public void onFailure(Throwable throwable);
}