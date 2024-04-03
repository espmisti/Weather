package com.mistbtv.weatherapp.di

import com.mistbtv.weatherapp.domain.repository.IWeatherRepository
import com.mistbtv.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetCurrentWeatherUseCase(
        repository: IWeatherRepository
    ): GetCurrentWeatherUseCase = GetCurrentWeatherUseCase(
        repository = repository
    )
}