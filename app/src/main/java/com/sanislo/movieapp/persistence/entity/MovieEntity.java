package com.sanislo.movieapp.persistence.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.sanislo.movieapp.persistence.response.movieDetails.GenresItem;
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
    private List<GenresItem> genres;
    private double popularity;
    private List<ProductionCountriesItem> productionCountries;
    @PrimaryKey
    private int id;
    private int voteCount;
    private int budget;
    private String overview;
    private String originalTitle;
    private int runtime;
    private String posterPath;
    private List<SpokenLanguagesItem> spokenLanguages;
    private List<ProductionCompaniesItem> productionCompanies;
    private String releaseDate;
    private double voteAverage;
    private Object belongsToCollection;
    private String tagline;
    private boolean adult;
    private String homepage;
    private String status;
}
