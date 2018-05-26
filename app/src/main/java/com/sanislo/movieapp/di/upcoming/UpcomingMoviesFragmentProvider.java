package com.sanislo.movieapp.di.upcoming;

import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class UpcomingMoviesFragmentProvider {

    @ContributesAndroidInjector(modules = UpcomingMoviesFragmentModule.class)
    abstract UpcomingMoviesFragment provideUpcomingMoviesFragment();
}


