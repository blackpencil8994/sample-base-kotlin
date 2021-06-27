package com.oanhltk.sample_base_kotlin

import android.app.Application
import com.facebook.stetho.Stetho
import com.oanhltk.sample_base_kotlin.di.component.AppComponent
import com.oanhltk.sample_base_kotlin.di.component.DaggerAppComponent


class AppController : Application() {

    private var mAppComponent: AppComponent? = null


    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.builder()
            .application(this)
            .bindContext(this)
            .build()

        initStetho()
    }

    fun getAppComponent(): AppComponent? {
        return mAppComponent
    }

    private fun initStetho() {
        Stetho.initializeWithDefaults(this)
    }
}