package com.sanislo.movieapp.presentation.upcomingMovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.databinding.ObservableBoolean;

import com.sanislo.movieapp.domain.SingleLiveEvent;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository;
import com.sanislo.movieapp.domain.model.MovieListItemModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class UpcomingMoviesViewModel extends ViewModel {
    private static final String TAG = UpcomingMoviesViewModel.class.getSimpleName();

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final MutableLiveData<Boolean> isRefreshing = new MutableLiveData<>();
    private final SingleLiveEvent<Throwable> error = new SingleLiveEvent<>();
    private final UpcomingMoviesRepository mUpcomingMoviesRepository;
    private final LiveData<PagedList<MovieListItemModel>> movieListItemEntityLiveData;

    public UpcomingMoviesViewModel(UpcomingMoviesRepository upcomingMoviesRepository) {
        this.mUpcomingMoviesRepository = upcomingMoviesRepository;
        movieListItemEntityLiveData = mUpcomingMoviesRepository.getUpcomingLiveData();
    }

    public LiveData<PagedList<MovieListItemModel>> getMovieListItemEntityLiveData() {
        return movieListItemEntityLiveData;
    }

    public void refresh() {
        Disposable d = mUpcomingMoviesRepository.refreshUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> isRefreshing.setValue(true))
                .subscribe(result -> {
                    isRefreshing.setValue(false);
                }, this.error::setValue);
        compositeDisposable.add(d);
    }

    public MutableLiveData<Boolean> getIsRefreshing() {
        return isRefreshing;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
