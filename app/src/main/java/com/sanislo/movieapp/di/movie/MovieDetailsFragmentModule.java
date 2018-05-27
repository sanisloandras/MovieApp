package com.sanislo.movieapp.di.movie;

import android.arch.lifecycle.ViewModelProvider;

import com.sanislo.movieapp.di.ViewModelProviderFactory;
import com.sanislo.movieapp.domain.movie.MovieRepository;
import com.sanislo.movieapp.domain.movie.MovieRepositoryImpl;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepositoryImpl;
import com.sanislo.movieapp.domain.video.VideoRepository;
import com.sanislo.movieapp.domain.video.VideoRepositoryImpl;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.GenreDao;
import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.dao.MovieJoinDao;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.persistence.dao.VideoDao;
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsViewModel;
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsFragmentModule {
    @Provides
    MovieDetailsViewModel provideViewModel(MovieRepository movieRepository, VideoRepository videoRepository) {
        return new MovieDetailsViewModel(movieRepository, videoRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(MovieDetailsViewModel movieDetailsViewModel) {
        return new ViewModelProviderFactory<>(movieDetailsViewModel);
    }

    @Provides
    MovieRepository provideMovieRepository(MovieAppApi movieAppApi,
                                           MovieDao movieDao,
                                           MovieJoinDao movieJoinDao,
                                           GenreDao genreDao) {
        return new MovieRepositoryImpl(movieAppApi,
                movieDao,
                genreDao,
                movieJoinDao);
    }

    @Provides
    VideoRepository provideVideoRepository(MovieAppApi movieAppApi, VideoDao videoDao) {
        return new VideoRepositoryImpl(movieAppApi, videoDao);
    }
}
