package com.sanislo.movieapp.domain.upcoming;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.sanislo.movieapp.persistence.response.movieList.MovieListItem;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class UpcomingMoviesDataSource extends PageKeyedDataSource<Integer, MovieListItem> {
    private static final String TAG = UpcomingMoviesDataSource.class.getSimpleName();

    private UpcomingMoviesRepository upcomingMoviesRepository;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public UpcomingMoviesDataSource(UpcomingMoviesRepository upcomingMoviesRepository) {
        this.upcomingMoviesRepository = upcomingMoviesRepository;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, MovieListItem> callback) {
        upcomingMoviesRepository.getUpcoming(1)
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    callback.onResult(result.getResults(),
                            null,
                            2);
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieListItem> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, MovieListItem> callback) {
        upcomingMoviesRepository.getUpcoming(params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(result -> {
                    callback.onResult(result.getResults(),
                            result.getTotalPages() == params.key ? null : result.getTotalPages());
                });
    }
}
