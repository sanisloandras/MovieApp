package com.sanislo.movieapp.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.sanislo.movieapp.persistence.entity.GenreEntity;
import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.entity.MovieJoin;

import java.util.List;

@Dao
public interface MovieJoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(MovieJoin movieJoin);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insert(List<MovieJoin> movieJoinList);

    @Query("SELECT * FROM movie INNER JOIN movie_join ON " +
            "movie.id=movie_join.movieId " +
            "WHERE movie_join.genreId=:genreId")
    LiveData<List<MovieEntity>> getMoviesForGenre(final int genreId);

    @Query("SELECT * FROM genre INNER JOIN movie_join ON " +
            "genre.id=movie_join.genreId " +
            "WHERE movie_join.movieId=:movieId")
    LiveData<List<GenreEntity>> getGenresForMovie(final int movieId);
}
