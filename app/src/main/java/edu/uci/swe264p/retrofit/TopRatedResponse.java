package edu.uci.swe264p.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * TopRatedResponse -
 *
 * Class containing array of objects from the response.
 * In this case, array of Movies class get stored in the results.
 *
 */
public class TopRatedResponse {

    @SerializedName("results")
    private List<Movie> results;
    @SerializedName("page")
    private int page;
    @SerializedName("total_results")
    private int total_results;
    @SerializedName("total_page")
    private int total_page;

    public List<Movie> getMovies() { return results; }

    public List<Movie> get20Movies() {

        if (results.size() > 20){
            List<Movie> movies20 = new ArrayList<>();
            for (int i = 0 ; i < 20; i++){
                movies20.add(results.get(i));
            }
            return movies20;
        } else {
            System.err.println("Returned response does not have 20 movies");
            return results;
        }
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getPage() { return page; }

    public int getTotalResult() { return total_results; }

    public int getTotalPage() { return total_page; }

}
