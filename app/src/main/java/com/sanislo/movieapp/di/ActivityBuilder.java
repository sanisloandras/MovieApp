package com.sanislo.movieapp.di;

import com.sanislo.movieapp.di.main.MainActivityModule;
import com.sanislo.movieapp.di.upcoming.UpcomingMoviesFragmentProvider;
import com.sanislo.movieapp.presentation.MainActivity;
import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            UpcomingMoviesFragmentProvider.class,
            MovieDetailsFragment.class})
    abstract MainActivity bindMainActivity();
}


