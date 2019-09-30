package com.ademeridien.gdk2019.moviecatalogue.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteMovie;
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteTVShow;
import com.ademeridien.gdk2019.moviecatalogue.model.Movie;
import com.ademeridien.gdk2019.moviecatalogue.model.TVShow;

import java.util.ArrayList;

import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FILM_TABLE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.FILM_TYPE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.ID;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.LANGUAGE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.OVERVIEW;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.POSTER;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.RELEASE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.TITLE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.VOTE_AVERAGE;

public class Helper {
    private SQLiteDatabase db;
    private DatabaseHelper databaseHelper;

    public Helper(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() throws SQLException {
        db = databaseHelper.getWritableDatabase();
    }

    public void close() {
        databaseHelper.close();
        if (db.isOpen())
            db.close();
    }

    public ArrayList<FavoriteMovie> getAllFavoriteMovie() {
        ArrayList<FavoriteMovie> arrayListMovie = new ArrayList<>();
        Cursor cursor = db.query(FILM_TABLE, null,
                "FILM_TYPE='0'",
                null,
                null,
                null,
                ID + " DESC",
                null);
        cursor.moveToFirst();
        FavoriteMovie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new FavoriteMovie();
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                movie.setRelease(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                movie.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                movie.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));

                arrayListMovie.add(movie);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayListMovie;
    }

    public ArrayList<FavoriteTVShow> getAllFavoriteTVShow() {
        ArrayList<FavoriteTVShow> arrayListTV = new ArrayList<>();
        Cursor cursor = db.query(FILM_TABLE, null,
                "FILM_TYPE='1'",
                null,
                null,
                null,
                ID + " DESC",
                null);
        cursor.moveToFirst();
        FavoriteTVShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new FavoriteTVShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                tvShow.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE)));
                tvShow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                tvShow.setPoster(cursor.getString(cursor.getColumnIndexOrThrow(POSTER)));
                tvShow.setFirstAirDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                tvShow.setLanguage(cursor.getString(cursor.getColumnIndexOrThrow(LANGUAGE)));
                tvShow.setVoteAverage(cursor.getString(cursor.getColumnIndexOrThrow(VOTE_AVERAGE)));

                arrayListTV.add(tvShow);
                cursor.moveToNext();
            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayListTV;
    }

    public void addMovieFavorite(Movie movie) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(OVERVIEW, movie.getOverview());
        args.put(POSTER, movie.getPosterPath());
        args.put(RELEASE, movie.getReleaseDate());
        args.put(LANGUAGE, movie.getOriginalLanguage());
        args.put(VOTE_AVERAGE, movie.getVoteAverage());
        args.put(FILM_TYPE, "0");
        db.insert(FILM_TABLE, null, args);
    }

    public void addTVShowFavorite(TVShow tv) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ID, tv.getId());
        args.put(TITLE, tv.getName());
        args.put(OVERVIEW, tv.getOverview());
        args.put(POSTER, tv.getPosterPath());
        args.put(RELEASE, tv.getFirstAirDate());
        args.put(LANGUAGE, tv.getLanguage());
        args.put(VOTE_AVERAGE, tv.getVoteAverage());
        args.put(FILM_TYPE, "1");
        db.insert(FILM_TABLE, null, args);
    }

    public void deleteFavorite(int id) {
        db.delete(FILM_TABLE, ID + " = '" + id + "'", null);
    }

    public boolean favoriteCheck(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM film_table WHERE id = " + id + "", null);
        cursor.moveToFirst();
        if (cursor.getCount() <= 0) {
            return false;
        }
        cursor.close();
        return true;
    }
}
