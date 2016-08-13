package com.kogimobile.nifs.socialfeed.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.kogimobile.nifs.socialfeed.model.instagram.InstagramResponse;
import com.kogimobile.nifs.socialfeed.model.instagram.InstagramUser;

public interface InstagramAPI {

    @GET("users/self")
    Call<InstagramUser> getUserInfo(@Query("access_token") String token);

    @GET("users/self/media/recent")
    Call<InstagramResponse> getRecentPictures(@Query("access_token") String token);

    @GET("users/self/media/liked")
    Call<InstagramResponse> getLikedPictures(@Query("access_token") String token);

}