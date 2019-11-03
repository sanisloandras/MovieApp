package com.sanislo.movieapp.domain.model

data class YoutubeVideoModel(val site: String,
                             val size: Int,
                             val iso31661: String,
                             val name: String,
                             val id: String,
                             val movieId: Int,
                             val type: String,
                             val iso6391: String,
                             val key: String,
                             val url: String)
