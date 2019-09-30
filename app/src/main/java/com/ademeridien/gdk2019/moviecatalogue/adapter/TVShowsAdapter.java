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
import com.ademeridien.gdk2019.moviecatalogue.model.TVShow;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.ademeridien.gdk2019.moviecatalogue.ViewDetailTVActivity.EXTRA_TVSHOW;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TvViewHolder> {
    private Context context;
    private ArrayList<TVShow> tvShowList;

    public TVShowsAdapter(Context context, ArrayList<TVShow> tvShowList) {
        this.context = context;
        this.tvShowList = tvShowList;
    }

    @NonNull
    @Override
    public TvViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_movie, parent, false);
        return new TvViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TvViewHolder holder, int i) {
        final TVShow tvShow = tvShowList.get(i);
        holder.title.setText(tvShow.getName());
        holder.description.setText(tvShow.getOverview());

        Glide.with(context)
                .load(tvShow.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(holder.imgTv);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewDetailTVActivity.class);
            intent.putExtra(EXTRA_TVSHOW, tvShow);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tvShowList.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder {
        TextView title, description;
        ImageView imgTv;

        TvViewHolder(@NonNull View view) {
            super(view);
            title = view.findViewById(R.id.title_film);
            description = view.findViewById(R.id.desc_film);
            imgTv = view.findViewById(R.id.img_movie);
        }
    }
}
