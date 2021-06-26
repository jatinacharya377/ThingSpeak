package com.example.thingspeak.model;

import com.google.gson.annotations.SerializedName;

public class Feeds {

    @SerializedName("created_at")
    String createdAt;
    @SerializedName("entry_id")
    int entryId;
    @SerializedName("field1")
    String field1;
    @SerializedName("field2")
    String field2;
    @SerializedName("field3")
    String field3;

    public Feeds() {}

    public String getCreatedAt() {
        return createdAt;
    }

    public int getEntryId() {
        return entryId;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
