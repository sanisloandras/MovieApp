package com.sanislo.movieapp.persistence.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.sanislo.movieapp.persistence.entity.VideoEntity;

import java.util.List;

@Dao
public interface VideoDao {
    @Insert
    void insert(List<VideoEntity> videoEntities);

    @Query("SELECT * FROM video WHERE movieId=:movieId")
    LiveData<List<VideoEntity>> getVideosForMovie(int movieId);
}
