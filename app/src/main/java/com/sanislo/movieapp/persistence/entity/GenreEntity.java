package com.sanislo.movieapp.persistence.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "genre")
public class GenreEntity {
    private String name;
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;

    public GenreEntity(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GenreEntity{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
