package com.sanislo.movieapp.presentation.upcomingMovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;

import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository;
import com.sanislo.movieapp.domain.model.MovieListItemModel;

public class UpcomingMoviesViewModel extends ViewModel {
    private static final String TAG = UpcomingMoviesViewModel.class.getSimpleName();

    private UpcomingMoviesRepository mUpcomingMoviesRepository;
    private final LiveData<PagedList<MovieListItemModel>> movieListItemEntityLiveData;

    public UpcomingMoviesViewModel(UpcomingMoviesRepository upcomingMoviesRepository) {
        this.mUpcomingMoviesRepository = upcomingMoviesRepository;
        movieListItemEntityLiveData = mUpcomingMoviesRepository.getUpcomingLiveData();
    }

    public LiveData<PagedList<MovieListItemModel>> getMovieListItemEntityLiveData() {
        return movieListItemEntityLiveData;
    }
}
