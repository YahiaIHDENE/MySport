package com.example.mysport;

import android.content.Context;

public interface IAddReservationJob {
    public void onSuccess(String retour, Context context);
    public void onFailure(Throwable throwable);
}
