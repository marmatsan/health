package com.marmatsan.onboarding_ui.age

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_ui.UiEvent
import com.marmatsan.core_domain.util.UiText
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_ui.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits
) : ViewModel() {

    var state by mutableStateOf(AgeState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChange -> {
                if (state.age.length <= 3) {
                    state = state.copy(age = filterOutDigits(event.age))
                }
            }

            is AgeEvent.OnNextClicked -> {
                viewModelScope.launch {
                    val ageNumber = state.age.toIntOrNull() ?: run {
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