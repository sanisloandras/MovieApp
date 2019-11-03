package com.sanislo.movieapp.di

import android.arch.persistence.room.Room
import android.content.Context
import com.sanislo.movieapp.persistence.dao.*
import com.sanislo.movieapp.persistence.db.MovieAppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Provides
    @Singleton
    internal fun providesDatabase(context: Context, @DatabaseInfo databaseName: String): MovieAppDatabase {
        val builder = Room.databaseBuilder(context, MovieAppDatabase::class.java, databaseName)
        return builder.build()
    }

    @Provides
    @DatabaseInfo
    internal fun providesDatabaseName(): String {
        return "Movies.db"
    }

    @Provides
    internal fun providesUpcomingDao(database: MovieAppDatabase): UpcomingDao {
        return database.upcomingDao()
    }

    @Provides
    internal fun providesMovieDao(database: MovieAppDatabase): MovieDao {
        return database.movieDao()
    }

    @Provides
    internal fun providesMovieJoinDao(database: MovieAppDatabase): MovieJoinDao {
        return database.movieJoinDao()
    }

    @Provides
    internal fun providesVideoDao(database: MovieAppDatabase): VideoDao {
        return database.videoDao()
    }

    @Provides
    internal fun providesGenreDao(database: MovieAppDatabase): GenreDao {
        return database.genreDao()
    }
}

