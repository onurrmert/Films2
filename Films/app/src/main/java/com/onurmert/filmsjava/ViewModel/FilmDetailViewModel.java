package com.onurmert.filmsjava.ViewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onurmert.filmsjava.Model.FilmsModel;
import com.onurmert.filmsjava.Room.FilmsDatabase;

public class FilmDetailViewModel extends ViewModel {

    public MutableLiveData<FilmsModel> filmsModel = new MutableLiveData<>();

    public void getOneFilm(Context context, int id){

        FilmsDatabase filmsDatabase = FilmsDatabase.getDatabase(context);

        FilmsDatabase.databaseExecutor.execute(()->{
            filmsModel.postValue(filmsDatabase.filmsDao().getOneFilm(id));
        });
    }
}
