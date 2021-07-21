package com.oanhltk.sample_base_kotlin.data.remote

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class NetworkBoundResource<ResultType, RequestType> @MainThread
protected constructor() {

    private val asObservable: Observable<Resource<ResultType>>

    init {
        val source: Observable<Resource<ResultType>>

        if (shouldFetch()) {

            source = createCall()
                    .subscribeOn(Schedulers.io())
                    .doOnNext {
                        saveCallResult(processResponse(it)!!)
                    }
                    .flatMap {
                        loadFromDb()
                                .toObservable()
                                .map { Resource.success(it) }
                    }
                    .onErrorResumeNext { t: Throwable ->
                        loadFromDb()
                                .toObservable()
                                .map { Resource.success(it) }
                    }
                    .observeOn(AndroidSchedulers.mainThread())

        } else {
            source = loadFromDb()
                    .toObservable()
                    .map { Resource.success(it) }
        }

        asObservable = source
    }

    fun getAsObservable(): Observable<Resource<ResultType>> {
        return asObservable
    }

    @WorkerThread
    protected fun processResponse(response: Resource<RequestType>): RequestType? {
        return response.data
    }

    @WorkerThread
    protected abstract fun saveCallResult(item: RequestType)

    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract fun loadFromDb(): Flowable<ResultType>

    @MainThread
    protected abstract fun createCall(): Observable<Resource<RequestType>>
}