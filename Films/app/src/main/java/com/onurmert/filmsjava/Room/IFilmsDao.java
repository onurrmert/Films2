package com.onurmert.filmsjava.Room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.onurmert.filmsjava.Model.FilmsModel;
import java.util.List;

@Dao
public interface IFilmsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<FilmsModel> filmsModels);

    @Query("SELECT * FROM filmsTable")
    List<FilmsModel> getAllfilms();

    @Query("SELECT * FROM filmsTable WHERE id = :id")
    FilmsModel getOneFilm(int id);

    @Query("DELETE FROM filmsTable")
    void deleteAll();
}
