package com.sanislo.movieapp.presentation.movieDetails

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.sanislo.movieapp.domain.SingleLiveEvent
import com.sanislo.movieapp.domain.model.MovieModel
import com.sanislo.movieapp.domain.model.YoutubeVideoModel
import com.sanislo.movieapp.domain.movie.MovieRepository
import com.sanislo.movieapp.domain.video.VideoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//TODO make this work without internet
class MovieDetailsViewModel(private val movieRepository: MovieRepository, private val videoRepository: VideoRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val error = SingleLiveEvent<Throwable>()

    fun loadMovie(id: Int) {
        val d = movieRepository.movie(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess { result -> loadVideos(id) }
                .subscribe({ result ->

                }, { error ->
                    Log.d(TAG, "loadMovie: error", error)
                    this.error.setValue(error)
                })
        compositeDisposable.add(d)
    }

    private fun loadVideos(id: Int) {
        val d = videoRepository.videos(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> Log.d(TAG, "loadVideos: $result") }, { error ->
                    this.error.setValue(error)
                })
        compositeDisposable.add(d)
    }

    fun getMovieModelLiveData(id: Int): LiveData<MovieModel> {
        return movieRepository.movieLiveData(id)
    }

    fun getYoutubeVideosForMovie(movieId: Int): LiveData<List<YoutubeVideoModel>> {
        return videoRepository.youtubeVideosLiveData(movieId)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    companion object {
        val TAG = MovieDetailsViewModel::class.java.simpleName
    }
}
