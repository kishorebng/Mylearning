package com.kishore.news.model

import java.util.*
import java.util.concurrent.TimeUnit

class NewsModelUtil {

    companion object {

        /* Milliseconds in a day */
        val DAY_IN_MILLIS = TimeUnit.DAYS.toMillis(1)
        fun getNormalizedUtcMsForToday(): Long {

            /*
         * This number represents the number of milliseconds that have elapsed since January
         * 1st, 1970 at midnight in the GMT time zone.
         */
            val utcNowMillis = System.currentTimeMillis()

            /*
         * This TimeZone represents the device's current time zone. It provides us with a means
         * of acquiring the offset for local time from a UTC time stamp.
         */
            val currentTimeZone = TimeZone.getDefault()

            /*
         * The getOffset method returns the number of milliseconds to add to UTC time to get the
         * elapsed time since the epoch for our current time zone. We pass the current UTC time
         * into this method so it can determine changes to account for daylight savings time.
         */
            val gmtOffsetMillis = currentTimeZone.getOffset(utcNowMillis)

            /*
         * UTC time is measured in milliseconds from January 1, 1970 at midnight from the GMT
         * time zone. Depending on your time zone, the time since January 1, 1970 at midnight (GMT)
         * will be greater or smaller. This variable represents the number of milliseconds since
         * January 1, 1970 (GMT) time.
         */
            val timeSinceEpochLocalTimeMillis = utcNowMillis + gmtOffsetMillis

            /* This method simply converts milliseconds to days, disregarding any fractional days */
            val daysSinceEpochLocal = TimeUnit.MILLISECONDS.toDays(timeSinceEpochLocalTimeMillis)

            /*
         * Finally, we convert back to milliseconds. This time stamp represents today's date at
         * midnight in GMT time. We will need to account for local time zone offsets when
         * extracting this information from the database.
         */

            return TimeUnit.DAYS.toMillis(daysSinceEpochLocal)
        }


        fun getNormalizedUtcDateForToday(): Date {
            val normalizedMilli = getNormalizedUtcMsForToday()
            return Date(normalizedMilli)
        }


          val NORMAL_NEWS : Int = 0
          val HEADLINE : Int = 1
    }
}