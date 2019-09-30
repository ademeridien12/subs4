package com.ademeridien.gdk2019.moviecatalogue.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ademeridien.gdk2019.moviecatalogue.BuildConfig;
import com.ademeridien.gdk2019.moviecatalogue.api.MovieRepository;
import com.ademeridien.gdk2019.moviecatalogue.model.MovieResponse;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<MovieResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        MovieRepository movieRepository = MovieRepository.getInstance();
        mutableLiveData = movieRepository.getMovies(BuildConfig.API_TOKEN, BuildConfig.ORI_LANGUAGE);
    }

    public LiveData<MovieResponse> getMovieRepository() {
        return mutableLiveData;
    }
}
