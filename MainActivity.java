package com.codepath.rkpandey.flixster;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.codepath.rkpandey.flixster.adapters.MovieAdapter;
import com.codepath.rkpandey.flixster.models.Movie;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {

    public static final String NOW_PLAYING_URL = "https://api.themoviedb.org/3/movie/popular?api_key=26bff066a763b4a0eae213d5224d377c";
   public static final String TAG = "MainActivity";

   List<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        RecyclerView rvMovies = findViewById (R.id.rvMovies);
        movies = new ArrayList<> ();

        MovieAdapter movieAdapter = new MovieAdapter (this,movies);

        rvMovies.setAdapter(movieAdapter);

        rvMovies.setLayoutManager(new LinearLayoutManager (this));

        AsyncHttpClient client = new AsyncHttpClient ();
        client.get (NOW_PLAYING_URL, new JsonHttpResponseHandler () {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d (TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONArray results = jsonObject.getJSONArray ("results");
                    Log.i (TAG, "Results" + results.toString ());
                    movies.addAll(Movie.fromJsonArray (results));
                    movieAdapter.notifyDataSetChanged();
                    Log.i (TAG, "Movies" + movies.size ());
                } catch (JSONException e) {
                    Log.e (TAG, "Hit json exception");
                }
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                   Log.d(TAG, "onFailure");
            }
        });


    }
}
