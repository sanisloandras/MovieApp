package com.sanislo.movieapp.persistence.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;

import com.sanislo.movieapp.persistence.entity.GenreEntity;

import java.util.List;

@Dao
public interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<GenreEntity> genreEntityList);
}
