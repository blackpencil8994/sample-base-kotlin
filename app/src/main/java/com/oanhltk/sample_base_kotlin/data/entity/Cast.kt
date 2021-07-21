package com.oanhltk.sample_base_kotlin.data.entity

import android.util.Log
import androidx.room.Entity
import com.oanhltk.sample_base_kotlin.utils.AppConstants
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(primaryKeys = ["id"])
@JsonClass(generateAdapter = true)
data class Cast(

    @Json(name = "adult")
    val adult: Boolean?,

    @Json(name = "profile_path")
    var profilePath: String?,

    @Json(name = "id")
    val id: Int,

    @Json(name = "original_name")
    val originalName: String?,

    @Json(name = "name")
    val name: String?,

    @Json(name = "character")
    val character: String?
) {
    fun getFormattedProfilePath(): String? {
        if (profilePath != null && !profilePath!!.startsWith("http")) {
            profilePath = String.format(AppConstants.IMAGE_URL, profilePath)
        }
        profilePath?.let { Log.d("getFormattedPosterPath", it) }
        return profilePath
    }
}


