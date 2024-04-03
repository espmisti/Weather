package com.mistbtv.weatherapp.domain.usecase

import com.mistbtv.weatherapp.domain.model.param.CurrentWeatherParams
import com.mistbtv.weatherapp.domain.model.Weather
import com.mistbtv.weatherapp.domain.repository.IWeatherRepository
import com.mistbtv.weatherapp.domain.util.BaseUseCase
import kotlinx.coroutines.flow.Flow

class GetCurrentWeatherUseCase(
    private val repository: IWeatherRepository
) : BaseUseCase<CurrentWeatherParams, Weather>() {

    override suspend operator fun invoke(params: CurrentWeatherParams): Flow<Result<Weather>> {
        return repository.getCurrentWeather(params = params)
    }
}