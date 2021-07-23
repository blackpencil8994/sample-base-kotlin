package com.oanhltk.sample_base_kotlin.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.oanhltk.sample_base_kotlin.di.ViewModelKey
import com.oanhltk.sample_base_kotlin.di.factory.ViewModelFactory
import com.oanhltk.sample_base_kotlin.ui.main.fragments.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}