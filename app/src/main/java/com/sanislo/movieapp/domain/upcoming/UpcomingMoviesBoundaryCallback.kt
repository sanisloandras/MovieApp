package com.sanislo.movieapp.domain.upcoming

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PagedList
import com.sanislo.movieapp.domain.model.MovieListItemModel
import io.reactivex.schedulers.Schedulers

class UpcomingMoviesBoundaryCallback(private val upcomingMoviesRepository: UpcomingMoviesRepository) : PagedList.BoundaryCallback<MovieListItemModel>() {
    private var page = 1

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        load()
    }

    override fun onItemAtEndLoaded(itemAtEnd: MovieListItemModel) {
        super.onItemAtEndLoaded(itemAtEnd)
        load()
    }

    @SuppressLint("CheckResult")
    private fun load() {
        upcomingMoviesRepository.getUpcoming(page)
                .doOnSuccess { result ->
                    upcomingMoviesRepository.saveUpcomingMovies(result)
                    page = result.page + 1
                }
                .subscribeOn(Schedulers.io())
                .subscribe({ result -> Log.d(TAG, "load: $result") }, { error -> Log.d(TAG, "load: error", error) })
    }

    companion object {
        val TAG = UpcomingMoviesBoundaryCallback::class.java.simpleName
    }
}
