package com.onurmert.filmsjava.Model;

import com.google.gson.annotations.SerializedName;

public class Films {

    @SerializedName("status_message")
    private final String status_message;

    @SerializedName("data")
    private final DataFilms data;

    @SerializedName("status")
    private final String status;

    public Films(String status_message, DataFilms data, String status) {
        this.status = status;
        this.status_message = status_message;
        this.data = data;
    }

    public String getStatus_message() {
        return status_message;
    }

    public DataFilms getData() {
        return this.data;
    }

    public String getStatus() {
        return status;
    }
}
