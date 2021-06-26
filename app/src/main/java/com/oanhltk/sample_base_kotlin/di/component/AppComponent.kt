package com.oanhltk.sample_base_kotlin.di.component

import android.app.Application
import com.oanhltk.sample_base_kotlin.di.module.ApiModule
import com.oanhltk.sample_base_kotlin.di.module.RepositoryModule
import com.oanhltk.sample_base_kotlin.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
        ApiModule::class, RepositoryModule::class
])
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }


    fun inject(mainActivity: MainActivity)

}