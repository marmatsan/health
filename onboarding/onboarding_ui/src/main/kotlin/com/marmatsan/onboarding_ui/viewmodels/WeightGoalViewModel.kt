package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.onboarding_ui.events.WeightGoalEvent
import com.marmatsan.onboarding_ui.states.WeightGoalState
import kotlinx.coroutines.launch

class WeightGoalViewModel : BaseViewModel<WeightGoalState, WeightGoalEvent>(
    initialState = WeightGoalState()
) {

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