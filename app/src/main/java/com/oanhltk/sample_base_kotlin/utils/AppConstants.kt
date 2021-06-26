package com.oanhltk.sample_base_kotlin.utils

interface AppConstants {
    companion object {

        val PAGE_LIMIT = 10

        val CREDIT_CAST = "cast"
        val CREDIT_CREW = "crew"

        val INTENT_MOVIE = "movie"
        val INTENT_CATEGORY = "category"
        val INTENT_VIDEO_KEY = "intent_video_key"

        val TRANSITION_IMAGE_NAME = "image"

        val TYPE_MOVIES = "movie"
        val TYPE_TVS = "tv"
        val MOVIES_POPULAR = "popular"
        val MOVIES_UPCOMING = "upcoming"
        val MOVIES_TOP_RATED = "top_rated"
        val TV_ON_THE_AIR = "on_the_air"

        val MOVIE_STATUS_RELEASED = "Released"

        val BASE_URL = "https://api.themoviedb.org/3/"
        val IMAGE_URL = "https://image.tmdb.org/t/p/w500%s"

        val TMDB_API_KEY = "5e74ee79280d770dc8ed5a2fbdda955a"
        val YOUTUBE_API_KEY = "AIzaSyCZY8Vnw_6GcJcESL-NilTZDMSvg9ViLt8"

    }
}