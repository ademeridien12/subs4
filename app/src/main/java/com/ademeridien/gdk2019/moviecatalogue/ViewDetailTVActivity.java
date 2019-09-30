package com.ademeridien.gdk2019.moviecatalogue;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ademeridien.gdk2019.moviecatalogue.db.Helper;
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteTVShow;
import com.ademeridien.gdk2019.moviecatalogue.model.TVShow;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ViewDetailTVActivity extends AppCompatActivity {
    public static final String EXTRA_TVSHOW = "extra_tvshow";
    public static final String EXTRA_FAVORITE_TVSHOW = "extra_favorite_tvshow";
    TextView title, overview, release, language, voteAverage;
    ImageView poster;
    TVShow tvShow;
    FavoriteTVShow favoriteTVShow;
    FloatingActionButton fab;
    Helper helper;
    boolean isFavorite = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail_tv);

        helper = new Helper(this);
        helper.open();

        poster = findViewById(R.id.image_tv);
        title = findViewById(R.id.tv_title);
        overview = findViewById(R.id.tv_overview);
        release = findViewById(R.id.tv_firstRelease);
        language = findViewById(R.id.tv_language);
        voteAverage = findViewById(R.id.tv_userscore);
        fab = findViewById(R.id.fab_favorite);

        if (getIntent().getParcelableExtra(EXTRA_TVSHOW) != null) {
            getTVShowDetail();
            favoriteCheck(tvShow.getId());
        } else {
            getFavoriteTVShowDetail();
            favoriteCheck(favoriteTVShow.getId());
        }
    }

    private void getTVShowDetail() {
        tvShow = getIntent().getParcelableExtra(EXTRA_TVSHOW);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(R.string.tab_text_2);
        actionBar.setSubtitle(tvShow.getName());

        title.setText(Objects.requireNonNull(tvShow).getName());
        Glide.with(this)
                .load(tvShow.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(poster);
        overview.setText(tvShow.getOverview());
        release.setText(tvShow.getFirstAirDate());
        language.setText(tvShow.getLanguage());
        voteAverage.setText(tvShow.getVoteAverage());

        fab.setOnClickListener(v -> {
            if (isFavorite) {
                favoriteCheck(tvShow.getId());
                Toast.makeText(getApplicationContext(), R.string.already_on_favorite, Toast.LENGTH_SHORT).show();
            } else {
                helper.addTVShowFavorite(tvShow);
                favoriteCheck(tvShow.getId());
                Toast.makeText(getApplicationContext(), R.string.add_to_favorite, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFavoriteTVShowDetail() {
        favoriteTVShow = getIntent().getParcelableExtra(EXTRA_FAVORITE_TVSHOW);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(R.string.favorite_tvshow);
        actionBar.setSubtitle(favoriteTVShow.getTitle());

        title.setText(Objects.requireNonNull(favoriteTVShow).getTitle());
        Glide.with(this)
                .load(favoriteTVShow.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(poster);
        overview.setText(favoriteTVShow.getOverview());
        release.setText(favoriteTVShow.getFirstAirDate());
        language.setText(favoriteTVShow.getLanguage());
        voteAverage.setText(favoriteTVShow.getVoteAverage());

        fab.setOnClickListener(v -> {
            if (isFavorite) {
                helper.deleteFavorite(favoriteTVShow.getId());
                favoriteCheck(favoriteTVShow.getId());
                Toast.makeText(getApplicationContext(), R.string.success_remove_favorite, Toast.LENGTH_SHORT).show();
            } else {
                favoriteCheck(favoriteTVShow.getId());
                Toast.makeText(getApplicationContext(), R.string.already_remove_favorite, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void favoriteCheck(int id) {
        isFavorite = helper.favoriteCheck(id);
        if (isFavorite) {
            fab.setImageResource(R.drawable.ic_favorite);
            isFavorite = true;
        } else {
            fab.setImageResource(R.drawable.ic_favorite_border);
            isFavorite = false;
        }
    }
}