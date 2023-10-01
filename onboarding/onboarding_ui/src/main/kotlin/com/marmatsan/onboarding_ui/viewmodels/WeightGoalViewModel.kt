package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.core_ui.viewmodel.BaseViewModel
import com.marmatsan.onboarding_ui.events.WeightGoalEvent
import com.marmatsan.onboarding_ui.states.WeightGoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightGoalViewModel @Inject constructor(
    private val preferences: Preferences
) : BaseViewModel<WeightGoalEvent>() {

    private val _state = MutableStateFlow(WeightGoalState())
    val state = _state.asStateFlow()

    override suspend fun handleEvent(event: WeightGoalEvent) {
        when (event) {
            is WeightGoalEvent.OnWeightGoalChange -> {
                _state.value = _state.value.copy(goal = event.goal)
            }

            is WeightGoalEvent.OnNextClick -> {
                viewModelScope.launch {
                    preferences.saveGoal(state.value.goal)
                    sendSuccessEvent()
                }
            }
        }
    }
}