package com.sanislo.movieapp.domain.model

data class MovieListItemModel(val _id: Int,
                              val overview: String?,
                              val originalLanguage: String?,
                              val originalTitle: String?,
                              val isVideo: Boolean,
                              val title: String?,
                              val posterPath: String?,
                              val backdropPath: String?,
                              val releaseDate: String?,
                              val popularity: Double,
                              val voteAverage: Double,
                              val id: Int,
                              val isAdult: Boolean,
                              val voteCount: Int)
