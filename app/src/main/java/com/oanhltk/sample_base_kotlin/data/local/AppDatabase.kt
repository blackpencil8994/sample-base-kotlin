package com.oanhltk.sample_base_kotlin.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oanhltk.sample_base_kotlin.data.entity.Movie


@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
