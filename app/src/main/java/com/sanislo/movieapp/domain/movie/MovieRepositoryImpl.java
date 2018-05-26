package com.sanislo.movieapp.domain.movie;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;

import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;

import io.reactivex.Single;

public class MovieRepositoryImpl implements MovieRepository {
    public static final String TAG = MovieRepository.class.getSimpleName();

    private MovieAppApi movieAppApi;
    private MovieDao movieDao;

    public MovieRepositoryImpl(MovieAppApi movieAppApi, MovieDao movieDao) {
        this.movieAppApi = movieAppApi;
        this.movieDao = movieDao;
    }

    @Override
    public Single<MovieResponse> movie(int id) {
        return movieAppApi.movie(id)
                .doOnSuccess(movieResponse -> {
                    //movieDao.insert();
                });
    }

    @Override
    public LiveData<MovieModel> movieLiveData(int id) {
        return Transformations.map(movieDao.movie(id), new Function<MovieEntity, MovieModel>() {
            @Override
            public MovieModel apply(MovieEntity input) {
                return null;
            }
        });
    }
}
