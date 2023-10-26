package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.onboarding_domain.extensions.hasAtMostLengthOf
import com.marmatsan.core_domain.usecases.FilterOutDigits
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.onboarding_domain.usecases.ValidateAge
import com.marmatsan.onboarding_ui.events.AgeEvent
import com.marmatsan.onboarding_ui.states.AgeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgeViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateAge: ValidateAge,
    private val filterOutDigits: FilterOutDigits
) : BaseViewModel<AgeEvent>() {

    private val _state = MutableStateFlow(AgeState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: AgeEvent) {
        when (event) {
            is AgeEvent.OnAgeChange -> {
                if (event.age hasAtMostLengthOf 3) {
                    _state.value = _state.value.copy(age = filterOutDigits(event.age))
                }
            }

            is AgeEvent.OnNextClick -> {
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