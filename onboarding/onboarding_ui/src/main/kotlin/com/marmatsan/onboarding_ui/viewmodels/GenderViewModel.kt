package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_ui.events.GenderEvent
import com.marmatsan.onboarding_ui.states.GenderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val validateGender: ValidateGender
) : BaseViewModel<GenderState, GenderEvent>(
    initialState = GenderState()
) {

    override suspend fun handleEvent(event: GenderEvent) {
        when (event) {
            is GenderEvent.OnGenderChange -> {
                _state.value = _state.value.copy(gender = event.gender)
            }

            is GenderEvent.OnNextClicked -> {
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