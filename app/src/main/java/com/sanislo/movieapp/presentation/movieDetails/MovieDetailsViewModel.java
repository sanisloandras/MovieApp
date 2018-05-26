package com.sanislo.movieapp.presentation.movieDetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.domain.movie.MovieRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieDetailsViewModel extends ViewModel {
    public static final String TAG = MovieDetailsViewModel.class.getSimpleName();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MovieRepository movieRepository;

    public MovieDetailsViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void loadMovie(int id) {
        Disposable d = movieRepository.movie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
        compositeDisposable.add(d);
    }

    public LiveData<MovieModel> getMovieModelLiveData(int id) {
        return movieRepository.movieLiveData(id);
    }
}
