package com.mistbtv.weatherapp.data.network.model

import com.mistbtv.weatherapp.data.network.model.Current.Companion.toCurrent
import com.mistbtv.weatherapp.data.network.model.Location.Companion.toLocation
import com.mistbtv.weatherapp.domain.model.Weather as DomainWeather

data class Weather(
    val current: Current,
    val location: Location
) {
    companion object {
        fun Weather.toWeather(): DomainWeather = DomainWeather(
            current = current.toCurrent(),
            location = location.toLocation()
        )
    }
}