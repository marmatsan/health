package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.onboarding_domain.extensions.hasAtMostLengthOf
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateHeight
import com.marmatsan.onboarding_ui.events.HeightEvent
import com.marmatsan.onboarding_ui.states.HeightState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateHeight: ValidateHeight,
    private val filterOutDigits: FilterOutDigits
) : BaseViewModel<HeightEvent>() {

    private val _state = MutableStateFlow(HeightState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: HeightEvent) {
        when (event) {
            is HeightEvent.OnHeightChange -> {
                if (event.height hasAtMostLengthOf 3) {
                    _state.value = _state.value.copy(height = filterOutDigits(event.height))
                }
            }

            is HeightEvent.OnNextClick -> {
                viewModelScope.launch {
                    when (val result = validateHeight(height = state.value.height)) {
                        is UseCaseResult.Success -> {
                            preferences.saveHeight(result.data)
                            sendSuccessEvent()
                        }

                        is UseCaseResult.Error -> {
                            sendErrorEvent(result.message)
                        }
                    }
                }
            }
        }
    }
}