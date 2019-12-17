package com.example.mysport;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FetchUserJob implements IFetchUserJob {

    private Class<? extends Activity> next_activity;
    private Context current_context;
    private Gson gson;

    public FetchUserJob(Context current_context, Class<? extends Activity> next_activity){
        this.current_context = current_context;
        this.next_activity = next_activity;
        this.gson = new Gson();
    }

    @Override
    public void onSuccess(User user) {
        if(user != null){

            try {
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(current_context.openFileOutput("user.json", Context.MODE_PRIVATE));
                outputStreamWriter.write(gson.toJson(user));
                outputStreamWriter.close();
            }
            catch (IOException e) {
                Toast.makeText(current_context, "Error saving the user", Toast.LENGTH_LONG).show();
            }

            Intent change = new Intent(current_context, next_activity);
            current_context.startActivity(change);
        }
        else {
            Toast.makeText(current_context, "incorrect email or password, Please retry", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Throwable throwable) {
        Toast.makeText(current_context, "incorrect email or password", Toast.LENGTH_LONG).show();
    }
}