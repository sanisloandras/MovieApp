package com.sanislo.movieapp.persistence.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.sanislo.movieapp.persistence.response.movieDetails.ProductionCompaniesItem;
import com.sanislo.movieapp.persistence.response.movieDetails.ProductionCountriesItem;
import com.sanislo.movieapp.persistence.response.movieDetails.SpokenLanguagesItem;

import java.util.List;

@Entity(tableName = "movie")
public class MovieEntity {
    private String originalLanguage;
    private String imdbId;
    private boolean video;
    private String title;
    private String backdropPath;
    private int revenue;
    //private List<GenreEntity> genres;
    private double popularity;
    @Ignore
    private List<ProductionCountriesItem> productionCountries;
    @PrimaryKey
    @ColumnInfo(name = "id")
    private int id;
    private int voteCount;
    private int budget;
    private String overview;
    private String originalTitle;
    private int runtime;
    private String posterPath;
    @Ignore
    private List<SpokenLanguagesItem> spokenLanguages;
    @Ignore
    private List<ProductionCompaniesItem> productionCompanies;
    private String releaseDate;
    private double voteAverage;
    @Ignore
    private Object belongsToCollection;
    private String tagline;
    private boolean adult;
    private String homepage;
    private String status;

    public MovieEntity() {
    }

    public MovieEntity(String originalLanguage, String imdbId, boolean video, String title, String backdropPath, int revenue, double popularity, int id, int voteCount, int budget, String overview, String originalTitle, int runtime, String posterPath, String releaseDate, double voteAverage, Object belongsToCollection, String tagline, boolean adult, String homepage, String status) {
        this.originalLanguage = originalLanguage;
        this.imdbId = imdbId;
        this.video = video;
        this.title = title;
        this.backdropPath = backdropPath;
        this.revenue = revenue;
        this.popularity = popularity;
        this.id = id;
        this.voteCount = voteCount;
        this.budget = budget;
        this.overview = overview;
        this.originalTitle = originalTitle;
        this.runtime = runtime;
        this.posterPath = posterPath;
        this.releaseDate = releaseDate;
        this.voteAverage = voteAverage;
        this.belongsToCollection = belongsToCollection;
        this.tagline = tagline;
        this.adult = adult;
        this.homepage = homepage;
        this.status = status;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
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

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public List<ProductionCountriesItem> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountriesItem> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public List<SpokenLanguagesItem> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguagesItem> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public List<ProductionCompaniesItem> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompaniesItem> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Object getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(Object belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
