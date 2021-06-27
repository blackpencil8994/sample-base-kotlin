package com.oanhltk.sample_base_kotlin.di.module

import android.app.Application
import androidx.room.Room
import com.oanhltk.sample_base_kotlin.data.local.AppDatabase
import com.oanhltk.sample_base_kotlin.data.local.MovieDao
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

@Module
class DbModule {

    @Provides
    @Singleton
    internal fun provideDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
                application, AppDatabase::class.java, "MyMovieTheater.db")
                .allowMainThreadQueries().build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

}
