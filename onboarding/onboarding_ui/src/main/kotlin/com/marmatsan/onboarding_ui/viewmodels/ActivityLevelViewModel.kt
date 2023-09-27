package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.onboarding_ui.events.ActivityLevelEvent
import com.marmatsan.onboarding_ui.states.ActivityLevelState
import kotlinx.coroutines.launch

class ActivityLevelViewModel : BaseViewModel<ActivityLevelState, ActivityLevelEvent>(
    initialState = ActivityLevelState()
) {

    override suspend fun handleEvent(event: ActivityLevelEvent) {
        when (event) {
            is ActivityLevelEvent.OnActivityLevelChange -> {
                _state.value = _state.value.copy(activityLevel = event.activityLevel)
            }

            is ActivityLevelEvent.OnNextClick -> {
                viewModelScope.launch {
                    preferences.saveActivityLevel(state.value.activityLevel)
                    sendSuccessEvent()
                }
            }
        }
    }
}