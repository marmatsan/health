package com.marmatsan.onboarding_ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_ui.R
import com.marmatsan.onboarding_ui.events.AgeEvent
import com.marmatsan.onboarding_ui.states.AgeState
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.GenderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    companion object {
        private const val STATE_KEY = "ageState"
    }

    val state = savedStateHandle.getStateFlow(key = STATE_KEY, initialValue = AgeState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChange -> {
                if (state.value.age.length <= 3) {
                    savedStateHandle[STATE_KEY] = state.value.copy(age = filterOutDigits(event.age))
                }
            }

            is AgeEvent.OnNextClicked -> {
                viewModelScope.launch {
                    val ageNumber = state.value.age.toIntOrNull() ?: run {
                        _uiEvent.send(
                            UiEvent.ShowSnackBar(
                                UiText.StringResource(R.string.error_age_cant_be_empty)
                            )
                        )
                        return@launch
                    }
                    preferences.saveAge(ageNumber)
                    _uiEvent.send(UiEvent.Success)
                }
            }

            is AgeEvent.OnBackClicked -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateBack)
                }
            }
        }
    }
}