package com.ademeridien.gdk2019.moviecatalogue.model;

import android.os.Parcel;
import android.os.Parcelable;

public class FavoriteTVShow implements Parcelable {
    private int id;
    private String title, overview, poster, firstAirDate, language, voteAverage;

    public FavoriteTVShow() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(String voteAverage) {
        this.voteAverage = voteAverage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(overview);
        parcel.writeString(poster);
        parcel.writeString(firstAirDate);
        parcel.writeString(language);
        parcel.writeString(voteAverage);
    }

    private FavoriteTVShow(Parcel in) {
        id = in.readInt();
        title = in.readString();
        overview = in.readString();
        poster = in.readString();
        firstAirDate = in.readString();
        language = in.readString();
        voteAverage = in.readString();
    }

    public static final Creator<FavoriteTVShow> CREATOR = new Creator<FavoriteTVShow>() {
        @Override
        public FavoriteTVShow createFromParcel(Parcel in) {
            return new FavoriteTVShow(in);
        }

        @Override
        public FavoriteTVShow[] newArray(int size) {
            return new FavoriteTVShow[size];
        }
    };
}
