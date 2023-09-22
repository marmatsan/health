package com.marmatsan.onboarding_ui.gender

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_ui.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateGender: ValidateGender,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val state = savedStateHandle.getStateFlow(key = "gender", initialValue = GenderState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: GenderEvent) {
        when (event) {
            is GenderEvent.OnGenderChange -> {
                val newState = state.value.copy(gender = event.gender)
                savedStateHandle["gender"] = newState
            }

            is GenderEvent.OnNextClicked -> {
                when (val result = validateGender(state.value.gender)) {
                    is ValidateGender.Result.Success -> {
                        viewModelScope.launch {
                            preferences.saveGender(state.value.gender)
                            _uiEvent.send(UiEvent.Success)
                        }
                    }

                    is ValidateGender.Result.Error -> {
                        viewModelScope.launch {
                            _uiEvent.send(UiEvent.ShowSnackBar(message = result.message))
                        }
                    }
                }
            }
        }
    }
}