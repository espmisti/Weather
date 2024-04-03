package com.mistbtv.weatherapp.domain.repository

import com.mistbtv.weatherapp.domain.model.param.CurrentWeatherParams
import com.mistbtv.weatherapp.domain.model.Weather
import kotlinx.coroutines.flow.Flow

interface IWeatherRepository {
    suspend fun getCurrentWeather(params: CurrentWeatherParams): Flow<Result<Weather>>
}