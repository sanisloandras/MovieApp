package com.sanislo.movieapp.presentation.movieDetails;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.sanislo.movieapp.domain.SingleLiveEvent;
import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.domain.model.VideoModel;
import com.sanislo.movieapp.domain.model.YoutubeVideoModel;
import com.sanislo.movieapp.domain.movie.MovieRepository;
import com.sanislo.movieapp.domain.video.VideoRepository;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

//TODO make this work without internet
public class MovieDetailsViewModel extends ViewModel {
    public static final String TAG = MovieDetailsViewModel.class.getSimpleName();

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private SingleLiveEvent<Throwable> error = new SingleLiveEvent<>();
    private MovieRepository movieRepository;
    private VideoRepository videoRepository;

    public MovieDetailsViewModel(MovieRepository movieRepository, VideoRepository videoRepository) {
        this.movieRepository = movieRepository;
        this.videoRepository = videoRepository;
    }

    public void loadMovie(int id) {
        Disposable d = movieRepository.movie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(result -> {
                    loadVideos(id);
                })
                .subscribe(result -> {

                }, error -> {
                    Log.d(TAG, "loadMovie: error", error);
                    this.error.setValue(error);
                });
        compositeDisposable.add(d);
    }

    public void loadVideos(int id) {
        Disposable d = videoRepository.videos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    Log.d(TAG, "loadVideos: " + result);
                }, error -> {
                    Log.d(TAG, "loadVideos: error", error);
                    this.error.setValue(error);
                });
        compositeDisposable.add(d);
    }

    public SingleLiveEvent<Throwable> getError() {
        return error;
    }

    public LiveData<MovieModel> getMovieModelLiveData(int id) {
        return movieRepository.movieLiveData(id);
    }

    public LiveData<List<VideoModel>> getVideosForMovieLiveData(int movieId) {
        return videoRepository.videosLiveData(movieId);
    }

    public LiveData<List<YoutubeVideoModel>> getYoutubeVideosForMovie(int movieId) {
        return videoRepository.youtubeVideosLiveData(movieId);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
