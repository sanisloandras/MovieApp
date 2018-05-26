package com.sanislo.movieapp.di;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

@Module(includes = {NetworkModule.class, DatabaseModule.class})
public class AppModule {

    @Provides
    Context provideApplicationContext(Application application) {
        return application.getApplicationContext();
    }
}


