package com.kishore.news.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface NewsDao {


    @Query("SELECT * FROM newsDetail WHERE isHeadline = 0")
    fun getAllNews(): LiveData<List<NewsTable>>

    @Query("SELECT * FROM newsDetail WHERE isHeadline = 1")
    fun getheadlines(): LiveData<List<NewsTable>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(news: List<NewsTable?>)

//LiveData<Optional<NewsTable>>   optional will return empty instead of null
    @Query("SELECT * FROM newsDetail where id=:id")
    fun getNewsDetail(id: Int): NewsTable


    @Query("DELETE FROM newsDetail ")
    fun deleteOldNews()

    @Query("DELETE FROM newsDetail where isHeadline=:breaking")
    fun deleteNews(breaking :Int)


    //@Query("SELECT * FROM newsDetail  WHERE author LIKE :query OR title LIKE :query OR description LIKE :query OR content LIKE :query OR sourceName LIKE :query")
    @Query("SELECT * FROM newsDetail  WHERE title LIKE :query AND isHeadline = 0")
    fun searchNews(query: String): LiveData<List<NewsTable>>


    /**
     * Inserts a list of [NewsTable] into the weather table. If there is a conflicting id
     * or date the weather entry uses the [OnConflictStrategy] of replacing the weather
     * forecast. The required uniqueness of these values is defined in the [NewsTable].
     *
     * @param weather A list of weather forecasts to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg news: NewsTable?)

    /**
     * Deletes any News newsdata older than the given day
     *
     * @param date The date to delete all prior weather from (exclusive)
     */
    @Query("DELETE FROM newsDetail WHERE date < :date")
    fun deleteOldNews(date: Date)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlldata(plants: Array<NewsTable?>)


}