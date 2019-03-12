package com.kishore.apparchitecture.model.forview

import java.util.*

class ListWeatherEntry {

    private var id: Int = 0
    private var weatherIconId: Int = 0
    private lateinit var date: Date
    private var min: Double = 0.toDouble()
    private var max: Double = 0.toDouble()

    fun getId() : Int {
        return id
    }

    fun setId( id: Int )  {
         this.id = id
    }


    fun getWeatherIconId() : Int {
        return weatherIconId
    }

    fun setWeatherIconId( weatherIconId: Int )  {
        this.weatherIconId = weatherIconId
    }

    fun getDate() : Date {
        return date
    }

    fun setDate( date: Date)  {
        this.date = date
    }

    fun getMin() : Double {
        return min
    }

    fun setMin( id: Double )  {
        this.min = min
    }


    fun getMax() : Double {
        return max
    }

    fun setMax( max: Double )  {
        this.max = max
    }
}