package com.sanislo.movieapp.persistence.dao;

import android.arch.persistence.room.Dao;

import com.sanislo.movieapp.persistence.entity.GenreEntity;

@Dao
public interface GenreDao {
    void insert(GenreEntity genreEntity);
}
