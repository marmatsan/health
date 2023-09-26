package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_domain.extensions.hasAtMostLengthOf
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateAge
import com.marmatsan.onboarding_ui.events.AgeEvent
import com.marmatsan.onboarding_ui.states.AgeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val validateAge: ValidateAge,
    private val filterOutDigits: FilterOutDigits
) : BaseViewModel<AgeState, AgeEvent>(
    initialState = AgeState()
) {

    override suspend fun handleEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChange -> {
                if (event.age hasAtMostLengthOf 3) {
                    _state.value = _state.value.copy(age = filterOutDigits(event.age))
                }
            }

            is AgeEvent.OnNextClicked -> {
                viewModelScope.launch {
                    when (val result = validateAge(age = state.value.age)) {
                        is UseCaseResult.Success -> {
                            preferences.saveAge(result.data)
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