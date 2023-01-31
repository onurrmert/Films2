package com.onurmert.filmsjava.ViewModel;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.onurmert.filmsjava.Model.FilmsModel;
import com.onurmert.filmsjava.Room.FilmsDatabase;
import java.util.List;

public class CurrentViewModel extends ViewModel {

    public MutableLiveData<List<FilmsModel>> filmList = new MutableLiveData<>();

    public void getAllFilms(Context context){

        FilmsDatabase filmsDatabase =  FilmsDatabase.getDatabase(context);

        FilmsDatabase.databaseExecutor.execute(()->{
            filmList.postValue(filmsDatabase.filmsDao().getAllfilms());
        });
    }

    public void delete(Context context){

        FilmsDatabase filmsDatabase = FilmsDatabase.getDatabase(context);

        FilmsDatabase.databaseExecutor.execute(()->{
            filmsDatabase.filmsDao().deleteAll();
        });
    }
}
