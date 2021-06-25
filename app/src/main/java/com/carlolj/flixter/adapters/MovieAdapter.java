package com.carlolj.flixter.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.carlolj.flixter.MovieDetailsActivity;
import com.carlolj.flixter.R;
import com.carlolj.flixter.models.Movie;

import org.jetbrains.annotations.NotNull;
import org.parceler.Parcels;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

//We define the class MovieAdapter which extends from the abstract class RecyclerView.adapter
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    Context context;
    List<Movie> movies;

    //Constructor of the Adapter that recieves the context and a list of object movie
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @NotNull

    //When we're creating the viewHolder (which will have the items), and inflating it in the context received with item_movie xml. THIS WILL RETURN THE HOLDER.
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        Log.d("MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent,false);
        return new ViewHolder(movieView);
    }

    //Involves populating data into the item through the holder
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "onBindViewHolder " + position);
        //Get the movie at  the passed position
        Movie movie = movies.get(position);
        //Bind the movie dat into the VH
        holder.bind(movie);
    }

    //Return the total count of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    //Each of the elements inside the recycler view
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RatingBar rbVoteAverage;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);

            rbVoteAverage = itemView.findViewById(R.id.rbVoteAverage);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);

            ivPoster = itemView.findViewById(R.id.ivPoster);
            //Add this as the itemView OnClickListener
            itemView.setOnClickListener(this);
        }

        public void bind(Movie movie) {

            float voteAverage = movie.getVoteAverage().floatValue();
            rbVoteAverage.setRating(voteAverage/2.0f);

            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;
            //If phone is in landscape
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                //Then imageUrl = BACKDROP IMAGE
                imageUrl = movie.getBackdropPath();
            }else {
                //else imageUrl = poster IMAGE
                imageUrl = movie.getPosterPath();
            }

            int radius = 30;
            int margin = 10;
            Glide.with(context)
                    .load(imageUrl)
                    .transform(new RoundedCornersTransformation(radius,margin))
                    .placeholder(R.drawable.bananaloading)
                    .into(ivPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            //Make sure the position is valid, actually exists in the view
            if(position != RecyclerView.NO_POSITION){
                //Create a new movie with the movie of the selected position, this wont work if the class is static
                Movie movie = movies.get(position);
                //Create intent for the new activity
                Intent intent = new Intent(context, MovieDetailsActivity.class);
                //Serialize the movie using parceler, use its short name as a key
                intent.putExtra(Movie.class.getSimpleName(), Parcels.wrap(movie));
                //Show the activity
                context.startActivity(intent);
            }
        }
    }
}
