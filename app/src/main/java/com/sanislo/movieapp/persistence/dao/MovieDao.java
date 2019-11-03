package com.sanislo.movieapp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sanislo.movieapp.persistence.entity.MovieEntity;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<MovieEntity> movie(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieEntity movieEntity);
}
