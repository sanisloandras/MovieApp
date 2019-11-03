package com.sanislo.movieapp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

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
