package com.sanislo.movieapp.domain.model;

import java.util.Objects;

public class YoutubeVideoModel {
    private String site;
    private int size;
    private String iso31661;
    private String name;
    private String id;
    private int movieId;
    private String type;
    private String iso6391;
    private String key;
    private String url;

    public YoutubeVideoModel(String site, int size, String iso31661, String name, String id, int movieId, String type, String iso6391, String key, String url) {
        this.site = site;
        this.size = size;
        this.iso31661 = iso31661;
        this.name = name;
        this.id = id;
        this.movieId = movieId;
        this.type = type;
        this.iso6391 = iso6391;
        this.key = key;
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public int getSize() {
        return size;
    }

    public String getIso31661() {
        return iso31661;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getMovieId() {
        return movieId;
    }

    public String getType() {
        return type;
    }

    public String getIso6391() {
        return iso6391;
    }

    public String getKey() {
        return key;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YoutubeVideoModel that = (YoutubeVideoModel) o;
        return size == that.size &&
                movieId == that.movieId &&
                Objects.equals(site, that.site) &&
                Objects.equals(iso31661, that.iso31661) &&
                Objects.equals(name, that.name) &&
                Objects.equals(id, that.id) &&
                Objects.equals(type, that.type) &&
                Objects.equals(iso6391, that.iso6391) &&
                Objects.equals(key, that.key) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {

        return Objects.hash(site, size, iso31661, name, id, movieId, type, iso6391, key, url);
    }

    @Override
    public String toString() {
        return "YoutubeVideoModel{" +
                "site='" + site + '\'' +
                ", size=" + size +
                ", iso31661='" + iso31661 + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", movieId=" + movieId +
                ", type='" + type + '\'' +
                ", iso6391='" + iso6391 + '\'' +
                ", key='" + key + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
