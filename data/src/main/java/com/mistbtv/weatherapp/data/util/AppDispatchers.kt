package com.mistbtv.weatherapp.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

object AppDispatchers {
    val MAIN: MainCoroutineDispatcher = Dispatchers.Main
    val DEFAULT: CoroutineDispatcher = Dispatchers.Default
    val IO: CoroutineDispatcher = Dispatchers.IO
    val UNCONFINED: CoroutineDispatcher = Dispatchers.Unconfined
}