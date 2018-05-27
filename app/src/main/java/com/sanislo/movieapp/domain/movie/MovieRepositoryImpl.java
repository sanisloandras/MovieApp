package com.sanislo.movieapp.domain.movie;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.util.Log;

import com.sanislo.movieapp.domain.mapper.GenreEntityToGenreModelMapper;
import com.sanislo.movieapp.domain.mapper.MovieEntityToMovieModelMapper;
import com.sanislo.movieapp.domain.mapper.MovieResponseToGenreEntityMapper;
import com.sanislo.movieapp.domain.mapper.MovieResponseToMovieEntityMapper;
import com.sanislo.movieapp.domain.mapper.MovieResponseToMovieJoinMapper;
import com.sanislo.movieapp.domain.model.GenreModel;
import com.sanislo.movieapp.domain.model.MovieModel;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.GenreDao;
import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.dao.MovieJoinDao;
import com.sanislo.movieapp.persistence.entity.GenreEntity;
import com.sanislo.movieapp.persistence.entity.MovieEntity;
import com.sanislo.movieapp.persistence.entity.MovieJoin;
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleSource;

public class MovieRepositoryImpl implements MovieRepository {
    public static final String TAG = MovieRepository.class.getSimpleName();

    private MovieResponseToMovieEntityMapper movieResponseToMovieEntityMapper
            = new MovieResponseToMovieEntityMapper();
    private MovieResponseToMovieJoinMapper movieResponseToMovieJoinMapper
            = new MovieResponseToMovieJoinMapper();
    private MovieEntityToMovieModelMapper movieEntityToMovieModelMapper
            = new MovieEntityToMovieModelMapper();
    private MovieResponseToGenreEntityMapper movieResponseToGenreEntityMapper
            = new MovieResponseToGenreEntityMapper();
    private GenreEntityToGenreModelMapper genreEntityToGenreModelMapper
            = new GenreEntityToGenreModelMapper();

    private MovieAppApi movieAppApi;
    private MovieDao movieDao;
    private GenreDao genreDao;
    private MovieJoinDao movieJoinDao;

    public MovieRepositoryImpl(MovieAppApi movieAppApi, MovieDao movieDao, GenreDao genreDao, MovieJoinDao movieJoinDao) {
        this.movieAppApi = movieAppApi;
        this.movieDao = movieDao;
        this.genreDao = genreDao;
        this.movieJoinDao = movieJoinDao;
    }

    @Override
    public Single<MovieResponse> movie(int id) {
        return movieAppApi.movie(id)
                .doOnSuccess(movieResponse -> {
                    Log.d(TAG, "movie: " + movieResponse);
                    movieDao.insert(movieResponseToMovieEntityMapper.map2(movieResponse));
                })
                .flatMap(movieResponse -> {
                    return Completable.fromAction(() -> {
                        Log.d(TAG, "movie: " + movieResponseToMovieJoinMapper.map(movieResponse));
                        genreDao.insert(movieResponseToGenreEntityMapper.map(movieResponse));
                        movieJoinDao.insert(movieResponseToMovieJoinMapper.map(movieResponse));
                    }).andThen(Single.just(movieResponse));
                });
    }

    @Override
    public LiveData<List<GenreModel>> movieGenresLiveData(int id) {
        return Transformations.map(movieJoinDao.getGenresForMovie(id), input -> {
            return genreEntityToGenreModelMapper.map2(input);
        });
    }

    @Override
    public LiveData<MovieModel> movieLiveData(int id) {
        return Transformations.map(movieDao.movie(id), input -> {
            return movieEntityToMovieModelMapper.map(input);
        });
    }
}
