package com.sanislo.movieapp.persistence.dao;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

import java.util.List;

@Dao
public interface UpcomingDao {
    @Query("SELECT * FROM movies")
    DataSource.Factory<Integer, MovieListItemEntity> upcoming();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] save(List<MovieListItemEntity> movieListItemEntities);

    @Query("DELETE FROM movies")
    void clear();
}
