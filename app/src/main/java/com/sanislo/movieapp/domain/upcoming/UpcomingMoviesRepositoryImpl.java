package com.sanislo.movieapp.domain.upcoming;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.util.Log;

import com.sanislo.movieapp.domain.mapper.MovieListItemEntityToMovieListItemModel;
import com.sanislo.movieapp.domain.mapper.MovieResponseMapper;
import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse;

import java.util.concurrent.Executors;

import io.reactivex.Single;

public class UpcomingMoviesRepositoryImpl implements UpcomingMoviesRepository {
    private static final String TAG = UpcomingMoviesRepository.class.getSimpleName();
    public static final int PAGE_SIZE = 12;

    private MovieResponseMapper movieResponseMapper = new MovieResponseMapper();
    private MovieListItemEntityToMovieListItemModel movieListItemEntityToMovieListItemModel
            = new MovieListItemEntityToMovieListItemModel();
    private MovieAppApi mMovieAppApi;
    private UpcomingDao mUpcomingDao;

    public UpcomingMoviesRepositoryImpl(MovieAppApi mMovieAppApi, UpcomingDao mUpcomingDao) {
        this.mMovieAppApi = mMovieAppApi;
        this.mUpcomingDao = mUpcomingDao;
    }

    @Override
    public Single<MovieListResponse> getUpcoming(int page) {
        return mMovieAppApi.upcoming(page);
    }

    @Override
    public Single<MovieListResponse> getUpcoming() {
        return mMovieAppApi.upcoming();
    }

    @Override
    public Single<MovieListResponse> refreshUpcoming() {
        return mMovieAppApi.upcoming()
                .doOnSuccess(result -> {
                    mUpcomingDao.clear();
                    saveUpcomingMovies(result);
                })
                .doOnSuccess(movieListResponse -> {
                    Log.d(TAG, "refreshUpcoming: " + movieListResponse);
                });
    }

    @Override
    public void saveUpcomingMovies(MovieListResponse movieListResponse) {
        Executors.newSingleThreadExecutor().execute(() -> {
            long[] inseted = mUpcomingDao.save(movieResponseMapper.map2(movieListResponse.getResults()));
            Log.d(TAG, "saveUpcomingMovies: " + inseted.length);
        });
    }

    @Override
    public LiveData<PagedList<MovieListItemModel>> getUpcomingLiveData() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(PAGE_SIZE)
                .setInitialLoadSizeHint(PAGE_SIZE * 2)
                .setPrefetchDistance(PAGE_SIZE / 6)
                .build();
        DataSource.Factory<Integer, MovieListItemModel> dataSource = mUpcomingDao.upcoming()
                .map(input -> movieListItemEntityToMovieListItemModel.map2(input));
        LivePagedListBuilder builder =
                new LivePagedListBuilder(dataSource, config)
                .setBoundaryCallback(new UpcomingMoviesBoundaryCallback(this));
        return builder.build();
    }
}
