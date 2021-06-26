package com.oanhltk.sample_base_kotlin.di.module

import android.app.Application
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.oanhltk.sample_base_kotlin.api.MovieService
import com.oanhltk.sample_base_kotlin.utils.AppConstants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class ApiModule {

    private val requestInterceptor: Interceptor
        get() = object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val originalRequest = chain.request()
                val originalUrl = originalRequest.url()
                val url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", AppConstants.TMDB_API_KEY)
                    .build()

                val requestBuilder = originalRequest.newBuilder().url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }


    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Provides
    @Singleton
    internal fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideCache(application: Application): Cache {
        val cacheSize = (10 * 1024 * 1024).toLong() // 10 MB
        val httpCacheDirectory = File(application.cacheDir, "http-cache")
        return Cache(httpCacheDirectory, cacheSize)
    }


    @Provides
    @Singleton
    internal fun provideOkhttpClient(cache: Cache): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()

        httpClient.apply {
            cache(cache)
            addInterceptor(logging)
            addNetworkInterceptor(requestInterceptor)
            addNetworkInterceptor(StethoInterceptor())
            connectTimeout(30, TimeUnit.SECONDS)
            readTimeout(30, TimeUnit.SECONDS)
        }

        return httpClient.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(AppConstants.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieApiService(retrofit: Retrofit): MovieService {
        return retrofit.create(MovieService::class.java)
    }
}