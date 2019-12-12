
package com.example.mysport.ApplicationInterface;

import android.content.Intent;

public class AddUserJob implements IAddUserJob{
    @Override
    public void onSuccess(int i){
        startActivity(new Intent(Inscription.this, MainActivity.class));

    }

    public void onFailure(Throwable throwable){

    }

}
