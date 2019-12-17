package com.example.mysport;

import android.widget.ListView;

public interface IGetMesReservationJob {
    public void onSuccess(String retour, ListView listViewMesAnnonce);
    public void onFailure(Throwable throwable);
}
