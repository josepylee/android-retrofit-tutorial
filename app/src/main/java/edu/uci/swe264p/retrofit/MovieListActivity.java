package edu.uci.swe264p.retrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/***
 *  MovieListActivity-
 *      Android activity for listing out top 20 popular movies
 *      into RecyclerView
 */
public class MovieListActivity extends AppCompatActivity {

    static final String TAG = MovieListActivity.class.getSimpleName();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        connect();

        recyclerView = findViewById(R.id.rvMovieList);
        recyclerView.setHasFixedSize(true); // OR false
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void connect() {

        // Registering MovieApiService class
        MovieApiService movieApiService = MainActivity.retrofit.create(MovieApiService.class);

        // Call class retrofit
        Call<TopRatedResponse> call = movieApiService.getTopRated(MainActivity.API_KEY);

        // setting up asynchronous callback for each 'Call'
        call.enqueue(new Callback<TopRatedResponse>() {

            // Overriding onResponse callback / with get body()
            @Override
            public void onResponse(Call<TopRatedResponse> call, Response<TopRatedResponse> response) {

//                List<Movie> top_rated_movies = response.body().getMovies();
                List<Movie> top_rated_movies = response.body().get20Movies();
                recyclerView.setAdapter(new MovieListAdapter(top_rated_movies));
                recyclerView.smoothScrollToPosition(0);

            }
            @Override
            public void onFailure(Call<TopRatedResponse> call, Throwable throwable) {
                Log.e(TAG + "", throwable.toString());
            }
        });
    }
}
