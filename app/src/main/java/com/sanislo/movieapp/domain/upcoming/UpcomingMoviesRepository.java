package com.sanislo.movieapp.domain.upcoming;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse;

import io.reactivex.Single;

public interface UpcomingMoviesRepository {
    Single<MovieListResponse> getUpcoming(int page);
    Single<MovieListResponse> getUpcoming();
    void saveUpcomingMovies(MovieListResponse movieListResponse);
    LiveData<PagedList<MovieListItemModel>> getUpcomingLiveData();
}
