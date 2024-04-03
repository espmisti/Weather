package com.mistbtv.weatherapp.data.network.model

import com.mistbtv.weatherapp.domain.model.Location as DomainLocation

data class Location(
    val country: String,
    val lat: Double,
    val localtime: String,
    val localtime_epoch: Int,
    val lon: Double,
    val name: String,
    val region: String,
    val tz_id: String
) {
    companion object {
        fun Location.toLocation(): DomainLocation = DomainLocation(
            country = country,
            name = name,
            region = region
        )
    }
}