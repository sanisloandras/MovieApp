package com.sanislo.movieapp.di.upcoming;

import android.arch.lifecycle.ViewModelProvider;

import com.sanislo.movieapp.di.ViewModelProviderFactory;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository;
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepositoryImpl;
import com.sanislo.movieapp.persistence.api.MovieAppApi;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class UpcomingMoviesFragmentModule {
    @Provides
    UpcomingMoviesViewModel provideViewModel(UpcomingMoviesRepository upcomingMoviesRepository) {
        return new UpcomingMoviesViewModel(upcomingMoviesRepository);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelFactory(UpcomingMoviesViewModel upcomingMoviesViewModel) {
        return new ViewModelProviderFactory<>(upcomingMoviesViewModel);
    }

    @Provides
    UpcomingMoviesRepository provideUpcomingMoviesRepository(MovieAppApi movieAppApi,
                                                             UpcomingDao upcomingDao) {
        return new UpcomingMoviesRepositoryImpl(movieAppApi, upcomingDao);
    }
}
