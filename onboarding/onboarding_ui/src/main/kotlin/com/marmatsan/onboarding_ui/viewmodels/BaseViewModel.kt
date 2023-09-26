package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.preferences.PreferencesData
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_ui.events.Event
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

abstract class BaseViewModel<T : State, E : Event>(
    initialState: T,
) : ViewModel() {

    @Inject
    protected lateinit var preferences: Preferences

    protected val _state = MutableStateFlow(initialState)
    var state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    protected abstract suspend fun handleEvent(event: E)

    fun onEvent(event: E) {
        viewModelScope.launch {
            handleEvent(event)
        }
    }

    protected suspend fun sendSuccessEvent() {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.Success)
        }
    }

    protected suspend fun sendErrorEvent(message: UiText) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.ShowSnackBar(message))
        }
    }
}