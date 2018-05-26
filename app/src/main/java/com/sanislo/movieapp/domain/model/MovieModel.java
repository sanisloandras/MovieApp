package com.sanislo.movieapp.domain.model;

import java.util.List;

public class MovieModel {
    private String originalLanguage;
    private String imdbId;
    private boolean video;
    private String title;
    private String backdropPath;
    private int revenue;
    private List<GenreModel> genres;
    private double popularity;
    private List<ProductionCountryModel> productionCountries;
    private int id;
    private int voteCount;
    private int budget;
    private String overview;
    private String originalTitle;
    private int runtime;
    private String posterPath;
    private List<SpokenLanguageModel> spokenLanguages;
    private List<ProductionCompanyModel> productionCompanies;
    private String releaseDate;
    private double voteAverage;
    private Object belongsToCollection;
    private String tagline;
    private boolean adult;
    private String homepage;
    private String status;

    public MovieModel(String originalLanguage, String imdbId, boolean video, String title, String backdropPath, int revenue, List<GenreModel> genres, double popularity, List<ProductionCountryModel> productionCountries, int id, int voteCount, int budget, String overview, String originalTitle, int runtime, String posterPath, List<SpokenLanguageModel> spokenLanguages, List<ProductionCompanyModel> productionCompanies, String releaseDate, double voteAverage, Object belongsToCollection, String tagline, boolean adult, String homepage, String status) {
        this.originalLanguage = originalLanguage;
        this.imdbId = imdbId;
        this.video = video;
        this.title = title;
        this.backdropPath = backdropPath;
        this.revenue = revenue;
        this.genres = genres;
        this.popularity = popularity;
        this.productionCountries = productionCountries;
        this.id = id;
        this.voteCount = voteCount;
        this.budget = budget;
        this.overview = overview;
        this.originalTitle = originalTitle;
        this.runtime = runtime;
        this.posterPath = posterPath;
        this.spokenLanguages = spokenLanguages;
        this.productionCompanies = productionCompanies;
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

    public List<GenreModel> getGenres() {
        return genres;
    }

    public void setGenres(List<GenreModel> genres) {
        this.genres = genres;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public List<ProductionCountryModel> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<ProductionCountryModel> productionCountries) {
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

    public List<SpokenLanguageModel> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<SpokenLanguageModel> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public List<ProductionCompanyModel> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<ProductionCompanyModel> productionCompanies) {
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

    @Override
    public String toString() {
        return "MovieModel{" +
                "originalLanguage='" + originalLanguage + '\'' +
                ", imdbId='" + imdbId + '\'' +
                ", video=" + video +
                ", title='" + title + '\'' +
                ", backdropPath='" + backdropPath + '\'' +
                ", revenue=" + revenue +
                ", genres=" + genres +
                ", popularity=" + popularity +
                ", productionCountries=" + productionCountries +
                ", id=" + id +
                ", voteCount=" + voteCount +
                ", budget=" + budget +
                ", overview='" + overview + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", runtime=" + runtime +
                ", posterPath='" + posterPath + '\'' +
                ", spokenLanguages=" + spokenLanguages +
                ", productionCompanies=" + productionCompanies +
                ", releaseDate='" + releaseDate + '\'' +
                ", voteAverage=" + voteAverage +
                ", belongsToCollection=" + belongsToCollection +
                ", tagline='" + tagline + '\'' +
                ", adult=" + adult +
                ", homepage='" + homepage + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
