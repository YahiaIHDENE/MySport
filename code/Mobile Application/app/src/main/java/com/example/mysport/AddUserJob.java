package com.example.mysport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class AddUserJob implements IAddUserJob{

    private Class<? extends Activity> next_activity;
    private Context current_context;

    public AddUserJob(Context current_context, Class<? extends Activity> next_activity){
        this.current_context = current_context;
        this.next_activity = next_activity;
    }

    @Override
    public void onSuccess(int i){
        if(i == 0){
            Intent intent = new Intent(current_context, next_activity);
            current_context.startActivity(intent);
        }
        else {
            Toast.makeText(current_context, "Please check that all the required fields are correctly set", Toast.LENGTH_LONG).show();
        }
    }

    public void onFailure(Throwable throwable){
        Toast.makeText(current_context, "Couldn't register the user, please retry later", Toast.LENGTH_LONG).show();
    }
}
