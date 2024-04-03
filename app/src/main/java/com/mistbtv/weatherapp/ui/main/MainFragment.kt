package com.mistbtv.weatherapp.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.mistbtv.weatherapp.R
import com.mistbtv.weatherapp.databinding.FragmentMainBinding
import com.mistbtv.weatherapp.domain.model.Location
import com.mistbtv.weatherapp.ui.main.contract.MainContract
import com.mistbtv.weatherapp.ui.main.contract.MainViewModel
import com.mistbtv.weatherapp.util.BaseFragment
import com.mistbtv.weatherapp.util.Utils.getWeatherImage
import com.mistbtv.weatherapp.util.Utils.isDay
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()

    override fun initFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Observer
        viewModel.state.observe(viewLifecycleOwner, stateLiveData())

        // Get Current Weather
        viewModel.handleIntent(event = MainContract.Intent.GetCurrentWeather(
            coordinate = "53.507852,49.420411",
            geo = "ru"
        ))

        // Button Change Country
        binding.btnCountry.setOnClickListener {

        }
    }

    private fun stateLiveData()= Observer<MainContract.State> { state ->
        when (state.currentWeather) {
            is MainContract.CurrentWeatherState.Success -> {
                val data = state.currentWeather.data

                // Country
                binding.txtCountry.text = data.location.name

                // Main
                val temp = data.current.temp_c.toString().substringBefore(".")
                binding.txtTemp.text = temp

                val day = isDay(data.current.is_day)
                val code = data.current.condition.code
                val image = getWeatherImage(code = code, isDay = day)
                binding.image.setImageResource(image)

                val condition = data.current.condition.text
                binding.txtCondition.text = condition

                // Small Bar
                val windKph = data.current.wind_kph
                binding.txtWindSpeed.text = "$windKph${getString(R.string.main_txt_km_h)}"

                val feelsLike = data.current.feelslike_c
                binding.txtFeelsLike.text = "$feelsLike°"

                val humidity = data.current.humidity
                binding.txtHumidity.text = "$humidity%"
                Log.d("APP_CHECK", "$data")
            }
            else -> Unit
        } 
    }
}