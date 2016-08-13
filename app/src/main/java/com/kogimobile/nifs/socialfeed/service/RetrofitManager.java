package com.kogimobile.nifs.socialfeed.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String BASE_URL = "https://api.instagram.com/v1/";

    private static RetrofitManager instance;
    private Retrofit retrofit;
    private InstagramAPI instagramAPI;

    public static RetrofitManager getInstance(){
        if (instance == null){
            instance = new RetrofitManager();
        }
        return instance;
    }


    private RetrofitManager(){
        Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
            .create();
        retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();
    }

    public InstagramAPI getInstagramApi() {
        if (instagramAPI == null) {
            instagramAPI = retrofit.create(InstagramAPI.class);
        }
        return instagramAPI;
    }

}