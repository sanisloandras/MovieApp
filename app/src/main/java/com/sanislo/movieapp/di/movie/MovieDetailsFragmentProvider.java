package com.sanislo.movieapp.di.movie;

import com.sanislo.movieapp.di.upcoming.UpcomingMoviesFragmentModule;
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment;
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = MovieDetailsFragment.class)
    abstract MovieDetailsFragment provideUpcomingMoviesFragment();
}
