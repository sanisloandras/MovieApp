package com.sanislo.movieapp.di

import com.sanislo.movieapp.di.main.MainActivityModule
import com.sanislo.movieapp.di.movie.MovieDetailsFragmentProvider
import com.sanislo.movieapp.di.upcoming.UpcomingMoviesFragmentProvider
import com.sanislo.movieapp.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [MainActivityModule::class, UpcomingMoviesFragmentProvider::class, MovieDetailsFragmentProvider::class])
    internal abstract fun bindMainActivity(): MainActivity
}


