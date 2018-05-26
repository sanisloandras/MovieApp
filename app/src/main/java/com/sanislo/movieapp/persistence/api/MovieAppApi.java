package com.sanislo.movieapp.persistence.api;

import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;
import com.sanislo.movieapp.persistence.response.movieList.MovieListResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAppApi {
    @GET("movie/upcoming")
    Single<MovieListResponse> upcoming(@Query("page") int page);

    @GET("movie/upcoming")
    Single<MovieListResponse> upcoming();

    @GET("movie/{movie_id}")
    Single<MovieResponse> movie(@Path("movie_id") int movieId);
}
