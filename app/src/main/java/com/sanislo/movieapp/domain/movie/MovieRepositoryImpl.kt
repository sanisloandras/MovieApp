package com.sanislo.movieapp.domain.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sanislo.movieapp.domain.mapper.*
import com.sanislo.movieapp.domain.model.GenreModel
import com.sanislo.movieapp.domain.model.MovieModel
import com.sanislo.movieapp.persistence.api.MovieAppApi
import com.sanislo.movieapp.persistence.dao.GenreDao
import com.sanislo.movieapp.persistence.dao.MovieDao
import com.sanislo.movieapp.persistence.dao.MovieJoinDao
import com.sanislo.movieapp.persistence.response.movieDetails.MovieResponse
import io.reactivex.Completable
import io.reactivex.Single

class MovieRepositoryImpl(private val movieAppApi: MovieAppApi, private val movieDao: MovieDao, private val genreDao: GenreDao, private val movieJoinDao: MovieJoinDao) : MovieRepository {

    private val movieResponseToMovieEntityMapper = MovieResponseToMovieEntityMapper()
    private val movieResponseToMovieJoinMapper = MovieResponseToMovieJoinMapper()
    private val movieEntityToMovieModelMapper = MovieEntityToMovieModelMapper()
    private val movieResponseToGenreEntityMapper = MovieResponseToGenreEntityMapper()
    private val genreEntityToGenreModelMapper = GenreEntityToGenreModelMapper()

    override fun movie(id: Int): Single<MovieResponse> {
        return movieAppApi.movie(id)
                .doOnSuccess { movieResponse -> movieDao.insert(movieResponseToMovieEntityMapper.map2(movieResponse)) }
                .flatMap { movieResponse ->
                    Completable.fromAction {
                        genreDao.insert(movieResponseToGenreEntityMapper.map(movieResponse))
                        movieJoinDao.insert(movieResponseToMovieJoinMapper.map(movieResponse))
                    }.andThen(Single.just(movieResponse))
                }
    }

    override fun movieGenresLiveData(id: Int): LiveData<List<GenreModel>> {
        return Transformations.map(movieJoinDao.getGenresForMovie(id)) {
            genreEntityToGenreModelMapper.map2(it)
        }
    }

    override fun movieLiveData(id: Int): LiveData<MovieModel> {
        return Transformations.map(movieDao.movie(id)) {
            movieEntityToMovieModelMapper.map(it)
        }
    }

    companion object {
        val TAG = MovieRepository::class.java.simpleName
    }
}
