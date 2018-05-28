package com.sanislo.movieapp.domain.upcoming;

import android.annotation.SuppressLint;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;
import android.util.Log;

import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

import io.reactivex.schedulers.Schedulers;

public class UpcomingMoviesBoundaryCallback extends PagedList.BoundaryCallback<MovieListItemModel> {
    public static final String TAG = UpcomingMoviesBoundaryCallback.class.getSimpleName();

    private UpcomingMoviesRepository upcomingMoviesRepository;
    private int page = 1;

    public UpcomingMoviesBoundaryCallback(UpcomingMoviesRepository upcomingMoviesRepository) {
        this.upcomingMoviesRepository = upcomingMoviesRepository;
    }

    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
        Log.d(TAG, "onZeroItemsLoaded: ");
        load();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull MovieListItemModel itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
        Log.d(TAG, "onItemAtEndLoaded: ");
        load();
    }

    @SuppressLint("CheckResult")
    private void load() {
        upcomingMoviesRepository.getUpcoming(page)
                .doOnSuccess(result -> {
                    upcomingMoviesRepository.saveUpcomingMovies(result);
                    page = result.getPage() + 1;
                })
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    Log.d(TAG, "load: " + result);
                }, error -> {
                    Log.d(TAG, "load: error", error);
                });
    }
}
