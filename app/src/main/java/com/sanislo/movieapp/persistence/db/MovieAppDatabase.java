package com.sanislo.movieapp.persistence.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

@Database(entities = {MovieListItemEntity.class, MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieAppDatabase extends RoomDatabase {
    public abstract UpcomingDao upcomingDao();
    public abstract MovieDao movieDao();
}

