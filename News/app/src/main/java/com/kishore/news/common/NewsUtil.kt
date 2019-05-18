package com.kishore.news.common

import java.text.SimpleDateFormat
import java.util.*

class NewsUtil {

    companion object {

        fun  formatDate(input :String?) : String {
            val input_DateFormat = "yyyy-MM-dd'T'HH:mm:ss'Z'"
            val output_DateFormat = "E MMM dd YYYY hh:mm a"
            val inputDateFormat = SimpleDateFormat(input_DateFormat)
            inputDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"))
            val date = inputDateFormat.parse(input)
            val outputDateFormat = SimpleDateFormat(output_DateFormat)
            return outputDateFormat.format(date)
        }

        fun  getformatedToday() : String {
            val output_DateFormat = "yyyy-MM-dd"
            val outputDateFormat = SimpleDateFormat(output_DateFormat)
            return output_DateFormat.format(Date())
        }

        fun getCountryCode () : String {
            return Locale.getDefault().country.toLowerCase()
        }

        fun getDisplayCountry () : String {
            return Locale.getDefault().displayCountry.toLowerCase()
        }

    }
}