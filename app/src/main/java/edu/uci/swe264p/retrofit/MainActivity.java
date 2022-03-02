package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * MainActivity -
 *
 * Includes two buttons to access ProgramListActivity and MovieListActivity
 * Make sure to replace the "API_KEY" with the key obtained from moviedb.
 * Please read the README.txt file for more information.
 *
 */
public class MainActivity extends AppCompatActivity {
    static final String TAG = MainActivity.class.getSimpleName();
    static final String BASE_URL = "https://api.themoviedb.org/3/";
    static Retrofit retrofit = null;
    final static String API_KEY = "e9a0361f8644da1cb403bc291818e688";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //First connect to the web service
        connect();

        // Button btnProgramList
        final Button btnProgramList = findViewById(R.id.btnProgramList);
        // registering click to the listener
        btnProgramList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                // Creating an intent when a button is clicked
                Intent intent = new Intent(getApplicationContext(), ProgramListActivity.class);

                // Start program list activitiy
                startActivity(intent);
            }
        });

        // Button btnMovieList
        final Button btnMovieList = findViewById(R.id.btnMovieList);
        btnMovieList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovieListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void connect() {
        // Retrofit builder that generate an object
        // Connects to moviedb
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        // Registering MovieApiService class
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);

        // Call class retrofit
        Call<Movie> call = movieApiService.getMovie(603, API_KEY);

        // setting up asynchronous callback for each 'Call'
        call.enqueue(new Callback<Movie>() {

            // Overriding onResponse callback / with get body()
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {


                int[] ids = {R.id.txtTitle, R.id.txtReleaseDate, R.id.txtPoster,
                        R.id.txtVote, R.id.txtOverview};

                // extracting values from the received body
                String[] values = {
                        response.body().getTitle(),
                        response.body().getReleaseDate(),
                        response.body().getPosterPath(),
                        response.body().getVoteAverage().toString(),
                        response.body().getOverview()
                };
                TextView tv;
                for (int i=0; i < ids.length; i++) {
                    tv = findViewById(ids[i]);
                    tv.setText(values[i]);
                }
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }
}
