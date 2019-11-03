package com.sanislo.movieapp.presentation.movieDetails

import android.annotation.SuppressLint
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.sanislo.movieapp.domain.SingleLiveEvent
import com.sanislo.movieapp.domain.model.MovieModel
import com.sanislo.movieapp.domain.model.YoutubeVideoModel
import com.sanislo.movieapp.domain.movie.MovieRepository
import com.sanislo.movieapp.domain.video.VideoRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MovieDetailsViewModel(private val movieRepository: MovieRepository, private val videoRepository: VideoRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    private val movieIdSubject = BehaviorSubject.create<Int>()
    private val movieIdLiveData = LiveDataReactiveStreams.fromPublisher(movieIdSubject.toFlowable(BackpressureStrategy.LATEST))
    val movieDetails: LiveData<MovieModel> = Transformations.switchMap(movieIdLiveData) {
        movieRepository.movieLiveData(it)
    }
    val youtubeVideos: LiveData<List<YoutubeVideoModel>> = Transformations.switchMap(movieIdLiveData) {
        videoRepository.youtubeVideosLiveData(it)
    }
    val error = SingleLiveEvent<Throwable>()

    init {
        observeMovieId()
    }

    @SuppressLint("CheckResult")
    private fun observeMovieId() {
        movieIdSubject.switchMapCompletable { movieId ->
            movieRepository.movie(movieId)
                    .ignoreElement()
                    .andThen(videoRepository.videos(movieId).ignoreElement())
                    .subscribeOn(Schedulers.io())
        }
                .doOnSubscribe {
                    compositeDisposable.add(it)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    error.value = it
                })
    }

    fun setMovieId(movieId: Int) = movieIdSubject.onNext(movieId)

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        val TAG = MovieDetailsViewModel::class.java.simpleName
    }
}
