package com.sanislo.movieapp.di.movie

import androidx.lifecycle.ViewModelProvider
import com.sanislo.movieapp.di.ViewModelProviderFactory
import com.sanislo.movieapp.domain.movie.MovieRepository
import com.sanislo.movieapp.domain.movie.MovieRepositoryImpl
import com.sanislo.movieapp.domain.video.VideoRepository
import com.sanislo.movieapp.domain.video.VideoRepositoryImpl
import com.sanislo.movieapp.persistence.api.MovieAppApi
import com.sanislo.movieapp.persistence.dao.GenreDao
import com.sanislo.movieapp.persistence.dao.MovieDao
import com.sanislo.movieapp.persistence.dao.MovieJoinDao
import com.sanislo.movieapp.persistence.dao.VideoDao
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsViewModel
import dagger.Module
import dagger.Provides

@Module
class MovieDetailsFragmentModule {
    @Provides
    internal fun provideViewModel(movieRepository: MovieRepository, videoRepository: VideoRepository): MovieDetailsViewModel {
        return MovieDetailsViewModel(movieRepository, videoRepository)
    }

    @Provides
    internal fun provideViewModelFactory(movieDetailsViewModel: MovieDetailsViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(movieDetailsViewModel)
    }

    @Provides
    internal fun provideMovieRepository(movieAppApi: MovieAppApi,
                                        movieDao: MovieDao,
                                        movieJoinDao: MovieJoinDao,
                                        genreDao: GenreDao): MovieRepository {
        return MovieRepositoryImpl(movieAppApi,
                movieDao,
                genreDao,
                movieJoinDao)
    }

    @Provides
    internal fun provideVideoRepository(movieAppApi: MovieAppApi, videoDao: VideoDao): VideoRepository {
        return VideoRepositoryImpl(movieAppApi, videoDao)
    }
}
