package com.mistbtv.weatherapp.data.di

import com.mistbtv.weatherapp.data.network.APIService
import com.mistbtv.weatherapp.data.repository.WeatherRepositoryImpl
import com.mistbtv.weatherapp.domain.repository.IWeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(api: APIService): IWeatherRepository = WeatherRepositoryImpl(api)
}