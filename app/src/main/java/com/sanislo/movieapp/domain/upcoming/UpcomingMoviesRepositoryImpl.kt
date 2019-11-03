package com.sanislo.movieapp.domain.upcoming

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import android.util.Log
import com.sanislo.movieapp.domain.mapper.MovieListItemEntityToMovieListItemModel
import com.sanislo.movieapp.domain.mapper.MovieResponseMapper
import com.sanislo.movieapp.domain.model.MovieListItemModel
import com.sanislo.movieapp.persistence.api.MovieAppApi
import com.sanislo.movieapp.persistence.dao.UpcomingDao
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse
import io.reactivex.Single
import java.util.concurrent.Executors

class UpcomingMoviesRepositoryImpl(private val mMovieAppApi: MovieAppApi, private val mUpcomingDao: UpcomingDao) : UpcomingMoviesRepository {
    private val movieResponseMapper = MovieResponseMapper()
    private val movieListItemEntityToMovieListItemModel = MovieListItemEntityToMovieListItemModel()

    override fun upcoming(): Single<MovieListResponse> {
        return mMovieAppApi.upcoming()
    }

    override fun upcomingMoviesLiveData(): LiveData<PagedList<MovieListItemModel>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .setPrefetchDistance(PAGE_SIZE / 6)
                .build()
        val dataSource = mUpcomingDao.upcoming()
                .map { input -> movieListItemEntityToMovieListItemModel.map2(input) }
        val builder = LivePagedListBuilder(dataSource, config)
                .setBoundaryCallback(UpcomingMoviesBoundaryCallback(this))
        return builder.build()
    }

    override fun getUpcoming(page: Int): Single<MovieListResponse> {
        return mMovieAppApi.upcoming(page)
    }

    override fun refreshUpcoming(): Single<MovieListResponse> {
        return mMovieAppApi.upcoming()
                .doOnSuccess { result ->
                    mUpcomingDao.clear()
                    saveUpcomingMovies(result)
                }
    }

    override fun saveUpcomingMovies(movieListResponse: MovieListResponse) {
        Executors.newSingleThreadExecutor().execute {
            val inserted = mUpcomingDao.save(movieResponseMapper.map2(movieListResponse.results))
            Log.d(TAG, "saveUpcomingMovies: " + inserted.size)
        }
    }

    companion object {
        private val TAG = UpcomingMoviesRepository::class.java.simpleName
        const val PAGE_SIZE = 12
    }
}
