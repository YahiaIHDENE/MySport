package com.example.mysport;

import android.content.Context;
import android.widget.Toast;

public class AddReservationJob implements IAddReservationJob {
    @Override
    public void onSuccess(String retour, Context context) {
        //Toast.makeText(context, "Votre reservation a ete prise en compte", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onFailure(Throwable throwable) {

    }
}
