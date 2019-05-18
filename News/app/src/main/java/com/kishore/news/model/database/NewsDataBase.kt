package com.kishore.news.model.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.kishore.news.model.database.utility.DateConverter

@Database (entities = [NewsTable::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: NewsDataBase? = null

        fun getInstance(context: Context): NewsDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): NewsDataBase {
            return Room.databaseBuilder(context, NewsDataBase::class.java, "newsDetail-db")
                    .allowMainThreadQueries()
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Log.i("Kishore","Created database")
                        }
                    })
                    .build()
        }
    }

}
