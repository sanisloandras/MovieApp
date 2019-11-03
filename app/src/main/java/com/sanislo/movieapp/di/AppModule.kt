package com.sanislo.movieapp.di

import android.app.Application
import android.content.Context

import dagger.Module
import dagger.Provides

@Module(includes = [NetworkModule::class, DatabaseModule::class])
class AppModule {

    @Provides
    internal fun provideApplicationContext(application: Application): Context {
        return application.applicationContext
    }
}


