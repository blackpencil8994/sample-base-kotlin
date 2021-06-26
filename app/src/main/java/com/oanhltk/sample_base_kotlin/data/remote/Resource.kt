package com.oanhltk.sample_base_kotlin.data.remote


import com.oanhltk.sample_base_kotlin.data.remote.Status.ERROR
import com.oanhltk.sample_base_kotlin.data.remote.Status.LOADING
import com.oanhltk.sample_base_kotlin.data.remote.Status.SUCCESS


class Resource<T> private constructor(val status: Status, val data: T?, val message: String?) {

    val isSuccess: Boolean
        get() = status === Status.SUCCESS && data != null

    val isLoading: Boolean
        get() = status === Status.LOADING

    val isLoaded: Boolean
        get() = status !== Status.LOADING

    companion object {

        fun <T> success(data: T): Resource<T> {
            return Resource(SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(LOADING, data, null)
        }
    }
}