package com.ademeridien.gdk2019.moviecatalogue.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ademeridien.gdk2019.moviecatalogue.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;
    private Service service;

    public static MovieRepository getInstance(){
        if (movieRepository == null){
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    private MovieRepository() {
        service = Base.createService();
    }

    public MutableLiveData<MovieResponse> getMovies(String apiKey, String oriLanguage){
        final MutableLiveData<MovieResponse> movieData = new MutableLiveData<>();
        service.getMovieList(apiKey, oriLanguage).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                if (response.isSuccessful()){
                    movieData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                movieData.setValue(null);
            }
        });
        return movieData;
    }
}
