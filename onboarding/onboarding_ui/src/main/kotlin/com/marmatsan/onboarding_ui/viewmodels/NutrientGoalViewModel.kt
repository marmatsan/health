package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.core_domain.usecases.FilterOutDigits
import com.marmatsan.core_domain.usecases.UseCaseResult
import com.marmatsan.onboarding_domain.usecases.ValidateNutrients
import com.marmatsan.onboarding_ui.events.NutrientGoalEvent
import com.marmatsan.onboarding_ui.states.NutrientGoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NutrientGoalViewModel @Inject constructor(
    private val preferences: Preferences,
    private val filterOutDigits: FilterOutDigits,
    private val validateNutrients: ValidateNutrients
) : BaseViewModel<NutrientGoalEvent>() {

    private val _state = MutableStateFlow(NutrientGoalState())
    val state = _state.asStateFlow()

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