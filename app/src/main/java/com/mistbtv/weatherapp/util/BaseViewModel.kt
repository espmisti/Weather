package com.mistbtv.weatherapp.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mistbtv.weatherapp.data.util.AppDispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ViewState, Intent : ViewIntent> : ViewModel() {

    private val initialState: State by lazy { initState() }

    private val currentState: State
        get() = state.value!!

    private val _state: MutableLiveData<State> = MutableLiveData(initialState)
    val state: LiveData<State> = _state

    private val _event: MutableSharedFlow<Intent> = MutableSharedFlow()
    val event = _event.asSharedFlow()


    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModelScope.launch(AppDispatchers.IO) {
            event.collect {
                handleIntent(it)
            }
        }
    }

    abstract fun initState(): State

    abstract fun handleIntent(event: Intent)

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _state.postValue(newState)
    }
}

interface ViewIntent
interface ViewState