package com.sanislo.movieapp.persistence.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

@Entity(tableName = "movies")
public class MovieListItemEntity {
    @PrimaryKey(autoGenerate = true)
    public int _id;
    private String overview;
    private String originalLanguage;
    private String originalTitle;
    private boolean video;
    private String title;
    //private List<Integer> genreIds;
    private String posterPath;
    private String backdropPath;
    private String releaseDate;
    private double popularity;
    private double voteAverage;
    private int id;
    private boolean adult;
    private int voteCount;

    public MovieListItemEntity(String overview, String originalLanguage, String originalTitle, boolean video, String title, String posterPath, String backdropPath, String releaseDate, double popularity, double voteAverage, int id, boolean adult, int voteCount) {
        this.overview = overview;
        this.originalLanguage = originalLanguage;
        this.originalTitle = originalTitle;
        this.video = video;
        this.title = title;
        this.posterPath = posterPath;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
        this.id = id;
        this.adult = adult;
        this.voteCount = voteCount;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieListItemEntity that = (MovieListItemEntity) o;
        return video == that.video &&
                Double.compare(that.popularity, popularity) == 0 &&
                Double.compare(that.voteAverage, voteAverage) == 0 &&
                id == that.id &&
                adult == that.adult &&
                voteCount == that.voteCount &&
                Objects.equals(overview, that.overview) &&
                Objects.equals(originalLanguage, that.originalLanguage) &&
                Objects.equals(originalTitle, that.originalTitle) &&
                Objects.equals(title, that.title) &&
                Objects.equals(posterPath, that.posterPath) &&
                Objects.equals(backdropPath, that.backdropPath) &&
                Objects.equals(releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(overview, originalLanguage, originalTitle, video, title, posterPath, backdropPath, releaseDate, popularity, voteAverage, id, adult, voteCount);
    }

    @Override
    public String toString() {
        return "MovieListItemEntity{" +
                "_id=" + _id +
                ", overview='" + overview + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", video=" + video +
                ", title='" + title + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", popularity=" + popularity +
                ", voteAverage=" + voteAverage +
                ", id=" + id +
                ", adult=" + adult +
                ", voteCount=" + voteCount +
                '}';
    }
}
