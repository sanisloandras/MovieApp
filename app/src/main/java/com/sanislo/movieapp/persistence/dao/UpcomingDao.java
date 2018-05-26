package com.sanislo.movieapp.persistence.dao;

import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PositionalDataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

import java.util.List;

@Dao
public interface UpcomingDao {
    @Query("SELECT * FROM movies")
    DataSource.Factory<Integer, MovieListItemEntity> upcoming();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] save(List<MovieListItemEntity> movieListItemEntities);
}
