package com.mistbtv.weatherapp.data.repository

import com.mistbtv.weatherapp.data.network.APIService
import com.mistbtv.weatherapp.data.network.model.Weather.Companion.toWeather
import com.mistbtv.weatherapp.data.util.BaseRepository
import com.mistbtv.weatherapp.domain.model.param.CurrentWeatherParams
import com.mistbtv.weatherapp.domain.model.Weather
import com.mistbtv.weatherapp.domain.repository.IWeatherRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
    private val api: APIService
) : IWeatherRepository, BaseRepository() {

    override suspend fun getCurrentWeather(params: CurrentWeatherParams): Flow<Result<Weather>> = safeApiCall {
        api.getCurrentWeather(
            query = params.coordinate,
            geo = params.geo
        )
    }.map { result ->
        result.map { it.toWeather() }
    }
}