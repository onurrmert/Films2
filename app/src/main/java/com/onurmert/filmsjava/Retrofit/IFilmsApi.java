package com.onurmert.filmsjava.Retrofit;

import com.onurmert.filmsjava.Model.Films;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IFilmsApi {

    @GET("list_movies.json?sort_by=date_added")
    Call<Films> getfilms();
}