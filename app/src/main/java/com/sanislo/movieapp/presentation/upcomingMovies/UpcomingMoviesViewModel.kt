package com.sanislo.movieapp.presentation.upcomingMovies

import android.annotation.SuppressLint
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.sanislo.movieapp.domain.SingleLiveEvent
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//TODO improve no internet handling
class UpcomingMoviesViewModel(private val mUpcomingMoviesRepository: UpcomingMoviesRepository) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val isRefreshing = MutableLiveData<Boolean>()
    //TODO use this
    private val error = SingleLiveEvent<Throwable>()
    val upcomingMoviesLiveData = mUpcomingMoviesRepository.upcomingMoviesLiveData()

    @SuppressLint("CheckResult")
    fun refresh() {
        mUpcomingMoviesRepository.refreshUpcoming()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { d ->
                    compositeDisposable.add(d)
                    isRefreshing.setValue(true)
                }
                .subscribe({ _ -> isRefreshing.setValue(false) }, { this.error.setValue(it) })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
