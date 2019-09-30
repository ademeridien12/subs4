package com.ademeridien.gdk2019.moviecatalogue;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.ademeridien.gdk2019.moviecatalogue.db.Helper;
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteMovie;
import com.ademeridien.gdk2019.moviecatalogue.model.Movie;
import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class ViewDetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    public static final String EXTRA_FAVORITE_MOVIE = "extra_favorite_movie";
    TextView title, overview, release, language, voteAverage;
    ImageView poster;
    Movie movie;
    FavoriteMovie favoriteMovie;
    FloatingActionButton fab;
    Helper helper;
    boolean isFavorite = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_detail_movie);

        helper = new Helper(this);
        helper.open();

        poster = findViewById(R.id.image_film);
        title = findViewById(R.id.tv_title);
        overview = findViewById(R.id.tv_description);
        release = findViewById(R.id.tv_release);
        language = findViewById(R.id.tv_language);
        voteAverage = findViewById(R.id.tv_voteAverage);
        fab = findViewById(R.id.fab_favorite);

        if (getIntent().getParcelableExtra(EXTRA_MOVIE) != null) {
            getMovieDetail();
            favoriteCheck(movie.getId());
        } else {
            getFavoriteMovieDetail();
            favoriteCheck(favoriteMovie.getId());
        }
    }

    private void getMovieDetail() {
        movie = getIntent().getParcelableExtra(EXTRA_MOVIE);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(R.string.tab_text_1);
        actionBar.setSubtitle(movie.getTitle());

        title.setText(Objects.requireNonNull(movie).getTitle());
        Glide.with(this)
                .load(movie.getPosterPath())
                .placeholder(R.color.colorPrimary)
                .into(poster);
        overview.setText(movie.getOverview());
        release.setText(movie.getReleaseDate());
        language.setText(movie.getOriginalLanguage());
        voteAverage.setText(movie.getVoteAverage());

        fab.setOnClickListener(v -> {
            if (isFavorite) {
                favoriteCheck(movie.getId());
                Toast.makeText(getApplicationContext(), R.string.already_on_favorite, Toast.LENGTH_SHORT).show();
            } else {
                helper.addMovieFavorite(movie);
                favoriteCheck(movie.getId());
                Toast.makeText(getApplicationContext(), R.string.add_to_favorite, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFavoriteMovieDetail() {
        favoriteMovie = getIntent().getParcelableExtra(EXTRA_FAVORITE_MOVIE);

        ActionBar actionBar = getSupportActionBar();
        Objects.requireNonNull(actionBar).setElevation(4);
        actionBar.setTitle(R.string.favorite_movie);
        actionBar.setSubtitle(favoriteMovie.getTitle());

        title.setText(Objects.requireNonNull(favoriteMovie).getTitle());
        Glide.with(this)
                .load(favoriteMovie.getPoster())
                .placeholder(R.color.colorPrimary)
                .into(poster);
        overview.setText(favoriteMovie.getOverview());
        release.setText(favoriteMovie.getRelease());
        language.setText(favoriteMovie.getLanguage());
        voteAverage.setText(favoriteMovie.getVoteAverage());

        fab.setOnClickListener(v -> {
            if (isFavorite) {
                helper.deleteFavorite(favoriteMovie.getId());
                favoriteCheck(favoriteMovie.getId());
                Toast.makeText(getApplicationContext(), R.string.success_remove_favorite, Toast.LENGTH_SHORT).show();
            } else {
                favoriteCheck(favoriteMovie.getId());
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
