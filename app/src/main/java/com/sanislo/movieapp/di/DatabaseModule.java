package com.sanislo.movieapp.di;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.sanislo.movieapp.persistence.dao.MovieDao;
import com.sanislo.movieapp.persistence.dao.MovieJoinDao;
import com.sanislo.movieapp.persistence.dao.UpcomingDao;
import com.sanislo.movieapp.persistence.db.MovieAppDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {
    @Provides
    @Singleton
    MovieAppDatabase providesDatabase(Context context, @DatabaseInfo String databaseName) {
        RoomDatabase.Builder<MovieAppDatabase> builder =
                Room.databaseBuilder(context, MovieAppDatabase.class, databaseName);
        return builder.build();
    }

    @Provides
    @DatabaseInfo
    String providesDatabaseName() {
        return "GettyImages.db";
    }

    @Provides
    UpcomingDao providesUpcomingDao(MovieAppDatabase database) {
        return database.upcomingDao();
    }

    @Provides
    MovieDao providesMovieDao(MovieAppDatabase database) {
        return database.movieDao();
    }

    @Provides
    MovieJoinDao providesMovieJoinDao(MovieAppDatabase database) {
        return database.movieJoinDao();
    }
}

