package com.example.thingspeak.repository;

import com.example.thingspeak.api.ThingSpeakAPI;
import com.example.thingspeak.api.ThingSpeakService;
import com.example.thingspeak.model.FeedsResponse;

import io.reactivex.Completable;
import io.reactivex.Single;

public class ThingSpeakRepository {

    private ThingSpeakAPI api;

    public Single<FeedsResponse> getFeeds(String api_key, int noOfFeeds) {

        return api.getFeeds(api_key, noOfFeeds);
    }

    public Completable createFeed(String api_key, int field1) {

        return api.createFeed(api_key, field1);
    }

    public void init() {

        ThingSpeakService service = new ThingSpeakService();
        api = service.getThingSpeakAPI();
    }
}
