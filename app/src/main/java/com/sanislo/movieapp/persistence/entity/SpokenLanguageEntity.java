package com.sanislo.movieapp.persistence.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "spoken_language")
public class SpokenLanguageEntity {
    private String name;
    @PrimaryKey
    private String iso6391;

    public SpokenLanguageEntity(String name, String iso6391) {
        this.name = name;
        this.iso6391 = iso6391;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    @Override
    public String toString() {
        return "SpokenLanguageEntity{" +
                "name='" + name + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                '}';
    }
}
