package com.mistbtv.weatherapp.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<B: ViewBinding> : Fragment() {

    lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = initFragmentBinding(inflater, container)
        return binding.root
    }

    abstract fun initFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B
}