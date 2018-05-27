package com.sanislo.movieapp.di.movie;

import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MovieDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = MovieDetailsFragmentModule.class)
    abstract MovieDetailsFragment provideUpcomingMoviesFragment();
}
