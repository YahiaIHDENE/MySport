package com.example.mysport;

import android.widget.ListView;

public interface IGetMesAnnoncesJob {
    public void onSuccess(String retour, ListView listViewMesAnnonce);
    public void onFailure(Throwable throwable);
}
