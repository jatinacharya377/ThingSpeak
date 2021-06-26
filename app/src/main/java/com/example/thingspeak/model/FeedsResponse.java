package com.example.thingspeak.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FeedsResponse {
    @SerializedName("feeds")
    List<Feeds> feedsList;

    public FeedsResponse() {}

    public List<Feeds> getFeedsList() {
        return feedsList;
    }
}
