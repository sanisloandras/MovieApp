package com.sanislo.movieapp.persistence.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.sanislo.movieapp.persistence.entity.VideoEntity;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface VideoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<VideoEntity> videoEntities);

    @Query("SELECT * FROM video WHERE movieId=:movieId")
    LiveData<List<VideoEntity>> getVideosForMovie(int movieId);

    @Query("SELECT * FROM video WHERE movieId=:movieId")
    Flowable<List<VideoEntity>> getVideosForMovieFlowable(int movieId);

    @Query("SELECT * FROM video WHERE movieId=:movieId AND site=:site")
    LiveData<List<VideoEntity>> getVideosForMovieBySiteLiveData(int movieId,
                                                                 String site);
}
