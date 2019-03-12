package com.kishore.apparchitecture.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kishore.apparchitecture.Utility.DATABASE_NAME
import com.kishore.apparchitecture.model.database.utility.DateConverter

@Database (entities = [WeatherTable::class], version = 1) //All entities should be listed here
@TypeConverters (DateConverter::class)
abstract class WeatherDataBase : RoomDatabase() {

    abstract fun weatherDao(): WeatherDao

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: WeatherDataBase? = null

        fun getInstance(context: Context): WeatherDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): WeatherDataBase {
            return Room.databaseBuilder(context, WeatherDataBase::class.java, DATABASE_NAME).build()
        }
    }
}