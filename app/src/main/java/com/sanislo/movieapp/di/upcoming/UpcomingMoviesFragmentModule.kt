package com.sanislo.movieapp.di.upcoming

import android.arch.lifecycle.ViewModelProvider

import com.sanislo.movieapp.di.ViewModelProviderFactory
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepository
import com.sanislo.movieapp.domain.upcoming.UpcomingMoviesRepositoryImpl
import com.sanislo.movieapp.persistence.api.MovieAppApi
import com.sanislo.movieapp.persistence.dao.UpcomingDao
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesViewModel

import dagger.Module
import dagger.Provides

@Module
class UpcomingMoviesFragmentModule {
    @Provides
    internal fun provideViewModel(upcomingMoviesRepository: UpcomingMoviesRepository): UpcomingMoviesViewModel {
        return UpcomingMoviesViewModel(upcomingMoviesRepository)
    }

    @Provides
    internal fun provideViewModelFactory(upcomingMoviesViewModel: UpcomingMoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(upcomingMoviesViewModel)
    }

    @Provides
    internal fun provideUpcomingMoviesRepository(movieAppApi: MovieAppApi,
                                                 upcomingDao: UpcomingDao): UpcomingMoviesRepository {
        return UpcomingMoviesRepositoryImpl(movieAppApi, upcomingDao)
    }
}
