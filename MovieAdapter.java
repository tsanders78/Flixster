package com.codepath.rkpandey.flixster.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codepath.rkpandey.flixster.DetailActivity;
import com.codepath.rkpandey.flixster.R;
import com.codepath.rkpandey.flixster.models.Movie;


import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    Context context;
    List<Movie> movies;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("MovieAdpater", "OnCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder (movieView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdpater", "OnBindViewHolder" + position);
        
        Movie movie = movies.get(position);
        
        holder.bind(movie);

    }

    @Override
    public int getItemCount() {
        return movies.size ();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        RelativeLayout container;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById (R.id.tvTitle);
            tvOverview = itemView.findViewById (R.id.tvOverView);
            ivPoster = itemView.findViewById (R.id.ivPoster);
            container = itemView.findViewById (R.id.container);



    }

        public void bind(Movie movie) {
            tvTitle.setText(movie.getTitle ());
            tvOverview.setText (movie.getOverview ());
            String imageUrl;

            if(context.getResources ().getConfiguration ().orientation == Configuration.ORIENTATION_LANDSCAPE) {
                imageUrl = movie.getBackdropPath ();
            } else {
                imageUrl = movie.getPosterPath ();
            }
            Glide.with(context).load(imageUrl).into(ivPoster);

            container.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(context, DetailActivity.class);
                    context.startActivity (i);
                }
            });
        }
    }
}
