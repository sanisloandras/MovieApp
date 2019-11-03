package com.sanislo.movieapp.domain.movie

import android.arch.lifecycle.LiveData

import com.sanislo.movieapp.domain.model.GenreModel
import com.sanislo.movieapp.domain.model.MovieModel
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse

import io.reactivex.Single

interface MovieRepository {
    fun movie(id: Int): Single<MovieResponse>
    fun movieGenresLiveData(id: Int): LiveData<List<GenreModel>>
    fun movieLiveData(id: Int): LiveData<MovieModel>
}
