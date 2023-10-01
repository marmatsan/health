package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.onboarding_domain.extensions.hasAtMostLengthOf
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateWeight
import com.marmatsan.onboarding_ui.events.WeightEvent
import com.marmatsan.onboarding_ui.states.WeightState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateWeight: ValidateWeight,
    private val filterOutDigits: FilterOutDigits
) : BaseViewModel<WeightEvent>() {

    private val _state = MutableStateFlow(WeightState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: WeightEvent) {
        when (event) {
            is WeightEvent.OnWeightChange -> {
                if (event.weight hasAtMostLengthOf 3) {
                    _state.value = _state.value.copy(weight = filterOutDigits(event.weight))
                }
            }

            is WeightEvent.OnNextClick -> {
                viewModelScope.launch {
                    when (val result = validateWeight(weight = state.value.weight)) {
                        is UseCaseResult.Success -> {
                            preferences.saveWeight(result.data)
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