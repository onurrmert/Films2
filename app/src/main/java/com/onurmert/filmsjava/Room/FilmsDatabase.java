package com.onurmert.filmsjava.Room;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.onurmert.filmsjava.Model.FilmsModel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {FilmsModel.class}, version = 1, exportSchema = false)
public abstract class FilmsDatabase extends RoomDatabase {

    public abstract IFilmsDao filmsDao();

    public static volatile FilmsDatabase INSTANCE;

    public static final int NUMBERTHREAD = 6;

    public static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBERTHREAD);

    public static FilmsDatabase getDatabase(Context context){

        if (INSTANCE == null){
            synchronized (FilmsDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(
                            context,
                            FilmsDatabase.class,
                            "filmsDatabase")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
