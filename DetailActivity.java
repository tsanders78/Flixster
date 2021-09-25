package com.codepath.rkpandey.flixster;

import static org.parceler.Parcels.*;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.RatingBar;
import android.widget.TextView;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.rkpandey.flixster.models.Movie;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import org.json.JSONArray;
import org.json.JSONException;

import okhttp3.Headers;


public class DetailActivity extends YouTubeBaseActivity {

    private static final String YOUTUBE_API_KEY = "AIzaSyDl7szTpoumRWvZAPnLnsECMvWcNCWaHEE";
   // private static final String VIDEOS_URL = "https://api.themoviedb.org/3/movie/popular?api_key=26bff066a763b4a0eae213d5224d377c";

    TextView tvTitle;
    TextView tvOverview;
    RatingBar ratingBar;
    YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail);

        tvTitle = findViewById (R.id.tvTitle);
        tvOverview = findViewById (R.id.tvOverView);
        ratingBar = findViewById (R.id.ratingBar);
        youTubePlayerView = findViewById (R.id.player);

        Movie movie = unwrap (getIntent ().getParcelableExtra ("movie"));
        //tvTitle.setText(movie.getTitle ());
        // tvOverview.setText(movie.getOverview ());
        //  ratingBar.setRating((float) movie.getRating ());


        youTubePlayerView.initialize (YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener () {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d ("DetailActivity","onInitializationSuccess");
                youTubePlayer.cueVideo("MysGjRS9jFU");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        });
    }
}
