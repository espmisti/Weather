package com.mistbtv.weatherapp.domain.util

import kotlinx.coroutines.flow.Flow

abstract class BaseUseCase<Params: Any, Out : Any> {
    abstract suspend operator fun invoke(params: Params): Flow<Result<Out>>
}