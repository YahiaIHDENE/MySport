package fr.redfabriq.increment;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.Retrofit;

interface IncrementRestService {

    String ENDPOINT = "http://04dadd2b.ngrok.io/";

    @GET("/incdecservice_war/increment")
    Call<Integer> increment();

    @GET("/incdecservice_war/decrement")
    Call<Integer> decrement();
}

public class MainActivity extends AppCompatActivity {
    Button increment = null;
    Button decrement = null;
    TextView text = null;
    Integer counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final IncrementRestService incrementRestService = new Retrofit.Builder()
                .baseUrl(IncrementRestService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IncrementRestService.class);

        text = findViewById(R.id.textView);
        increment = findViewById(R.id.increment);
        decrement = findViewById(R.id.decrement);

        text.setText(counter.toString());

        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementRestService.increment().enqueue(new Callback<Integer>() {
                @Override public void onResponse(Call<Integer> call, Response<Integer> response) {
                counter = response.body();
                text.setText(counter.toString());
                }

                @Override public void onFailure(Call<Integer> call, Throwable t) {
                text.setText("Failure");
                }
                });
            }
        });

        decrement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementRestService.decrement().enqueue(new Callback<Integer>() {
                @Override public void onResponse(Call<Integer> call, Response<Integer> response) {
                counter = response.body();
                text.setText(counter.toString());
                }

                @Override public void onFailure(Call<Integer> call, Throwable t) {
                text.setText("Failure");
                }
                });
            }
        });
    }
}
