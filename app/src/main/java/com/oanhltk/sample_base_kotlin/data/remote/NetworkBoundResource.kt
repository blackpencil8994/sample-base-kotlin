package com.oanhltk.sample_base_kotlin.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<RequestType> @MainThread
protected constructor() {

    private val asObservable: Observable<Resource<RequestType>>

    init {
            asObservable = createCall()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAsObservable(): Observable<Resource<RequestType>> {
        return asObservable
    }


    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract fun createCall(): Observable<Resource<RequestType>>
}