package com.carlolj.flixter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.carlolj.flixter.databinding.ActivityMovieDetailsBinding;
import com.carlolj.flixter.databinding.ActivityMovieTrailerBinding;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MovieTrailerActivity extends YouTubeBaseActivity {

    String videoId = "tKodtNFpzBA";
    YouTubePlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieTrailerBinding binding = ActivityMovieTrailerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent intent = new Intent();
        //Pass the data results
        Intent iin = getIntent();
        Bundle b = iin.getExtras();
        if(b!=null)
        {
            String j =(String) b.get("item_key");
            videoId=j;
        }
        // temporary test video id -- TODO replace with movie trailer video id
        // resolve the player view from the layout
        playerView = binding.player;

        // initialize with API key stored in secrets.xml
        playerView.initialize(getString(R.string.API_KEY), new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                YouTubePlayer youTubePlayer, boolean b) {
                // do any work here to cue video, play video, etc.
                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                YouTubeInitializationResult youTubeInitializationResult) {
                // log the error
                Toast toast = Toast.makeText(playerView.getContext(), "This movie doesn't have a preview", Toast.LENGTH_SHORT);
                toast.show();
                Log.e("MovieTrailerActivity", "Error initializing YouTube player");
            }
        });
    }
}
