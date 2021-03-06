package com.oanhltk.sample_base_kotlin.di.component

import android.app.Application
import android.content.Context
import com.oanhltk.sample_base_kotlin.di.module.ApiModule
import com.oanhltk.sample_base_kotlin.di.module.DbModule
import com.oanhltk.sample_base_kotlin.di.module.RepositoryModule
import com.oanhltk.sample_base_kotlin.di.module.ViewModelModule
import com.oanhltk.sample_base_kotlin.ui.main.MainActivity
import com.oanhltk.sample_base_kotlin.ui.main.fragments.detail.DetailMovieFragment
import com.oanhltk.sample_base_kotlin.ui.main.fragments.favorite.FavoriteFragment
import com.oanhltk.sample_base_kotlin.ui.main.fragments.home.HomeFragment
import com.oanhltk.sample_base_kotlin.ui.main.fragments.movie.MoviesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ApiModule::class,
        DbModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        fun bindContext(context: Context): Builder

        fun build(): AppComponent
    }


    fun inject(mainActivity: MainActivity)

    fun inject(moviesFragment: MoviesFragment)

    fun inject(detailMovieFragment: DetailMovieFragment)

    fun inject(favoriteFragment: FavoriteFragment)

}