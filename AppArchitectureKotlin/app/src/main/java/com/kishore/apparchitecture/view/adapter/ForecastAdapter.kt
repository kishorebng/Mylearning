package com.kishore.apparchitecture.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kishore.apparchitecture.Utility.WeatherDateUtils
import com.kishore.apparchitecture.Utility.WeatherUtils
import com.kishore.apparchitecture.model.forview.ListWeatherEntry
import java.util.*
import com.kishore.apparchitecture.R

class ForecastAdapter (context: Context, clickHandler: ForecastAdapterOnItemClickHandler) : RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder>(){

    private val VIEW_TYPE_TODAY = 0
    private val VIEW_TYPE_FUTURE_DAY = 1

    private lateinit var mContext: Context
    /*
    *  we've defined an interface to handle clicks on items within this Adapter
    */
    private lateinit var mClickHandler: ForecastAdapterOnItemClickHandler

    /*
       List data holder
     */
    private var mForecast: List<ListWeatherEntry>? = null

    private var mUseTodayLayout: Boolean = false

    init {
        mContext = context
        mClickHandler = clickHandler
        mUseTodayLayout = mContext.getResources().getBoolean(R.bool.use_today_layout)
    }

    /**
     * This gets called when each new ViewHolder is created. This happens when the RecyclerView
     * is laid out. Enough ViewHolders will be created to fill the screen and allow for scrolling.
     *
     * @param viewGroup The ViewGroup that these ViewHolders are contained within.
     * @param viewType  If your RecyclerView has more than one type of item (like ours does) you
     * can use this viewType integer to provide a different layout. See
     * for more details.
     * @return A new ForecastAdapterViewHolder that holds the View for each list item
     */
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ForecastAdapterViewHolder {
        val layoutId = getLayoutIdByType(viewType)
        val view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false)
        view.isFocusable = true
        return ForecastAdapterViewHolder(view)
    }

    override fun getItemCount(): Int {
        return if (null == mForecast) 0 else mForecast!!.size
    }

    /**
     * OnBindViewHolder is called by the RecyclerView to display the data at the specified
     * position. In this method, we update the contents of the ViewHolder to display the weather
     * details for this particular position, using the "position" argument that is conveniently
     * passed into us.
     *
     * @param forecastAdapterViewHolder The ViewHolder which should be updated to represent the
     * contents of the item at the given position in the data set.
     * @param position                  The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(forecastAdapterViewHolder: ForecastAdapterViewHolder, position: Int) {

        val currentWeather = mForecast!![position]

        /****************
         * Weather Icon *
         */
        val weatherIconId = currentWeather.getWeatherIconId()
        val weatherImageResourceId = getImageResourceId(weatherIconId, position)
        forecastAdapterViewHolder.iconView.setImageResource(weatherImageResourceId)

        /****************
         * Weather Date *
         */
        val dateInMillis = currentWeather.getDate()!!.getTime()
        /* Get human readable string using our utility method */
        val dateString = WeatherDateUtils.getFriendlyDateString(mContext, dateInMillis, false)

        /* Display friendly date string */
        forecastAdapterViewHolder.dateView.setText(dateString)

        /***********************
         * Weather Description *
         */
        val description = WeatherUtils.getStringForWeatherCondition(mContext, weatherIconId)
        /* Create the accessibility (a11y) String from the weather description */
        val descriptionA11y = mContext.getString(R.string.a11y_forecast, description)

        /* Set the text and content description (for accessibility purposes) */
        forecastAdapterViewHolder.descriptionView.setText(description)
        forecastAdapterViewHolder.descriptionView.contentDescription = descriptionA11y

        /**************************
         * High (max) temperature *
         */
        val highInCelsius = currentWeather.getMax()
        /*
          * If the user's preference for weather is fahrenheit, formatTemperature will convert
          * the temperature. This method will also append either °C or °F to the temperature
          * String.
          */
        val highString = WeatherUtils.formatTemperature(mContext, highInCelsius)
        /* Create the accessibility (a11y) String from the weather description */
        val highA11y = mContext.getString(R.string.a11y_high_temp, highString)

        /* Set the text and content description (for accessibility purposes) */
        forecastAdapterViewHolder.highTempView.setText(highString)
        forecastAdapterViewHolder.highTempView.contentDescription = highA11y

        /*************************
         * Low (min) temperature *
         */
        val lowInCelsius = currentWeather.getMin()
        /*
          * If the user's preference for weather is fahrenheit, formatTemperature will convert
          * the temperature. This method will also append either °C or °F to the temperature
          * String.
          */
        val lowString = WeatherUtils.formatTemperature(mContext, lowInCelsius)
        val lowA11y = mContext.getString(R.string.a11y_low_temp, lowString)

        /* Set the text and content description (for accessibility purposes) */
        forecastAdapterViewHolder.lowTempView.setText(lowString)
        forecastAdapterViewHolder.lowTempView.contentDescription = lowA11y
    }

    /**
     * Returns an integer code related to the type of View we want the ViewHolder to be at a given
     * position. This method is useful when we want to use different layouts for different items
     * depending on their position. In Sunshine, we take advantage of this method to provide a
     * different layout for the "today" layout. The "today" layout is only shown in portrait mode
     * with the first item in the list.
     *
     * @param position index within our RecyclerView and list
     * @return the view type (today or future day)
     */
    override fun getItemViewType(position: Int): Int {
        return if (mUseTodayLayout && position == 0) {
            VIEW_TYPE_TODAY
        } else {
            VIEW_TYPE_FUTURE_DAY
        }
    }


    /**
     * Returns the the layout id depending on whether the list item is a normal item or the larger
     * "today" list item.
     *
     * @param viewType
     * @return
     */
    private fun getLayoutIdByType(viewType: Int): Int {
        when (viewType) {

            VIEW_TYPE_TODAY -> {
                return R.layout.list_item_forecast_today
            }

            VIEW_TYPE_FUTURE_DAY -> {
                return R.layout.forecast_list_item
            }

            else -> throw IllegalArgumentException("Invalid view type, value of $viewType")
        }
    }

    /**
     * Converts the weather icon id from Open Weather to the local image resource id. Returns the
     * correct image based on whether the forecast is for today(large image) or the future(small image).
     *
     * @param weatherIconId Open Weather icon id
     * @param position      Position in list
     * @return Drawable image resource id for weather
     */
    private fun getImageResourceId(weatherIconId: Int, position: Int): Int {
        val viewType = getItemViewType(position)

        when (viewType) {

            VIEW_TYPE_TODAY -> return WeatherUtils
                    .getLargeArtResourceIdForWeatherCondition(weatherIconId)

            VIEW_TYPE_FUTURE_DAY -> return WeatherUtils
                    .getSmallArtResourceIdForWeatherCondition(weatherIconId)

            else -> throw IllegalArgumentException("Invalid view type, value of $viewType")
        }
    }


    /**
     * Swaps the list used by the ForecastAdapter for its weather data. This method is called by
     * [MainActivity] after a load has finished. When this method is called, we assume we have
     * a new set of data, so we call notifyDataSetChanged to tell the RecyclerView to update.
     *
     * @param newForecast the new list of forecasts to use as ForecastAdapter's data source
     */
    fun swapForecast(newForecast: List<ListWeatherEntry>) {
        // If there was no forecast data, then recreate all of the list
        if (mForecast == null) {
            mForecast = newForecast
            notifyDataSetChanged()
        } else {
            /*
            * Otherwise we use DiffUtil to calculate the changes and update accordingly. This
            * shows the four methods you need to override to return a DiffUtil callback. The
            * old list is the current list stored in mForecast, where the new list is the new
            * values passed in from the observing the database.
            */

            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int {
                    return mForecast!!.size
                }

                override fun getNewListSize(): Int {
                    return newForecast.size
                }

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return mForecast!![oldItemPosition].getId() === newForecast.get(newItemPosition).getId()
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    val newWeather = newForecast[newItemPosition]
                    val oldWeather = mForecast!![oldItemPosition]
                    return newWeather.getId() === oldWeather.getId() && newWeather.getDate()!!.equals(oldWeather.getDate())
                }
            })
            mForecast = newForecast
            result.dispatchUpdatesTo(this)
        }
    }


    /**
     * The interface that receives onItemClick messages.
     */
    interface ForecastAdapterOnItemClickHandler {
        fun onItemClick(date: Date)
    }

    /**
     * A ViewHolder is a required part of the pattern for RecyclerViews. It mostly behaves as
     * a cache of the child views for a forecast item. It's also a convenient place to set an
     * OnClickListener, since it has access to the adapter and the views.
     */
    inner class ForecastAdapterViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        val iconView: ImageView

        val dateView: TextView
        val descriptionView: TextView
        val highTempView: TextView
        val lowTempView: TextView

        init {

            iconView = view.findViewById(R.id.weather_icon)
            dateView = view.findViewById(R.id.date)
            descriptionView = view.findViewById(R.id.weather_description)
            highTempView = view.findViewById(R.id.high_temperature)
            lowTempView = view.findViewById(R.id.low_temperature)

            view.setOnClickListener(this)
        }

        /**
         * This gets called by the child views during a click. We fetch the date that has been
         * selected, and then call the onItemClick handler registered with this adapter, passing that
         * date.
         *
         * @param v the View that was clicked
         */
        override fun onClick(v: View) {
            val adapterPosition = adapterPosition
            val date = mForecast!![adapterPosition].getDate()
            mClickHandler.onItemClick(date)
        }
    }
}