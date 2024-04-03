package com.mistbtv.weatherapp.ui.main.contract

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mistbtv.weatherapp.data.util.AppDispatchers
import com.mistbtv.weatherapp.domain.model.param.CurrentWeatherParams
import com.mistbtv.weatherapp.domain.usecase.GetCurrentWeatherUseCase
import com.mistbtv.weatherapp.util.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : BaseViewModel<MainContract.State, MainContract.Intent>() {

    override fun initState(): MainContract.State = MainContract.State(
        currentWeather = MainContract.CurrentWeatherState.Idle
    )

    override fun handleIntent(event: MainContract.Intent) {
        when (event) {
            is MainContract.Intent.GetCurrentWeather -> fetchCurrentWeather(
                coordinate = event.coordinate,
                geo = event.geo
            )
        }
    }

    private fun fetchCurrentWeather(coordinate: String, geo: String) =
        viewModelScope.launch(AppDispatchers.IO) {
            val params = CurrentWeatherParams(
                coordinate = coordinate,
                geo = geo
            )
            getCurrentWeatherUseCase(params = params).collect { result ->
                result.fold(
                    onSuccess = {
                        setState {
                            copy(currentWeather = MainContract.CurrentWeatherState.Success(data = it))
                        }
                    },
                    onFailure = {
                        Log.e("app_check", "[Error]: $it")
                    }
                )
            }
        }
}