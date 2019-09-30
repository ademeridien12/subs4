package com.ademeridien.gdk2019.moviecatalogue.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ademeridien.gdk2019.moviecatalogue.R;
import com.ademeridien.gdk2019.moviecatalogue.ViewDetailMovieActivity;
import com.ademeridien.gdk2019.moviecatalogue.model.Movie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.ademeridien.gdk2019.moviecatalogue.ViewDetailMovieActivity.EXTRA_MOVIE;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private Context context;
    private ArrayList<Movie> movieList;

    public MoviesAdapter(Context context, ArrayList<Movie> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        final Movie movie = movieList.get(i);
        holder.title.setText(movie.getTitle());
        holder.description.setText(movie.getOverview());
        Glide.with(context)
                .load(movie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(holder.imgMovie);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewDetailMovieActivity.class);
            intent.putExtra(EXTRA_MOVIE, movie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imgMovie;

        MovieViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title_film);
            description = view.findViewById(R.id.desc_film);
            imgMovie = view.findViewById(R.id.img_movie);
        }
    }
}
