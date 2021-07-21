package com.oanhltk.sample_base_kotlin.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.oanhltk.sample_base_kotlin.data.entity.Cast

class CastListTypeConverter {

    @TypeConverter
    fun fromString(value: String): List<Cast>? {
        val listType = object : TypeToken<List<Cast>>() {}.type
        return Gson().fromJson<List<Cast>>(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Cast>?): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
