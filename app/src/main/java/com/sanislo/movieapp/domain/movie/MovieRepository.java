package com.sanislo.movieapp.domain.movie;

import android.arch.lifecycle.LiveData;

import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;

import io.reactivex.Single;

public interface MovieRepository {
    Single<MovieResponse> movie(int id);
    LiveData<MovieModel> movieLiveData(int id);
}
