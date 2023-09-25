package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_ui.R
import com.marmatsan.onboarding_ui.events.AgeEvent
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.AgeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    private val _state = MutableStateFlow(AgeState())
    var state = _state.asStateFlow()

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChange -> {
                if (state.value.age.length <= 3) {
                    _state.value = _state.value.copy(age = filterOutDigits(event.age))
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

        }
    }
}