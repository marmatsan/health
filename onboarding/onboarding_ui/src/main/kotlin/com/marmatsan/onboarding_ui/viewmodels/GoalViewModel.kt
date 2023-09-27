package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_ui.events.ActivityLevelEvent
import com.marmatsan.onboarding_ui.events.GenderEvent
import com.marmatsan.onboarding_ui.events.GoalEvent
import com.marmatsan.onboarding_ui.states.ActivityLevelState
import com.marmatsan.onboarding_ui.states.GenderState
import com.marmatsan.onboarding_ui.states.GoalState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class GoalViewModel : BaseViewModel<GoalState, GoalEvent>(
    initialState = GoalState()
) {

    override suspend fun handleEvent(event: GoalEvent) {
        when (event) {
            is GoalEvent.OnGoalChange -> {
                _state.value = _state.value.copy(goal = event.goal)
            }

            is GoalEvent.OnNextClicked -> {
                viewModelScope.launch {
                    preferences.saveGoal(state.value.goal)
                    sendSuccessEvent()
                }
            }
        }
    }
}