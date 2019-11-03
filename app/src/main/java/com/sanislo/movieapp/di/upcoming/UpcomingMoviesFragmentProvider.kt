package com.sanislo.movieapp.di.upcoming

import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UpcomingMoviesFragmentProvider {

    @ContributesAndroidInjector(modules = [UpcomingMoviesFragmentModule::class])
    internal abstract fun provideUpcomingMoviesFragment(): UpcomingMoviesFragment
}


