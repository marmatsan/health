package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.State
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<T : State, E : UiEvent> @Inject constructor(
    private val preferences: Preferences
) : ViewModel() {



    private val _state = MutableStateFlow(initialState())
    var state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    protected abstract fun initialState(): T
    protected abstract suspend fun handleEvent(event: E)
    protected abstract suspend fun saveData(value: T)

    fun onEvent(event: E) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    protected suspend fun saveAndSendSuccessEvent() {
        viewModelScope.launch {
            saveData(state.value)
            _uiEvent.send(UiEvent.Success)
        }
    }

    protected suspend fun sendErrorEvent(message: Int) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.ShowSnackBar(UiText.StringResource(message)))
        }
    }
}