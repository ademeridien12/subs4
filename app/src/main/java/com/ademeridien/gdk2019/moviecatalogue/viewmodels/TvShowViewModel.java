package com.ademeridien.gdk2019.moviecatalogue.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ademeridien.gdk2019.moviecatalogue.BuildConfig;
import com.ademeridien.gdk2019.moviecatalogue.api.TvShowRepository;
import com.ademeridien.gdk2019.moviecatalogue.model.TVResponse;

public class TvShowViewModel extends ViewModel {
    private MutableLiveData<TVResponse> mutableLiveData;

    public void init(){
        if (mutableLiveData != null){
            return;
        }
        TvShowRepository tvShowRepository = TvShowRepository.getInstance();
        mutableLiveData = tvShowRepository.getTvs(BuildConfig.API_TOKEN, BuildConfig.ORI_LANGUAGE);
    }

    public LiveData<TVResponse> getTVRepository() {
        return mutableLiveData;
    }
}
