package com.mistbtv.weatherapp.ui.main.contract

import com.mistbtv.weatherapp.domain.model.Weather
import com.mistbtv.weatherapp.util.ViewIntent
import com.mistbtv.weatherapp.util.ViewState
import java.lang.Exception

object MainContract {

    data class State(
        val currentWeather: CurrentWeatherState
    ): ViewState

    sealed class CurrentWeatherState {
        data object Idle : CurrentWeatherState()
        data class Success(val data: Weather): CurrentWeatherState()
        data class Failure(val message: String): CurrentWeatherState()
    }

    sealed class Intent : ViewIntent {
        data class GetCurrentWeather(
            val coordinate: String,
            val geo: String
        ): Intent()
    }

    sealed class Effect {
        data object FillSmallBar : Effect()
    }
}