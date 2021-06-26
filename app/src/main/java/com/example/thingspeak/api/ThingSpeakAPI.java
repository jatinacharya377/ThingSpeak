package com.example.thingspeak.api;

import com.example.thingspeak.model.FeedsResponse;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ThingSpeakAPI {

    @GET("channels/533401/feeds.json?")
    Single<FeedsResponse> getFeeds(
            @Query("api_key") String api_key,
            @Query("results") int noOfFeeds
    );

    @FormUrlEncoded
    @POST("update?")
    Completable createFeed(
            @Field("api_key") String api_key,
            @Field("field1") int field1
    );
}