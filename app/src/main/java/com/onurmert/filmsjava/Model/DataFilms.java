package com.onurmert.filmsjava.Model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class DataFilms {

    @SerializedName("movies")
    private final List<FilmsModel> movies;

    @SerializedName("page_number")
    private final int page_number;

    @SerializedName("movie_count")
    private final int movie_count;

    @SerializedName("limit")
    private final int limit;

    DataFilms(List<FilmsModel> movies, int page_number, int movie_count, int limit){
        this.movies = movies;
        this.page_number = page_number;
        this.movie_count = movie_count;
        this.limit = limit;
    }

    public List<FilmsModel> getMovies() {
        return this.movies;
    }

    public int getPage_number() {
        return page_number;
    }

    public int getMovie_count() {
        return movie_count;
    }

    public int getLimit() {
        return limit;
    }
}
