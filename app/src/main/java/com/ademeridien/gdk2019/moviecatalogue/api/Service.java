package com.ademeridien.gdk2019.moviecatalogue.api;

import com.ademeridien.gdk2019.moviecatalogue.model.MovieResponse;
import com.ademeridien.gdk2019.moviecatalogue.model.TVResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("discover/movie")
    Call<MovieResponse> getMovieList(@Query("api_key") String apiKey, @Query("language") String oriLanguage);

    @GET("discover/tv")
    Call<TVResponse> getTVList(@Query("api_key") String apiKey, @Query("language") String oriLanguage);
}
