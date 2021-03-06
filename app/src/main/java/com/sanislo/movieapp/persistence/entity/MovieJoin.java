package com.sanislo.movieapp.persistence.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;

@Entity(tableName = "movie_join",
    primaryKeys = {"movieId", "genreId"},
    foreignKeys = {@ForeignKey(entity = MovieEntity.class,
                    parentColumns = "id",
                    childColumns = "movieId"),
            @ForeignKey(entity = GenreEntity.class,
                    parentColumns = "id",
                    childColumns = "genreId")}
)
public class MovieJoin {
    private int movieId;
    private int genreId;

    public MovieJoin(int movieId, int genreId) {
        this.movieId = movieId;
        this.genreId = genreId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Override
    public String toString() {
        return "MovieJoin{" +
                "movieId=" + movieId +
                ", genreId=" + genreId +
                '}';
    }
}