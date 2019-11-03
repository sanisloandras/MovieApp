package com.sanislo.movieapp.domain.upcoming

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sanislo.movieapp.domain.model.MovieListItemModel
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse
import io.reactivex.Single

interface UpcomingMoviesRepository {
    fun upcoming(): Single<MovieListResponse>
    fun upcomingMoviesLiveData(): LiveData<PagedList<MovieListItemModel>>
    fun getUpcoming(page: Int): Single<MovieListResponse>
    fun refreshUpcoming(): Single<MovieListResponse>
    fun saveUpcomingMovies(movieListResponse: MovieListResponse)
}
