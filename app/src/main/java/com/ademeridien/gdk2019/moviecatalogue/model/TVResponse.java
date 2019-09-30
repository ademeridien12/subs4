package com.ademeridien.gdk2019.moviecatalogue.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TVResponse {
    @SerializedName("results")
    @Expose
    private List<TVShow> results = null;

    public List<TVShow> getResults() {
        return results;
    }
}
