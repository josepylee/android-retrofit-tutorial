package edu.uci.swe264p.retrofit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * MovieListAdapter -
 *  Adapater for listing out the popular movie list
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> mData;

    //Implement it as the ProgramListAdapter class
    MovieListAdapter(List<Movie> data){
        this.mData = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvVote;
        TextView tvReleaseDate;
        TextView tvOverview;
        ImageView ivMovie;

        ViewHolder(View itemView){
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvVote = itemView.findViewById(R.id.tvVote);
            tvReleaseDate = itemView.findViewById(R.id.tvReleaseDate);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            ivMovie = itemView.findViewById(R.id.ivMovie);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_row, parent, false);
        return new MovieListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie program = mData.get(position);
        holder.tvTitle.setText(program.getTitle());
        holder.tvOverview.setText(program.getOverview());
        holder.tvReleaseDate.setText(program.getReleaseDate());
        holder.tvVote.setText(program.getVoteAverage().toString());

        // Using Picasso to obtain images from URI
        Picasso.get()
                .load(program.getPosterPath())
                .into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
