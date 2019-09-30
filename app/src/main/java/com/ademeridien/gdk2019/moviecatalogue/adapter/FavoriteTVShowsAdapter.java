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
import com.ademeridien.gdk2019.moviecatalogue.ViewDetailTVActivity;
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteTVShow;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.ademeridien.gdk2019.moviecatalogue.ViewDetailTVActivity.EXTRA_FAVORITE_TVSHOW;

public class FavoriteTVShowsAdapter extends RecyclerView.Adapter<FavoriteTVShowsAdapter.TVShowViewHolder> {
    private ArrayList<FavoriteTVShow> favoriteTVShowsList = new ArrayList<>();
    private Context context;

    public FavoriteTVShowsAdapter(Context context) {
        this.context = context;
    }

    public void setListTVShows(ArrayList<FavoriteTVShow> tvShows) {
        favoriteTVShowsList.clear();
        favoriteTVShowsList.addAll(tvShows);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new TVShowViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int i) {
        final FavoriteTVShow tvShow = favoriteTVShowsList.get(i);
        holder.title.setText(tvShow.getTitle());
        holder.overview.setText(tvShow.getOverview());
        Glide.with(context)
                .load(tvShow.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(holder.poster);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewDetailTVActivity.class);
            intent.putExtra(EXTRA_FAVORITE_TVSHOW, tvShow);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return favoriteTVShowsList.size();
    }

    class TVShowViewHolder extends RecyclerView.ViewHolder {
        private TextView title, overview;
        private ImageView poster;

        TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_film);
            overview = itemView.findViewById(R.id.desc_film);
            poster = itemView.findViewById(R.id.img_movie);
        }
    }
}
