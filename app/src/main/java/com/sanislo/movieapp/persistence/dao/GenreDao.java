package com.sanislo.movieapp.persistence.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.sanislo.movieapp.persistence.entity.GenreEntity;

import java.util.List;

@Dao
public interface GenreDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<GenreEntity> genreEntityList);
}
