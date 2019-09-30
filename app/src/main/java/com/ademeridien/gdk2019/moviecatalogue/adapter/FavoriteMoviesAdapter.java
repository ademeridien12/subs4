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
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteMovie;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.ademeridien.gdk2019.moviecatalogue.ViewDetailMovieActivity.EXTRA_FAVORITE_MOVIE;

public class FavoriteMoviesAdapter extends RecyclerView.Adapter<FavoriteMoviesAdapter.MovieViewHolder> {
    private ArrayList<FavoriteMovie> favMovieList = new ArrayList<>();
    private Context context;

    public FavoriteMoviesAdapter(Context context) {
        this.context = context;
    }

    public void setListMovie(ArrayList<FavoriteMovie> movies) {
        favMovieList.clear();
        favMovieList.addAll(movies);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int i) {
        final FavoriteMovie movie = favMovieList.get(i);
        holder.title.setText(movie.getTitle());
        holder.overview.setText(movie.getOverview());
        Glide.with(context)
                .load(movie.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(holder.poster);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewDetailMovieActivity.class);
            intent.putExtra(EXTRA_FAVORITE_MOVIE, movie);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favMovieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview;
        private ImageView poster;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_film);
            overview = itemView.findViewById(R.id.desc_film);
            poster = itemView.findViewById(R.id.img_movie);
        }
    }
}
