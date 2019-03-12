package com.kishore.apparchitecture.model.network.retrofit.responsepojo

import com.kishore.apparchitecture.Utility.WeatherDateUtils
import com.kishore.apparchitecture.model.database.WeatherTable
import java.util.*


class WeatherData {

    var city: City? = null
    var cod: String? = null
    var message: Double? = null
    var cnt: Int? = null
    var list: java.util.List<com.kishore.apparchitecture.model.network.retrofit.responsepojo.List>? = null

    /**
       Get data  which is only required for UI
     */
    fun toWeatherTableData(): Array<WeatherTable?> {
        var mWeatherForecast: Array<WeatherTable?> = arrayOfNulls<WeatherTable>(this.list!!.size)
        for (i in 0 until this.list!!.size) {
           val data : com.kishore.apparchitecture.model.network.retrofit.responsepojo.List =   this.list!!.get(i)
            val dateTimeMillis = WeatherDateUtils.getNormalizedUtcMsForToday() + WeatherDateUtils.DAY_IN_MILLIS * i
            val pressure = data.pressure
           val humidity = data.humidity
           val windSpeed = data.speed
           val windDirection = data.deg
           //  Temperatures are sent by Open Weather Map in a child object called "temp".
           val max = data.temp!!.max
           val min = data.temp!!.min
           val weatherInfo  : Weather = data.weather!!.get(0)
           val weatherId = weatherInfo.id
            mWeatherForecast[i]= WeatherTable(weatherId!!, Date(dateTimeMillis), max!!, min!!,
                    humidity!!.toInt(), pressure!!, windSpeed!!, windDirection!!.toDouble())
        }
        return mWeatherForecast
    }

}
