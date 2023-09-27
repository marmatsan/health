package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.onboarding_domain.extensions.hasAtMostLengthOf
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateHeight
import com.marmatsan.onboarding_ui.events.HeightEvent
import com.marmatsan.onboarding_ui.states.HeightState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeightViewModel @Inject constructor(
    private val validateHeight: ValidateHeight,
    private val filterOutDigits: FilterOutDigits
) : BaseViewModel<HeightState, HeightEvent>(
    initialState = HeightState()
) {

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