package com.sanislo.movieapp.domain.model;

import android.arch.persistence.room.PrimaryKey;

public class VideoModel {
    private String site;
    private int size;
    private String iso31661;
    private String name;
    private String id;
    private int movieId;
    private String type;
    private String iso6391;
    private String key;

    public VideoModel(String site, int size, String iso31661, String name, String id, int movieId, String type, String iso6391, String key) {
        this.site = site;
        this.size = size;
        this.iso31661 = iso31661;
        this.name = name;
        this.id = id;
        this.movieId = movieId;
        this.type = type;
        this.iso6391 = iso6391;
        this.key = key;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIso6391() {
        return iso6391;
    }

    public void setIso6391(String iso6391) {
        this.iso6391 = iso6391;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "VideoModel{" +
                "site='" + site + '\'' +
                ", size=" + size +
                ", iso31661='" + iso31661 + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", movieId=" + movieId +
                ", type='" + type + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
