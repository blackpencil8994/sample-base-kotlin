package com.oanhltk.sample_base_kotlin.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.oanhltk.sample_base_kotlin.AppController
import com.oanhltk.sample_base_kotlin.R
import com.oanhltk.sample_base_kotlin.di.factory.ViewModelFactory
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        (applicationContext as AppController).getAppComponent()?.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun getDefaultViewModelProviderFactory(): ViewModelFactory {
        return viewModelFactory
    }
}