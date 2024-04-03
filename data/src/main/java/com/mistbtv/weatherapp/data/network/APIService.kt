package com.mistbtv.weatherapp.data.network

import com.mistbtv.weatherapp.data.network.model.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

internal interface APIService {

    /**
     * Get current weather
     */
    @GET(GET_CURRENT_WEATHER)
    suspend fun getCurrentWeather(
        @Query("key") key: String = KEY,
        @Query("q") query: String,
        @Query("lang") geo: String
    ): Response<Weather>

    /**
     * Get forecast weather
     */
    @GET(GET_FORECAST_WEATHER)
    suspend fun getForecastWeather()

    companion object {
        const val URL = "https://api.weatherapi.com/v1/"
        const val KEY = "3afbc1eed6fd48f495f162747240204"

        private const val GET_CURRENT_WEATHER = "current.json"
        private const val GET_FORECAST_WEATHER = "forecast.json"
    }
}