package edu.uci.swe264p.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 *  Interface to communicate with MovieDB web service
 */
public interface MovieApiService {

    // Gets movie details
    @GET("movie/{id}")
    Call<Movie> getMovie(@Path("id") int id, @Query("api_key") String apiKey);

    // Gets top rated movies
    @GET("movie/top_rated")
    Call<TopRatedResponse> getTopRated(@Query("api_key") String apiKey);

}
