package com.oanhltk.sample_base_kotlin.di.module

import com.oanhltk.sample_base_kotlin.di.repository.MovieRepository
import com.oanhltk.sample_base_kotlin.di.repository.MovieRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}