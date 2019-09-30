package com.ademeridien.gdk2019.moviecatalogue.api;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.ademeridien.gdk2019.moviecatalogue.model.TVResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowRepository {
    private static TvShowRepository tvShowRepository;
    private Service service;

    public static TvShowRepository getInstance(){
        if (tvShowRepository == null){
            tvShowRepository = new TvShowRepository();
        }
        return tvShowRepository;
    }

    private TvShowRepository() {
        service = Base.createService();
    }

    public MutableLiveData<TVResponse> getTvs(String apiKey, String oriLanguage){
        final MutableLiveData<TVResponse> tvData = new MutableLiveData<>();
        service.getTVList(apiKey, oriLanguage).enqueue(new Callback<TVResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVResponse> call, @NonNull Response<TVResponse> response) {
                if (response.isSuccessful()){
                    tvData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TVResponse> call, @NonNull Throwable t) {
                tvData.setValue(null);
            }
        });
        return tvData;
    }
}
