package com.sanislo.movieapp.domain.model

data class MovieModel(val originalLanguage: String,
                      val imdbId: String,
                      val video: Boolean,
                      val title: String,
                      val backdropPath: String,
                      val revenue: Int,
                      val genres: List<GenreModel>?,
                      val popularity: Double,
                      val productionCountries: List<ProductionCountryModel>?,
                      val id: Int,
                      val voteCount: Int,
                      val budget: Int,
                      val overview: String,
                      val originalTitle: String,
                      val runtime: Int,
                      val posterPath: String,
                      val spokenLanguages: List<SpokenLanguageModel>?,
                      val productionCompanies: List<ProductionCompanyModel>?,
                      val releaseDate: String,
                      val voteAverage: Double,
                      val belongsToCollection: Any?,
                      val tagline: String,
                      val adult: Boolean,
                      val homepage: String?,
                      val status: String)
