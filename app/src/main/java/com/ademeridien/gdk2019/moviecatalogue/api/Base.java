package com.ademeridien.gdk2019.moviecatalogue.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class Base {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static <S> S createService() {
        return retrofit.create((Class<S>) Service.class);
    }
}
