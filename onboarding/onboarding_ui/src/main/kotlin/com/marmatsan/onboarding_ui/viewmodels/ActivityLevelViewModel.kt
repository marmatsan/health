package com.marmatsan.onboarding_ui.viewmodels

import androidx.lifecycle.viewModelScope
import com.marmatsan.core_domain.preferences.Preferences
import com.marmatsan.onboarding_domain.use_case.UseCaseResult
import com.marmatsan.onboarding_domain.use_case.ValidateGender
import com.marmatsan.onboarding_ui.events.ActivityLevelEvent
import com.marmatsan.onboarding_ui.events.GenderEvent
import com.marmatsan.onboarding_ui.states.ActivityLevelState
import com.marmatsan.onboarding_ui.states.GenderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActivityLevelViewModel : BaseViewModel<ActivityLevelState, ActivityLevelEvent>(
    initialState = ActivityLevelState()
) {

    override suspend fun handleEvent(event: ActivityLevelEvent) {
        when (event) {
            is ActivityLevelEvent.OnActivityLevelChange -> {
                _state.value = _state.value.copy(activityLevel = event.activityLevel)
            }

            is ActivityLevelEvent.OnNextClicked -> {
                viewModelScope.launch {
                    preferences.saveActivityLevel(state.value.activityLevel)
                    sendSuccessEvent()
                }
            }
        }
    }
}