package com.sanislo.movieapp.presentation.upcomingMovies

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanislo.movieapp.domain.SingleLiveEvent
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UpcomingMoviesViewModel(private val mUpcomingMoviesRepository: UpcomingMoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val isRefreshing = MutableLiveData<Boolean>()
    val error = SingleLiveEvent<Throwable>()
    val upcomingMoviesLiveData = mUpcomingMoviesRepository.upcomingMoviesLiveData()

    @SuppressLint("CheckResult")
    fun refresh() {
        mUpcomingMoviesRepository.refreshUpcoming()
                .ignoreElement()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { d ->
                    compositeDisposable.add(d)
                    isRefreshing.value = true
                }
                .doFinally {
                    isRefreshing.value = false
                }
                .subscribe({}, {
                    error.value = it
                })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
