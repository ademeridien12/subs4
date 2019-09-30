package com.ademeridien.gdk2019.moviecatalogue.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FILM_TABLE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.FILM_TYPE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.ID;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.LANGUAGE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.OVERVIEW;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.POSTER;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.RELEASE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.TITLE;
import static com.ademeridien.gdk2019.moviecatalogue.db.DatabaseContract.FilmColumn.VOTE_AVERAGE;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbMovie";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s"
                    + " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL," +
                    "%s TEXT NOT NULL)",
            FILM_TABLE,
            ID,
            TITLE,
            OVERVIEW,
            POSTER,
            RELEASE,
            LANGUAGE,
            VOTE_AVERAGE,
            FILM_TYPE
    );

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MOVIE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int il) {
        db.execSQL("DROP TABLE IF EXISTS " + FILM_TABLE);
        onCreate(db);
    }
}
