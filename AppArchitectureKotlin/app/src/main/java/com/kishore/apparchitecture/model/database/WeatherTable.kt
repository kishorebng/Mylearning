package com.kishore.apparchitecture.model.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "weather", indices = arrayOf(Index(value = ["date"], unique = true)))
data class WeatherTable(
        var weatherIconId: Int = 0,
        var date: Date? = null,
        var min: Double = 0.toDouble(),
        var max: Double = 0.toDouble(),
        var humidity: Int = 0,
        var pressure: Double = 0.toDouble(),
        var wind: Double = 0.toDouble(),
        var degrees: Double = 0.toDouble()
) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    //Getter setter needed if variables are private
    //Since we are using val and initialized value constructor is not required


}


