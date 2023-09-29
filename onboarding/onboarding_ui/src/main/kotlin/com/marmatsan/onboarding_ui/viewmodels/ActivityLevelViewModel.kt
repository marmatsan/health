package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.onboarding_ui.events.ActivityLevelEvent
import com.marmatsan.onboarding_ui.states.ActivityLevelState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityLevelViewModel @Inject constructor() : BaseViewModel<ActivityLevelState, ActivityLevelEvent>(
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