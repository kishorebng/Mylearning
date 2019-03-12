package com.kishore.apparchitecture.model.database

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Insert
import androidx.room.Query
import com.kishore.apparchitecture.model.forview.ListWeatherEntry
import java.util.*


@Dao
interface WeatherDao {

    /**
     * Gets the weather for a single day
     *
     * @param date The date you want weather for
     * @return [LiveData] with weather for a single day
     */
    @Query("SELECT * FROM weather WHERE date = :date")
    fun getWeatherByDate(date: Date): LiveData<WeatherTable>

    /**
     * Selects all ids entries after a give date, inclusive. This is for easily seeing
     * what entries are in the database without pulling all of the data.
     *
     * @param date The date to select after (inclusive)
     * @return Number of future weather forecasts stored in the database
     */
    @Query("SELECT COUNT(id) FROM weather WHERE date >= :date")
    fun countAllFutureWeather(date: Date): Int


    /**
     * Inserts a list of [WeatherEntry] into the weather table. If there is a conflicting id
     * or date the weather entry uses the [OnConflictStrategy] of replacing the weather
     * forecast. The required uniqueness of these values is defined in the [WeatherEntry].
     *
     * @param weather A list of weather forecasts to insert
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun bulkInsert(vararg weather: WeatherTable?)

    /**
     * Deletes any weather data older than the given day
     *
     * @param date The date to delete all prior weather from (exclusive)
     */
    @Query("DELETE FROM weather WHERE date < :date")
    fun deleteOldWeather(date: Date)

    /**
     * Selects all [ListWeatherEntry] entries after a give date, inclusive. The LiveData will
     * be kept in sync with the database, so that it will automatically notify observers when the
     * values in the table change.
     *
     * @param date A [Date] from which to select all future weather
     * @return [LiveData] list of all [ListWeatherEntry] objects after date*/

    @Query("SELECT id, weatherIconId, date, min, max FROM weather WHERE date >= :date")
    fun getCurrentWeatherForecasts(date: Date): LiveData<List<ListWeatherEntry>>


}