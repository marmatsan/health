package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.onboarding_domain.use_case.FilterOutDigits
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateNutrients
import com.marmatsan.onboarding_ui.events.NutrientGoalEvent
import com.marmatsan.onboarding_ui.events.UiEvent
import com.marmatsan.onboarding_ui.states.NutrientGoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : BaseViewModel<NutrientGoalState, NutrientGoalEvent>(
    initialState = NutrientGoalState()
) {

    override suspend fun handleEvent(event: NutrientGoalEvent) {
        when (event) {
            is NutrientGoalEvent.OnCarbPctChange -> {
                _state.value = _state.value.copy(
                    carbsPct = filterOutDigits(event.carbPct)
                )
            }

            is NutrientGoalEvent.OnProteinPctChange -> {
                _state.value = _state.value.copy(
                    proteinPct = filterOutDigits(event.proteinPct)
                )
            }

            is NutrientGoalEvent.OnFatPctChange -> {
                _state.value = _state.value.copy(
                    fatPct = filterOutDigits(event.fatPct)
                )
            }

            is NutrientGoalEvent.OnNextClick -> {
                val result = validateNutrients(
                    carbsPctText = state.value.carbsPct,
                    proteinPctText = state.value.proteinPct,
                    fatPctText = state.value.fatPct
                )
                when (result) {
                    is UseCaseResult.Success -> {
                        viewModelScope.launch {
                            preferences.saveCarbRatio(result.data.carbsRatio)
                            preferences.saveProteinRatio(result.data.proteinRatio)
                            preferences.saveFatRatio(result.data.fatRatio)
                            sendSuccessEvent()
                        }
                    }

                    is UseCaseResult.Error -> {
                        viewModelScope.launch {
                            sendErrorEvent(result.message)
                        }
                    }
                }
            }
        }
    }

}