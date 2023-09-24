package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_ui.events.GenderEvent
import com.marmatsan.onboarding_ui.states.GenderState
import com.marmatsan.onboarding_ui.events.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateGender: ValidateGender
) : ViewModel() {


    private val _state = MutableStateFlow(value = GenderState())
    val state = _state.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), GenderState())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    fun onEvent(event: GenderEvent) {
        when (event) {
            is GenderEvent.OnGenderChange -> {
                = state.value.copy(gender = event.gender)
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