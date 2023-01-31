package com.onurmert.filmsjava.Retrofit;

import android.content.Context;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onurmert.filmsjava.Model.Films;
import com.onurmert.filmsjava.Room.FilmsDatabase;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private Retrofit getRetrofit(){

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Gson gsonBuilder = new GsonBuilder().setLenient().create();

        String BASE_URL = "https://yts.mx/api/v2/";

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder))
                .client(httpClient.build())
                .build();
    }

    public void getFilms(Context context){

        IFilmsApi filmsApi = getRetrofit().create(IFilmsApi.class);

        FilmsDatabase filmsDatabase = FilmsDatabase.getDatabase(context);

        filmsApi.getfilms().enqueue(new Callback<Films>() {
            @Override
            public void onResponse(@NonNull Call<Films> call, @NonNull Response<Films> response) {
                if (response.isSuccessful()){
                    if (response.body() != null){

                        FilmsDatabase.databaseExecutor.execute(()->{
                            filmsDatabase.filmsDao().insert(response.body().getData().getMovies());
                        });
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Films> call, @NonNull Throwable t) {
                Log.d("api error: ", t.getLocalizedMessage());
            }
        });
    }
}