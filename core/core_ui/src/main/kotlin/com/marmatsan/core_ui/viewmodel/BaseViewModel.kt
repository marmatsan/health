package com.marmatsan.core_ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.core_ui.event.Event
import com.marmatsan.core_ui.event.UiEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<E : Event> : ViewModel() {

    protected val _uiEvent = Channel<UiEvent>()
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