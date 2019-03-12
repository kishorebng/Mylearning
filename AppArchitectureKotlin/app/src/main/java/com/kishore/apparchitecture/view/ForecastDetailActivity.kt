package com.kishore.apparchitecture.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kishore.apparchitecture.InjectorUtils
import com.kishore.apparchitecture.Utility.WeatherDateUtils
import com.kishore.apparchitecture.Utility.WeatherUtils
import com.kishore.apparchitecture.model.database.WeatherTable
import com.kishore.apparchitecture.viewmodel.ForecastDetailViewModel
import com.kishore.apparchitecture.databinding.WeatherDetailBinding
import com.kishore.apparchitecture.R
import java.util.Date

class ForecastDetailActivity : AppCompatActivity() {

    private lateinit var mWeatherDetailBinding: WeatherDetailBinding
    private lateinit var mForecastDetailViewModel: ForecastDetailViewModel

    companion object {
        val WEATHER_ID_EXTRA = "WEATHER_ID_EXTRA"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mWeatherDetailBinding = DataBindingUtil.setContentView(this,
                R.layout.weather_detail)

        val timestamp = intent.getLongExtra(WEATHER_ID_EXTRA, -1)
        val date = Date(timestamp)
        // Get the ViewModel from the factory
        val factory = InjectorUtils.provideForecastDetailViewModelFactory(this.applicationContext, date)
        mForecastDetailViewModel = ViewModelProviders.of(this, factory).get(ForecastDetailViewModel::class.java)
        mForecastDetailViewModel.weatherData.observe(this, object : Observer<WeatherTable> {
            override fun onChanged(t: WeatherTable?) {
                //To change body of created functions use File | Settings | File Templates.
                bindWeatherToUI(t!!)
            }
        })
    }


    private fun bindWeatherToUI(weatherData: WeatherTable) {
        /****************
         * Weather Icon *
         */

        val weatherId = weatherData.weatherIconId
        val weatherImageId = WeatherUtils.getLargeArtResourceIdForWeatherCondition(weatherId)

        /* Set the resource ID on the icon to display the art */
        mWeatherDetailBinding.primaryInfo.weatherIcon.setImageResource(weatherImageId)

        /****************
         * Weather Date *
         */
        /*
         * The date that is stored is a GMT representation at midnight of the date when the weather
         * information was loaded for.
         *
         * When displaying this date, one must add the GMT offset (in milliseconds) to acquire
         * the date representation for the local date in local time.
         * WeatherDateUtils#getFriendlyDateString takes care of this for us.
         */
        val localDateMidnightGmt = weatherData.date!!.time
        val dateText = WeatherDateUtils.getFriendlyDateString(this, localDateMidnightGmt, true)
        mWeatherDetailBinding.primaryInfo.date.setText(dateText)

        /***********************
         * Weather Description *
         */
        /* Use the weatherId to obtain the proper description */
        val description = WeatherUtils.getStringForWeatherCondition(this, weatherId)

        /* Create the accessibility (a11y) String from the weather description */
        val descriptionA11y = getString(R.string.a11y_forecast, description)

        /* Set the text and content description (for accessibility purposes) */
        mWeatherDetailBinding.primaryInfo.weatherDescription.setText(description)
        mWeatherDetailBinding.primaryInfo.weatherDescription.setContentDescription(descriptionA11y)

        /* Set the content description on the weather image (for accessibility purposes) */
        mWeatherDetailBinding.primaryInfo.weatherIcon.setContentDescription(descriptionA11y)

        /**************************
         * High (max) temperature *
         */

        val maxInCelsius = weatherData.max

        /*
         * If the user's preference for weather is fahrenheit, formatTemperature will convert
         * the temperature. This method will also append either °C or °F to the temperature
         * String.
         */
        val highString = WeatherUtils.formatTemperature(this, maxInCelsius)

        /* Create the accessibility (a11y) String from the weather description */
        val highA11y = getString(R.string.a11y_high_temp, highString)

        /* Set the text and content description (for accessibility purposes) */
        mWeatherDetailBinding.primaryInfo.highTemperature.setText(highString)
        mWeatherDetailBinding.primaryInfo.highTemperature.setContentDescription(highA11y)

        /*************************
         * Low (min) temperature *
         */

        val minInCelsius = weatherData.min
        /*
         * If the user's preference for weather is fahrenheit, formatTemperature will convert
         * the temperature. This method will also append either °C or °F to the temperature
         * String.
         */
        val lowString = WeatherUtils.formatTemperature(this, minInCelsius)

        val lowA11y = getString(R.string.a11y_low_temp, lowString)

        /* Set the text and content description (for accessibility purposes) */
        mWeatherDetailBinding.primaryInfo.lowTemperature.setText(lowString)
        mWeatherDetailBinding.primaryInfo.lowTemperature.setContentDescription(lowA11y)

        /************
         * Humidity *
         */

        val humidity = weatherData.humidity
        val humidityString = getString(R.string.format_humidity, humidity)
        val humidityA11y = getString(R.string.a11y_humidity, humidityString)

        /* Set the text and content description (for accessibility purposes) */
        mWeatherDetailBinding.extraDetails.humidity.setText(humidityString)
        mWeatherDetailBinding.extraDetails.humidity.setContentDescription(humidityA11y)

        mWeatherDetailBinding.extraDetails.humidityLabel.setContentDescription(humidityA11y)

        /****************************
         * Wind speed and direction *
         */
        /* Read wind speed (in MPH) and direction (in compass degrees)*/
        val windSpeed = weatherData.wind
        val windDirection = weatherData.degrees
        val windString = WeatherUtils.getFormattedWind(this, windSpeed, windDirection)
        val windA11y = getString(R.string.a11y_wind, windString)

        /* Set the text and content description (for accessibility purposes) */
        mWeatherDetailBinding.extraDetails.windMeasurement.setText(windString)
        mWeatherDetailBinding.extraDetails.windMeasurement.setContentDescription(windA11y)
        mWeatherDetailBinding.extraDetails.windLabel.setContentDescription(windA11y)

        /************
         * Pressure *
         */
        val pressure = weatherData.pressure

        /*
         * Format the pressure text using string resources. The reason we directly access
         * resources using getString rather than using a method from SunshineWeatherUtils as
         * we have for other data displayed in this Activity is because there is no
         * additional logic that needs to be considered in order to properly display the
         * pressure.
         */
        val pressureString = getString(R.string.format_pressure, pressure)

        val pressureA11y = getString(R.string.a11y_pressure, pressureString)

        /* Set the text and content description (for accessibility purposes) */
        mWeatherDetailBinding.extraDetails.pressure.setText(pressureString)
        mWeatherDetailBinding.extraDetails.pressure.setContentDescription(pressureA11y)
        mWeatherDetailBinding.extraDetails.pressureLabel.setContentDescription(pressureA11y)
    }

}
