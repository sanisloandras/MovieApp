package com.sanislo.movieapp.di.movie;

import android.arch.lifecycle.ViewModelProvider;

import com.sanislo.movieapp.di.ViewModelProviderFactory;
import com.sanislo.movieapp.domain.movie.MovieRepository;
import com.sanislo.movieapp.domain.movie.MovieRepositoryImpl;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepositoryImpl;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsViewModel;
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class MovieDetailsFragmentModule {
    @Provides
    MovieDetailsViewModel provideViewModel(MovieRepository movieRepository) {
        return new MovieDetailsViewModel(movieRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(MovieDetailsViewModel movieDetailsViewModel) {
        return new ViewModelProviderFactory<>(movieDetailsViewModel);
    }

    @Provides
    MovieRepository provideMovieRepository(MovieAppApi movieAppApi,
                                           MovieDao movieDao) {
        return new MovieRepositoryImpl(movieAppApi, movieDao);
    }
}
