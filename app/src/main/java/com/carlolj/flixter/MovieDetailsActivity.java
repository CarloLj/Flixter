package com.carlolj.flixter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.carlolj.flixter.databinding.ActivityMovieDetailsBinding;
import com.carlolj.flixter.models.Movie;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import okhttp3.Headers;

public class MovieDetailsActivity extends AppCompatActivity {

    //The movie to display
    Movie movie;

    //The view Objects
    TextView tvMovieTitle;
    TextView tvMovieOverview;
    ImageView ivBackdropImage;
    RatingBar rbMovieVoteAverage;
    ConstraintLayout constraintLayout;
    String key = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailsBinding binding = ActivityMovieDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // past R searching uncomment to go back again
        //setContentView(R.layout.activity_movie_details);
        //tvMovieTitle = (TextView) findViewById(R.id.tvMovieTitle);
        //tvMovieOverview = (TextView) findViewById(R.id.tvMovieOverview);
        //rbMovieVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);

        tvMovieTitle = binding.tvMovieTitle;
        tvMovieOverview = binding.tvMovieOverview;
        rbMovieVoteAverage = binding.rbVoteAverage;
        ivBackdropImage = binding.ivBackdropVideo;

        constraintLayout = binding.content;
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

        //Unwrap the movie passed via intent, using its simple name as a key
        movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getSimpleName()));
        Log.d("MovieDetailsActivity", String.format("Showing details for '%s'", movie.getTitle()));

        tvMovieTitle.setText(movie.getTitle());
        tvMovieOverview.setText(movie.getOverview());
        tvMovieOverview.setMovementMethod(new ScrollingMovementMethod());

        int radius = 30;
        int margin = 10;
        Glide.with(this)
                .load(movie.getBackdropPath())
                .placeholder(R.drawable.bananaloading)
                .into(ivBackdropImage);

        //Vote average is 0.10 convert to 0.5 dividing by 2
        float voteAverage = movie.getVoteAverage().floatValue();
        rbMovieVoteAverage.setRating(voteAverage/2.0f);
    }

    public void loadMovie(View view) {
        String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/"+movie.getId()+"/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed&language=en-US";
        Log.d("MOVIE: ", movie.getId()+ "");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(NOW_PLAYING_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Headers headers, JSON json) {
                Log.d("MovieDetailsActivity", "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try{
                    JSONArray results = jsonObject.getJSONArray("results");
                    Log.d("MovieDetailsActivity", results.getJSONObject(1).getString("key")+"");
                    key = results.getJSONObject(1).getString("key");
                    Intent ii = new Intent(MovieDetailsActivity.this, MovieTrailerActivity.class);
                    ii.putExtra("item_key", key);
                    startActivity(ii);
                    Log.i("MovieDetailsActivity", "Results: " + results.toString());
                }catch(JSONException e){
                    Log.e("MovieDetailsActivity", "Hit json exception", e);
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int i, Headers headers, String s, Throwable throwable) {
                Log.d("MovieDetailsActivity", "onSuccess");
            }
        });
    }
}