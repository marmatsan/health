package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_ui.events.GenderEvent
import com.marmatsan.onboarding_ui.states.GenderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val preferences: Preferences,
    private val validateGender: ValidateGender
) : BaseViewModel<GenderEvent>() {

    private val _state = MutableStateFlow(GenderState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: GenderEvent) {
        when (event) {
            is GenderEvent.OnGenderChange -> {
                _state.value = _state.value.copy(gender = event.gender)
            }

            is GenderEvent.OnNextClick -> {
                viewModelScope.launch {
                    when (val result = validateGender(state.value.gender)) {
                        is UseCaseResult.Success -> {
                            preferences.saveGender(result.data)
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