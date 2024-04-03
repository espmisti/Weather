package com.mistbtv.weatherapp.util

import com.mistbtv.weatherapp.R

object Utils {

    fun getWeatherImage(code: Int, isDay: Boolean): Int {
        return when (code) {
            1003 -> if (isDay) R.drawable.ic_weather_partly_cloudy_day else R.drawable.ic_weather_partly_cloudy_night
            else -> if (isDay) R.drawable.ic_weather_sunny_day else R.drawable.ic_weather_moon_night
        }
    }

    fun isDay(value: Int): Boolean = value != 0
}