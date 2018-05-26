package com.sanislo.movieapp.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("SELECT * FROM movie WHERE id = :id")
    LiveData<MovieEntity> movie(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieEntity movieEntity);
}
