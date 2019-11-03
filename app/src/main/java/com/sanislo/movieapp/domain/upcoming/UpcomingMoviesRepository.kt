package com.sanislo.movieapp.domain.upcoming

import android.arch.lifecycle.LiveData
import android.arch.paging.PagedList
import com.sanislo.movieapp.domain.model.MovieListItemModel
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse
import io.reactivex.Single

interface UpcomingMoviesRepository {
    val upcoming: Single<MovieListResponse>
    val upcomingMoviesLiveData: LiveData<PagedList<MovieListItemModel>>
    fun getUpcoming(page: Int): Single<MovieListResponse>
    fun refreshUpcoming(): Single<MovieListResponse>
    fun saveUpcomingMovies(movieListResponse: MovieListResponse)
}
