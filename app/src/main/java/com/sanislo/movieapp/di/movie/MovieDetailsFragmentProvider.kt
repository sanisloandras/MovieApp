package com.sanislo.movieapp.di.movie

import com.sanislo.movieapp.presentation.movieDetails.MovieDetailsFragment

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MovieDetailsFragmentProvider {
    @ContributesAndroidInjector(modules = [MovieDetailsFragmentModule::class])
    internal abstract fun provideUpcomingMoviesFragment(): MovieDetailsFragment
}
