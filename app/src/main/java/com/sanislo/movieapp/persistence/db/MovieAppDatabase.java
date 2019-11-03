package com.sanislo.movieapp.persistence.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.sanislo.movieapp.persistence.dao.GenreDao;
import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.dao.MovieJoinDao;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.persistence.dao.VideoDao;
import com.sanislo.movieapp.persistence.entity.GenreEntity;
import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.entity.MovieJoin;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;
import com.sanislo.movieapp.persistence.entity.VideoEntity;

@Database(entities = {
        MovieListItemEntity.class,
        MovieEntity.class,
        GenreEntity.class,
        MovieJoin.class,
        VideoEntity.class}, version = 1, exportSchema = false)
public abstract class MovieAppDatabase extends RoomDatabase {
    public abstract UpcomingDao upcomingDao();
    public abstract MovieDao movieDao();
    public abstract MovieJoinDao movieJoinDao();
    public abstract VideoDao videoDao();
    public abstract GenreDao genreDao();
}

