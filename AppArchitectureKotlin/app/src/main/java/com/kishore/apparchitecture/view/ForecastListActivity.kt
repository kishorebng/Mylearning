package com.kishore.apparchitecture.view;

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import androidx.recyclerview.widget.RecyclerView
import com.kishore.apparchitecture.InjectorUtils
import com.kishore.apparchitecture.view.adapter.ForecastAdapter
import com.kishore.apparchitecture.viewmodel.ForecastListViewModel
import java.util.*
import com.kishore.apparchitecture.R

class ForecastListActivity() : AppCompatActivity(), ForecastAdapter.ForecastAdapterOnItemClickHandler {

    private var mPosition = RecyclerView.NO_POSITION
    private lateinit var mForecastAdapter: ForecastAdapter
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mLoadingIndicator: ProgressBar
    private lateinit var mViewModel: ForecastListViewModel


    @SuppressLint("WrongConstant")  // because of Vertical constant
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        // UI component intialization
        mRecyclerView = findViewById(R.id.recyclerview_forecast)
        mLoadingIndicator = findViewById(R.id.pb_loading_indicator)

        /*
       * A LinearLayoutManager is responsible for measuring and positioning item views within a
       * RecyclerView into a linear list. This means that it can produce either a horizontal or
       * vertical list depending on which parameter you pass in to the LinearLayoutManager
       * constructor. In our case, we want a vertical list, so we pass in the constant from the
       * LinearLayoutManager class for vertical lists, LinearLayoutManager.VERTICAL.
       *
       * There are other LayoutManagers available to display your data in uniform grids,
       * staggered grids, and more! See the developer documentation for more details.
       *
       * The third parameter (shouldReverseLayout) should be true if you want to reverse your
       * layout. Generally, this is only true with horizontal lists that need to support a
       * right-to-left layout.
       */
        val layoutManager = LinearLayoutManager(this, VERTICAL, false)

        /* setLayoutManager associates the LayoutManager we created above with our RecyclerView */
        mRecyclerView!!.layoutManager = layoutManager

        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mRecyclerView!!.setHasFixedSize(true)

        /*
        * The ForecastAdapter is responsible for linking our weather data with the Views that
        * will end up displaying our weather data. */

        mForecastAdapter = ForecastAdapter(this, this)

        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView!!.adapter = mForecastAdapter


        // Need View Model to get data
        /* Setting the adapter attaches it to the RecyclerView in our layout. */
        mRecyclerView!!.adapter = mForecastAdapter
        val factory = InjectorUtils.provideForecastListViewModelFactory(this.applicationContext)
        mViewModel = ViewModelProviders.of(this, factory).get(ForecastListViewModel::class.java)

        mViewModel!!.getForecast().observe(this,Observer { weatherEntries  ->
            this.mForecastAdapter!!.swapForecast(weatherEntries!!)
            if (mPosition == RecyclerView.NO_POSITION) mPosition = 0
            mRecyclerView!!.smoothScrollToPosition(mPosition)
            // Show the weather list or the loading screen based on whether the forecast data exists
            // and is loaded
            if (weatherEntries != null && weatherEntries!!.size !== 0)
                showWeatherDataView()
            else
                showLoading()
        })

    }

    override fun onItemClick(date: Date) {
        val weatherDetailIntent = Intent(this, ForecastDetailActivity::class.java)
        val timestamp = date!!.getTime()
        weatherDetailIntent.putExtra(ForecastDetailActivity.WEATHER_ID_EXTRA, timestamp)
        startActivity(weatherDetailIntent)
    }

    /**
     * This method will make the View for the weather data visible and hide the error message and
     * loading indicator.
     *
     *
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private fun showWeatherDataView() {
        // First, hide the loading indicator
        mLoadingIndicator!!.visibility = View.INVISIBLE
        // Finally, make sure the weather data is visible
        mRecyclerView!!.visibility = View.VISIBLE
    }

    /**
     * This method will make the loading indicator visible and hide the weather View and error
     * message.
     *
     *
     * Since it is okay to redundantly set the visibility of a View, we don't need to check whether
     * each view is currently visible or invisible.
     */
    private fun showLoading() {
        // Then, hide the weather data
        mRecyclerView!!.visibility = View.INVISIBLE
        // Finally, show the loading indicator
        mLoadingIndicator!!.visibility = View.VISIBLE
    }

}
